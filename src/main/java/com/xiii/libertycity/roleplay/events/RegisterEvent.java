package com.xiii.libertycity.roleplay.events;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.data.ServerData;
import com.xiii.libertycity.core.utils.TimeUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
public class RegisterEvent implements Listener {

    //Prevent movement if the player isn't registered, as the lowest priority to save performance
    @EventHandler(priority = EventPriority.LOWEST)
    public void onMove(PlayerMoveEvent e) {
        PlayerData data = Data.data.getUserData(e.getPlayer());
        if (data.playerID <= 0) e.setCancelled(true);
    }

    //Prevent commands if the player isn't registered, as lowest priority to save performance
    @EventHandler(priority = EventPriority.LOWEST)
    public void commandChecker(PlayerCommandPreprocessEvent e) {
        PlayerData data = Data.data.getUserData(e.getPlayer());
        if (data.playerID <= 0) e.setCancelled(true);
    }

    //Runs when the player leaves without finishing his registration
    @EventHandler
    public void forUnRegister(PlayerQuitEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {
            PlayerData data = Data.data.getUserData(e.getPlayer());
            if (data.playerID <= 0) {
                isWaitingAge = true;
                isWaitingPrenom = true;
                isWaitingNom = true;
                data.rpPrenom = null;
                data.rpNom = null;
                data.rpAge = 0;
                tempPrenom = null;
                tempName = null;
                tempAge = 0;
            }
        });
    }

    //Runs after rejoin of the registeration to broadcast a welcome message
    @EventHandler
    public void forRegister(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {
            PlayerData data = Data.data.getUserData(e.getPlayer());

            if (data.playerID <= 0) {
                e.getPlayer().sendTitle("§§§l§k|||§r §fBienvenue sur §2§lLiberty§a§lCity §6§lV5 §f! §4§l§k|||", "§6§k§l||§r §7CDébutez par entré votre §e§nPrénom RP§r §6§k§l||", 0, 14000, 0);
            } else {
                if (data.joinDate == null) {
                    Bukkit.broadcastMessage("");
                    Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §a§l" + data.rpPrenom + " §2§l" + data.rpNom + " §f rejoint la ville !");
                    Bukkit.broadcastMessage("");
                    data.joinDate = TimeUtil.getFullDate();
                }
            }


        });
    }

    private int tempAge;
    private String tempName;
    private String tempPrenom;

    private boolean isWaitingPrenom = true;
    private boolean isWaitingNom = true;
    private boolean isWaitingAge = true;


    //Listen to chat messages to register player properly
    @EventHandler
    public void forChatRegister(AsyncPlayerChatEvent e) {

        //Preload data to check if player is registered, if not: cancel his message
        PlayerData tdt = Data.data.getUserData(e.getPlayer());

        if(tdt.playerID <= 0) e.setCancelled(true);

        //Run async
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {

            //Get both player & server data
            PlayerData data = Data.data.getUserData(e.getPlayer());
            ServerData server = Data.data.getServerData(Bukkit.getServer());

            //Load yes & no options listeners
            List<String> optYes = Arrays.asList("yes", "oui", "o", "y", "Yes", "Oui", "O", "Y", "Yas", "yas", "ye", "Ye", "Ya", "ya");
            List<String> optNo = Arrays.asList("no", "non", "n", "annule", "cancel", "nah", "Non", "No", "N", "Nah");
            //List<String> remCara = Arrays.asList(",", "?", ";", ".", ":", "/", "!", "^", "¨", "$", "£", "¤", "ù", "%", "*", "µ", ")", "=", "+", "}", "°", "]", "~", "'", "{", "(", "[", "-", "|", "`", "_", "@", "²", "&", "<", ">", "#");

            //If player's ID is null AKA not registered
            if(data.playerID <= 0) {

                //Register last name (nom) | Check if rpPrenom isn't null & rpNom and rpAge are null
                if(data.rpPrenom != null && data.rpNom == null && data.rpAge == 0) {

                    //If we are waiting for his rpPrenom variable to be set
                    if(isWaitingNom) {

                        //Check database to make sure no one else is registered as the same first and last name
                        if(!server.rpPrenom.contains(data.rpPrenom) && !server.rpNom.contains(e.getMessage())) {

                            //Check if String follows ABC alphabet
                            if(e.getMessage().matches("[a-zA-Z]+")) {

                                //Set temp variable to his message
                                tempName = e.getMessage();

                                //Check if rpPrenom is fitting the length size requirement
                                if (tempName.length() >= 3 && tempName.length() <= 20) {

                                    //If the rpNom fits the length requirement, ask for confirmation and trigger it
                                    e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §fVous vous appelez §a§l" + data.rpPrenom + " §2§l" + tempName + "§f, c'est bien ça ? §7(Oui/Non)");
                                    isWaitingNom = false;

                                }

                                //If player's message isn't conform to the ABC alphabet
                            } else e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Votre §ePrénom RP§c est incorect!");

                            //If player didn't pass the database check
                        } else {

                            //Inform player and reset rpPrenom
                            data.rpPrenom = null;
                            isWaitingPrenom = true;
                            tempPrenom = null;
                            e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Une personne s'appelle déja comme ça ! Veuillez entrez votre §ePrénom RP");

                        }

                        //If we are in waiting for a rpNom confirmation
                    } else {

                        //Check if player's answer contains yes options
                        if(optYes.contains(e.getMessage())) {

                            //Re check if tempPrenom isn't null, as safety
                            if (tempName != null) {

                                //Set the player's rpNom to the tempName to confirm
                                data.rpNom = tempName;

                                //Tell the player his name has been permanently set
                                e.getPlayer().sendTitle("§4§l§k|||§r §fBonjour §a§l" + data.rpPrenom + " §2§l" + data.rpNom + "§f! §4§l§k|||", "§6§k§l||§r §7Pour finir entrez votre §e§nÂge RP§r §6§k§l||", 0, 14000, 0);
                                e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §fBien le bonjour §a§l" + data.rpPrenom + " §2§l" + data.rpNom + "§f!");

                            }

                            //Check if player's answer contains no options
                        } else if(optNo.contains(e.getMessage())) {

                            //Reset wait state & tell the player to restart procedure
                            isWaitingNom = true;
                            e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cAh mince! Veulliez reécrire votre §eNom RP§c s'il vous plaît!");

                        }



                    }

                }

                //Register first name (prénom) | Check if rpPrenom, rpNom and rpAge are null
                if(data.rpPrenom == null && data.rpNom == null && data.rpAge == 0) {
                    
                    //If we are waiting for his rpPrenom variable to be set
                    if(isWaitingPrenom) {
                        
                        //Check if String follows ABC alphabet
                        if(e.getMessage().matches("[a-zA-Z]+")) {

                            //Set temp variable to his message
                            tempPrenom = e.getMessage();

                            //Check if rpPrenom is fitting the length size requirement
                            if (tempPrenom.length() >= 3 && tempPrenom.length() <= 20) {

                                //If the rpPrenom fits the length requirement, ask for confirmation and trigger it
                                e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §fVous vous appelez §a§l" + tempPrenom + "§f, c'est bien ça ? §7(Oui/Non)");
                                isWaitingPrenom = false;

                            }

                        //If player's message isn't conform to the ABC alphabet
                        } else e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Votre §ePrénom RP§c est incorect!");

                    //If we are in waiting for a rpPrenom confirmation
                    } else {

                        //Check if player's answer contains yes options
                        if(optYes.contains(e.getMessage())) {

                            //Re check if tempPrenom isn't null, as safety
                            if (tempPrenom != null) {

                                //Set the player's rpPrenom to the tempPrenom to confirm
                                data.rpPrenom = tempPrenom;

                                //Tell the player his name has been permanently set
                                e.getPlayer().sendTitle("§4§l§k|||§r §fBonjour §a§l" + data.rpPrenom + " §f! §4§l§k|||", "§6§k§l||§r §7Continuez avec votre §e§nNom RP§r §6§k§l||", 0, 14000, 0);
                                e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §fBien le bonjour §a§l" + data.rpPrenom + "§f!");

                            }

                        //Check if player's answer contains no options
                        } else if(optNo.contains(e.getMessage())) {

                            //Reset wait state & tell the player to restart procedure
                            isWaitingPrenom = true;
                            e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cAh mince! Veulliez reécrire votre §ePrénom RP§c s'il vous plaît!");

                        }

                    }

                }

                //Final registeration checks
                if(data.rpPrenom != null && data.rpNom != null && data.rpAge >= 18) {

                    //Check if all wait times are disabled
                    if(!isWaitingPrenom && !isWaitingNom && !isWaitingAge && tempPrenom != null && tempName != null && tempAge >= 18 && optYes.contains(e.getMessage())) {

                        //Set all required variables to the roleplay of the player
                        data.rpCurrentChat = 0;
                        data.rpCurrentJob = "§eCitoyen";

                        //Set player's ID, update globalServerID and update averageAge
                        data.playerID = server.globalID + 1;
                        server.averageAge.add(data.rpAge);

                        //Kick player to finalize registration
                        e.getPlayer().sendTitle("", "", 0, 0, 0);
                        String kickMessage = ("§8§m+--------------------------+" + "\n" + "§4§l§k|||§r §fBienvenue §a§l" + data.rpPrenom + " §2§l" + data.rpNom + "§f! §4§l§k|||" + "\n" + "§c§k§l||§r §7Amusez vous bien ! §c§k§l||§r" + "\n" + " " + "\n" + "§8§oVous avez été engregistré, reconectez-vous !" + "\n" + "§8§m+--------------------------+");
                        Bukkit.getScheduler().runTask(LibertyCity.INSTANCE, () -> e.getPlayer().kickPlayer(kickMessage));

                    } else e.getPlayer().kickPlayer("§cErreur: RegisterEvent.java (237) -> isWaitingPrenom=" + isWaitingPrenom + " isWaitingNom=" + isWaitingNom + " isWaitingAge=" + isWaitingAge + " tempPrenom=" + tempPrenom + "tempName=" + tempName + " tempAge=" + tempAge + " rpPrenom=" + data.rpPrenom + " rpNom=" + data.rpNom + " rpAge=" + data.rpAge);

                }

                //Register age (rpAge) | Check if rpPrenom & rpNom aren't null but rpAge is null
                if(data.rpPrenom != null && data.rpNom != null && data.rpAge == 0) {

                    //If we are waiting for his rpAge variable to be set
                    if(isWaitingAge) {

                        //Set temp variable to his message
                        tempAge = Integer.parseInt(e.getMessage());

                        //Check if String follows ABC alphabet
                        //        (NOT NEEDED)

                        //Check if rpPrenom is fitting the length size requirement
                        if(tempAge >= 18 && tempAge <= 90) {

                            //If the rpAge fits the int requirement, ask for confirmation and trigger it
                            e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §fVous vous appelez §a§l" + data.rpPrenom + " §2§l" + data.rpNom + " §fet vous avez §6" + tempAge + "ans§f, c'est bien ça ? §7(Oui/Non)");
                            isWaitingAge = false;

                        }

                    //If we are in waiting for a rpAge confirmation
                    } else {

                        //Check if player's answer contains yes options
                        if(optYes.contains(e.getMessage())) {

                            //Re check if tempAge isn't null, as safety
                            if (tempAge != 0) {

                                //Set the player's rpAge to the tempAge to confirm
                                data.rpAge = tempAge;

                                //Tell the player his name has been permanently set
                                e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §fAlors pour bien confirmé le tous, vous vous appelez §a§l" + data.rpPrenom + " §2§l" + data.rpNom + " §fet vous avez §6" + data.rpAge + "§f, correct ?");

                            }

                        //Check if player's answer contains no options
                        } else if(optNo.contains(e.getMessage())) {

                            //Reset wait state
                            isWaitingAge = true;
                            isWaitingPrenom = true;
                            isWaitingNom = true;

                            //Reset previously set variables
                            data.rpPrenom = null;
                            data.rpNom = null;
                            data.rpAge = 0;

                            //Inform player of taken actions and make him restart the process properly
                            e.getPlayer().sendTitle("§§§l§k|||§r §fBienvenue sur §2§lLiberty§a§lCity §6§lV5 §f! §4§l§k|||", "§6§k§l||§r §7CDébutez par entré votre §e§nPrénom RP§r §6§k§l||", 0, 14000, 0);
                            e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cAh mince, on va recommencé depuis le début. Veuillez entrez votre §ePrénom RP");

                        }

                    }

                }
            }
        });
    }

}
