package com.xiii.libertycity.roleplay;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.data.ServerData;
import com.xiii.libertycity.core.utils.AlertUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class CustomChat implements Listener, CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("chat")) {

            // Initialize PlayerData and return current chat if no argument is specified
            PlayerData data = Data.data.getUserData((Player) sender);
            String chatTranslation = String.valueOf(data.rpCurrentChat);
            if(args.length < 1) sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes actuellement sur le chat §6" + chatTranslation.replace("0", "HRP").replace("1", "RP").replace("2", "Police").replace("3", "Gang"));

            // If the player asks to change chat
            if(args.length == 1) {

                if(args[0].equalsIgnoreCase("hrp")) {

                    if(data.rpCurrentChat == 0) sender.sendMessage("§2§lLiberty§a§lCity §7» §cVous êtes déja sur le chat §6HRP");
                    else {
                        if(!data.chatBanHRP) {
                            data.rpCurrentChat = 0;
                            sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais sur le chat §6HRP");
                        } else {
                            if(System.currentTimeMillis() >= data.muteDuration) {
                                data.isMuted = false;
                                data.chatBanHRP = false;
                                sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous n'êtes plus muet. §7(Automatique)");
                                AlertUtil.staffAlert("§8" + sender.getName() + " §8n'est plus muet (automatique)", "LibertyCity.staff.alert", 0);
                                data.rpCurrentChat = 0;
                                sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais sur le chat §6HRP");
                            } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous êtes muet(te) du chat §6HRP§r" + "\n" + "§8§m+--------------------------+§r" + "\n" + "            §4§lSANCTION§r" + "\n" + "\n" + "§f§l⋅ §7Muet(te) le §f» §c" + data.muteDate + "\n" + "§f§l⋅ §7Muet(te) par §f» §c" + data.mutedBy + "\n" + "§f§l⋅ §7Muet(te) jusqu'au §f» §c" + data.muteDisplayDate + "\n" + "§f§l⋅ §7Raison §f» §c" + data.muteReason + "\n" + "\n" + "§f§l⋅ §7Contestations §f» §bdiscord.gg/LibertyCity" + "\n" + "§8§m+--------------------------+");
                        }
                    }

                }

                if(args[0].equalsIgnoreCase("rp")) {

                    if(data.rpCurrentChat == 1) sender.sendMessage("§2§lLiberty§a§lCity §7» §cVous êtes déja sur le chat §6RP");
                    else {
                        if(!data.chatBanRP) {
                            data.rpCurrentChat = 1;
                            sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais sur le chat §6RP");
                        } else {
                            if(System.currentTimeMillis() >= data.muteDuration) {
                                data.isMuted = false;
                                data.chatBanRP = false;
                                sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous n'êtes plus muet. §7(Automatique)");
                                AlertUtil.staffAlert("§8" + sender.getName() + " §8n'est plus muet (automatique)", "LibertyCity.staff.alert", 0);
                                data.rpCurrentChat = 1;
                                sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais sur le chat §6RP");
                            } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous êtes muet(te) du chat §6RP§r" + "\n" + "§8§m+--------------------------+§r" + "\n" + "            §4§lSANCTION§r" + "\n" + "\n" + "§f§l⋅ §7Muet(te) le §f» §c" + data.muteDate + "\n" + "§f§l⋅ §7Muet(te) par §f» §c" + data.mutedBy + "\n" + "§f§l⋅ §7Muet(te) jusqu'au §f» §c" + data.muteDisplayDate + "\n" + "§f§l⋅ §7Raison §f» §c" + data.muteReason + "\n" + "\n" + "§f§l⋅ §7Contestations §f» §bdiscord.gg/LibertyCity" + "\n" + "§8§m+--------------------------+");
                        }
                    }

                }

                if(args[0].equalsIgnoreCase("police")) {
                    if(sender.hasPermission("LibertyCity.chat.police")) {
                        if (data.rpCurrentChat == 2)
                            sender.sendMessage("§2§lLiberty§a§lCity §7» §cVous êtes déja sur le chat §6Police");
                        else {
                            if (!data.chatBanPolice) {
                                data.rpCurrentChat = 2;
                                sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais sur le chat §6Police");
                            } else {
                                if (System.currentTimeMillis() >= data.muteDuration) {
                                    data.isMuted = false;
                                    data.chatBanPolice = false;
                                    sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous n'êtes plus muet. §7(Automatique)");
                                    AlertUtil.staffAlert("§8" + sender.getName() + " §8n'est plus muet (automatique)", "LibertyCity.staff.alert", 0);
                                    data.rpCurrentChat = 2;
                                    sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais sur le chat §6Police");
                                } else
                                    sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous êtes muet(te) du chat §6Police§r" + "\n" + "§8§m+--------------------------+§r" + "\n" + "            §4§lSANCTION§r" + "\n" + "\n" + "§f§l⋅ §7Muet(te) le §f» §c" + data.muteDate + "\n" + "§f§l⋅ §7Muet(te) par §f» §c" + data.mutedBy + "\n" + "§f§l⋅ §7Muet(te) jusqu'au §f» §c" + data.muteDisplayDate + "\n" + "§f§l⋅ §7Raison §f» §c" + data.muteReason + "\n" + "\n" + "§f§l⋅ §7Contestations §f» §bdiscord.gg/LibertyCity" + "\n" + "§8§m+--------------------------+");
                            }
                        }
                    } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cVous n'avez pas la permission requise!");

                }

                if(args[0].equalsIgnoreCase("gang")) {
                    if(sender.hasPermission("LibertyCity.chat.gang")) {
                        if (data.rpCurrentChat == 3)
                            sender.sendMessage("§2§lLiberty§a§lCity §7» §cVous êtes déja sur le chat §6Gang");
                        else {
                            if (!data.chatBanGang) {
                                data.rpCurrentChat = 3;
                                sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais sur le chat §6Gang");
                            } else {
                                if (System.currentTimeMillis() >= data.muteDuration) {
                                    data.isMuted = false;
                                    data.chatBanGang = false;
                                    sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous n'êtes plus muet. §7(Automatique)");
                                    AlertUtil.staffAlert("§8" + sender.getName() + " §8n'est plus muet (automatique)", "LibertyCity.staff.alert", 0);
                                    data.rpCurrentChat = 3;
                                    sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais sur le chat §6Gang");
                                } else
                                    sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous êtes muet(te) du chat §6Gang§r" + "\n" + "§8§m+--------------------------+§r" + "\n" + "            §4§lSANCTION§r" + "\n" + "\n" + "§f§l⋅ §7Muet(te) le §f» §c" + data.muteDate + "\n" + "§f§l⋅ §7Muet(te) par §f» §c" + data.mutedBy + "\n" + "§f§l⋅ §7Muet(te) jusqu'au §f» §c" + data.muteDisplayDate + "\n" + "§f§l⋅ §7Raison §f» §c" + data.muteReason + "\n" + "\n" + "§f§l⋅ §7Contestations §f» §bdiscord.gg/LibertyCity" + "\n" + "§8§m+--------------------------+");
                            }
                        }
                    } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cVous n'avez pas la permission requise!");

                }

            }

            if(args.length > 1) sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! Usage: /chat (RP,HRP,Police,Gang)");

        }

        return true;
    }

    @EventHandler
    public void forCustomChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {

            // Initialize PlayerData & ServerData
            PlayerData data = Data.data.getUserData(e.getPlayer());
            ServerData server = Data.data.getServerData(Bukkit.getServer());

            // Prevent spam :pray: + null safety
            if(data.playerID > 0) {
                if (((System.currentTimeMillis() - data.lastChat >= server.chatCooldownGlobal)) || e.getPlayer().hasPermission("LibertyCity.bypass.chatcooldown")) {

                    // Even if this isn't possible, its just safety.
                    if (data.rpCurrentChat < 0 || data.rpCurrentChat > 3) data.rpCurrentChat = 0;

                    if (data.rpCurrentChat == 0) {

                        if (!data.chatBanHRP && !data.isMuted) {

                            for (Player p : Bukkit.getOnlinePlayers()) {

                                // Initialize the looped player's PlayerData
                                PlayerData pData = Data.data.getUserData(p);

                                // Send message under specified format
                                if (pData.rpCurrentChat == 0)
                                    p.sendMessage("§7(§3§LHRP§7) §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §8| §e" + p.getName() + " §7» §f" + e.getMessage());
                                if (pData.spyChatHRP || pData.spyChatGlobal)
                                    p.sendMessage("§c§l[CS] §7(§3§LHRP§7) §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §8| §e" + p.getName() + " §7» §f" + e.getMessage());
                                Bukkit.getConsoleSender().sendMessage("§c§l[CS] §7(§3§LHRP§7) §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §8| §e" + p.getName() + " §7» §f" + e.getMessage());
                            }
                        }
                        if (data.chatBanHRP || data.isMuted) {
                            if (System.currentTimeMillis() >= data.muteDuration) {
                                data.isMuted = false;
                                data.chatBanHRP = false;
                                e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §fVous n'êtes plus muet. §7(Automatique)");
                                AlertUtil.staffAlert("§8" + e.getPlayer().getName() + " §8n'est plus muet (automatique)", "LibertyCity.staff.alert", 0);
                                for (Player p : Bukkit.getOnlinePlayers()) {

                                    // Initialize the looped player's PlayerData
                                    PlayerData pData = Data.data.getUserData(p);

                                    // Send message under specified format
                                    if (pData.rpCurrentChat == 0)
                                        p.sendMessage("§7(§3§LHRP§7) §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §8| §e" + p.getName() + " §7» §f" + e.getMessage());
                                    if (pData.spyChatHRP || pData.spyChatGlobal)
                                        p.sendMessage("§c§l[CS] §7(§3§LHRP§7) §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §8| §e" + p.getName() + " §7» §f" + e.getMessage());
                                    Bukkit.getConsoleSender().sendMessage("§c§l[CS] §7(§3§LHRP§7) §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §8| §e" + p.getName() + " §7» §f" + e.getMessage());
                                }
                            } else
                                e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous êtes muet(te) du chat §6HRP§r" + "\n" + "§8§m+--------------------------+§r" + "\n" + "            §4§lSANCTION§r" + "\n" + "\n" + "§f§l⋅ §7Muet(te) le §f» §c" + data.muteDate + "\n" + "§f§l⋅ §7Muet(te) par §f» §c" + data.mutedBy + "\n" + "§f§l⋅ §7Muet(te) jusqu'au §f» §c" + data.muteDisplayDate + "\n" + "§f§l⋅ §7Raison §f» §c" + data.muteReason + "\n" + "\n" + "§f§l⋅ §7Contestations §f» §bdiscord.gg/LibertyCity" + "\n" + "§8§m+--------------------------+");
                        }

                    }

                    if (data.rpCurrentChat == 1) {

                        if (!data.chatBanRP && !data.isMuted) {

                            for (Player p : Bukkit.getOnlinePlayers()) {

                                // Initialize the looped player's PlayerData
                                PlayerData pData = Data.data.getUserData(p);

                                // Send message under specified format
                                if (pData.rpCurrentChat == 1)
                                    p.sendMessage("§7(§2§LRP§7) §f" + data.rpCurrentJob + " §8| §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §7» §f" + e.getMessage());
                                if (pData.spyChatRP || pData.spyChatGlobal)
                                    p.sendMessage("§c§l[CS] §7(§2§LRP§7) §f" + data.rpCurrentJob + " §8| §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §7» §f" + e.getMessage());
                                Bukkit.getConsoleSender().sendMessage("§c§l[CS] §7(§2§LRP§7) §f" + data.rpCurrentJob + " §8| §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §7» §f" + e.getMessage());
                            }
                        }
                        if (data.chatBanRP || data.isMuted) {
                            if (System.currentTimeMillis() >= data.muteDuration) {
                                data.isMuted = false;
                                data.chatBanRP = false;
                                e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §fVous n'êtes plus muet. §7(Automatique)");
                                AlertUtil.staffAlert("§8" + e.getPlayer().getName() + " §8n'est plus muet (automatique)", "LibertyCity.staff.alert", 0);
                                for (Player p : Bukkit.getOnlinePlayers()) {

                                    // Initialize the looped player's PlayerData
                                    PlayerData pData = Data.data.getUserData(p);

                                    // Send message under specified format
                                    if (pData.rpCurrentChat == 1)
                                        p.sendMessage("§7(§2§LRP§7) §f" + data.rpCurrentJob + " §8| §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §7» §f" + e.getMessage());
                                    if (pData.spyChatRP || pData.spyChatGlobal)
                                        p.sendMessage("§c§l[CS] §7(§2§LRP§7) §f" + data.rpCurrentJob + " §8| §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §7» §f" + e.getMessage());
                                    Bukkit.getConsoleSender().sendMessage("§c§l[CS] §7(§2§LRP§7) §f" + data.rpCurrentJob + " §8| §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §7» §f" + e.getMessage());
                                }
                            } else
                                e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous êtes muet(te) du chat §6RP§r" + "\n" + "§8§m+--------------------------+§r" + "\n" + "            §4§lSANCTION§r" + "\n" + "\n" + "§f§l⋅ §7Muet(te) le §f» §c" + data.muteDate + "\n" + "§f§l⋅ §7Muet(te) par §f» §c" + data.mutedBy + "\n" + "§f§l⋅ §7Muet(te) jusqu'au §f» §c" + data.muteDisplayDate + "\n" + "§f§l⋅ §7Raison §f» §c" + data.muteReason + "\n" + "\n" + "§f§l⋅ §7Contestations §f» §bdiscord.gg/LibertyCity" + "\n" + "§8§m+--------------------------+");
                        }

                    }

                    if (data.rpCurrentChat == 2) {

                        if (!data.chatBanPolice && !data.isMuted) {

                            for (Player p : Bukkit.getOnlinePlayers()) {

                                // Initialize the looped player's PlayerData
                                PlayerData pData = Data.data.getUserData(p);

                                // Send message under specified format
                                if (pData.rpCurrentChat == 2)
                                    p.sendMessage("§7(§b§LPolice§7) §f" + data.rpPoliceRank + " §8| §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §7» §f" + e.getMessage());
                                if (pData.spyChatPolice || pData.spyChatGlobal)
                                    p.sendMessage("§c§l[CS] §7(§b§LPolice§7) §f" + data.rpPoliceRank + " §8| §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §7» §f" + e.getMessage());
                                Bukkit.getConsoleSender().sendMessage("§c§l[CS] §7(§b§LPolice§7) §f" + data.rpPoliceRank + " §8| §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §7» §f" + e.getMessage());
                            }
                        }
                        if (data.chatBanPolice || data.isMuted) {
                            if (System.currentTimeMillis() >= data.muteDuration) {
                                data.isMuted = false;
                                data.chatBanPolice = false;
                                e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §fVous n'êtes plus muet. §7(Automatique)");
                                AlertUtil.staffAlert("§8" + e.getPlayer().getName() + " §8n'est plus muet (automatique)", "LibertyCity.staff.alert", 0);
                                for (Player p : Bukkit.getOnlinePlayers()) {

                                    // Initialize the looped player's PlayerData
                                    PlayerData pData = Data.data.getUserData(p);

                                    // Send message under specified format
                                    if (pData.rpCurrentChat == 2)
                                        p.sendMessage("§7(§b§LPolice§7) §f" + data.rpPoliceRank + " §8| §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §7» §f" + e.getMessage());
                                    if (pData.spyChatPolice || pData.spyChatGlobal)
                                        p.sendMessage("§c§l[CS] §7(§b§LPolice§7) §f" + data.rpPoliceRank + " §8| §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §7» §f" + e.getMessage());
                                    Bukkit.getConsoleSender().sendMessage("§c§l[CS] §7(§b§LPolice§7) §f" + data.rpPoliceRank + " §8| §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §7» §f" + e.getMessage());
                                }
                            } else
                                e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous êtes muet(te) du chat §6Police§r" + "\n" + "§8§m+--------------------------+§r" + "\n" + "            §4§lSANCTION§r" + "\n" + "\n" + "§f§l⋅ §7Muet(te) le §f» §c" + data.muteDate + "\n" + "§f§l⋅ §7Muet(te) par §f» §c" + data.mutedBy + "\n" + "§f§l⋅ §7Muet(te) jusqu'au §f» §c" + data.muteDisplayDate + "\n" + "§f§l⋅ §7Raison §f» §c" + data.muteReason + "\n" + "\n" + "§f§l⋅ §7Contestations §f» §bdiscord.gg/LibertyCity" + "\n" + "§8§m+--------------------------+");
                        }

                    }

                    if (data.rpCurrentChat == 3) {

                        if (!data.chatBanGang && !data.isMuted) {

                            for (Player p : Bukkit.getOnlinePlayers()) {

                                // Initialize the looped player's PlayerData
                                PlayerData pData = Data.data.getUserData(p);

                                // Send message under specified format
                                if (pData.rpCurrentChat == 3)
                                    p.sendMessage("§7(§4§LGang§7) §f" + data.rpGangRank + " §8| §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §7» §f" + e.getMessage());
                                if (pData.spyChatGang || pData.spyChatGlobal)
                                    p.sendMessage("§c§l[CS] §7(§4§LGang§7) §f" + data.rpGangRank + " §8| §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §7» §f" + e.getMessage());
                                Bukkit.getConsoleSender().sendMessage("§c§l[CS] §7(§4§LGang§7) §f" + data.rpGangRank + " §8| §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §7» §f" + e.getMessage());
                            }
                        }
                        if (data.chatBanGang || data.isMuted) {
                            if (System.currentTimeMillis() >= data.muteDuration) {
                                data.isMuted = false;
                                data.chatBanGang = false;
                                e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §fVous n'êtes plus muet. §7(Automatique)");
                                AlertUtil.staffAlert("§8" + e.getPlayer().getName() + " §8n'est plus muet (automatique)", "LibertyCity.staff.alert", 0);
                                for (Player p : Bukkit.getOnlinePlayers()) {

                                    // Initialize the looped player's PlayerData
                                    PlayerData pData = Data.data.getUserData(p);

                                    // Send message under specified format
                                    if (pData.rpCurrentChat == 3)
                                        p.sendMessage("§7(§4§LGang§7) §f" + data.rpGangRank + " §8| §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §7» §f" + e.getMessage());
                                    if (pData.spyChatGang || pData.spyChatGlobal)
                                        p.sendMessage("§c§l[CS] §7(§4§LGang§7) §f" + data.rpGangRank + " §8| §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §7» §f" + e.getMessage());
                                    Bukkit.getConsoleSender().sendMessage("§c§l[CS] §7(§4§LGang§7) §f" + data.rpGangRank + " §8| §A§L" + data.rpPrenom + " §2§L" + data.rpNom + " §7» §f" + e.getMessage());
                                }
                            } else
                                e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous êtes muet(te) du chat §6Gang§r" + "\n" + "§8§m+--------------------------+§r" + "\n" + "            §4§lSANCTION§r" + "\n" + "\n" + "§f§l⋅ §7Muet(te) le §f» §c" + data.muteDate + "\n" + "§f§l⋅ §7Muet(te) par §f» §c" + data.mutedBy + "\n" + "§f§l⋅ §7Muet(te) jusqu'au §f» §c" + data.muteDisplayDate + "\n" + "§f§l⋅ §7Raison §f» §c" + data.muteReason + "\n" + "\n" + "§f§l⋅ §7Contestations §f» §bdiscord.gg/LibertyCity" + "\n" + "§8§m+--------------------------+");
                        }

                    }
                } else e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cVeuillez patientez entre chaque messages.");
                data.lastChat = System.currentTimeMillis();
            }

        });
    }

}
