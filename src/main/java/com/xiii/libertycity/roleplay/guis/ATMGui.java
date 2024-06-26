package com.xiii.libertycity.roleplay.guis;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.utils.ConvertUtils;
import com.xiii.libertycity.core.utils.InventoryUtils;
import com.xiii.libertycity.core.utils.MoneyUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.xiii.libertycity.core.utils.InventoryUtils.createGuiItem;

public class ATMGui implements Listener, CommandExecutor {

    // TODO: Change to proper bills IDs while keeping a copy of those for DEV.
    private final Material oneBill = Material.getMaterial(4306);
    private final Material twoBill = Material.getMaterial(4307);
    private final Material fiveBill = Material.getMaterial(4308);
    private final Material tenBill = Material.getMaterial(4309);
    private final Material twentyBill = Material.getMaterial(4310);
    private final Material fiftyBill = Material.getMaterial(4311);
    private final Material oneHundredBill = Material.getMaterial(4312);
    private final Material twoHundredBill = Material.getMaterial(4313);
    private final Material fiveHundredBill = Material.getMaterial(4314);
    private final Material oneThousandBill = Material.getMaterial(4315);
    private final Material twoThousandBill = Material.getMaterial(4316);
    private final Material fiveThousandBill = Material.getMaterial(4317);

    public void ATM(Player p) {
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {

            PlayerData data = Data.data.getUserData(p);

            p.closeInventory();
            Inventory ATM = Bukkit.createInventory(p.getPlayer(), 27, "                 §2§lATM");
            ItemStack glass = (createGuiItem(Material.STAINED_GLASS_PANE, " ", " "));
            ItemStack deposit = (createGuiItem(tenBill, "§aDéposer de l'argent", "", "§7§oVotre §n§6§oargent§r§7§o sera ajouté de votre §n§6§obanque§r§7§o §7§o!", "§7Vous avez §6$" + data.rpBank + " §7(" + ConvertUtils.format(data.rpBank) + "§7) (Banque)"));
            ItemStack close = (createGuiItem(Material.BARRIER, "§cFermer le menu", " ", "§7Cliquer pour fermer le menu."));
            ItemStack withdraw = (createGuiItem(fiveBill, "§cRetiré de l'argent", "", "§7§oL'§6§o§nargent§r§7§o sera retiré de votre §6§o§nbanque§r§7§o !", "§7Vous avez §6$" + data.rpBank + " §7(" + ConvertUtils.format(data.rpBank) + "§7) (Banque)"));

            for(int i = 0; i < 27; i++) {
                ATM.setItem(i,glass);
            }

            ATM.setItem(11, deposit);
            ATM.setItem(13, close);
            ATM.setItem(15, withdraw);

            p.getPlayer().openInventory(ATM);

        });
    }

    public void ATMDeposit(Player p) {
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {

            p.closeInventory();
            Inventory ATMDeposit = Bukkit.createInventory(p.getPlayer(), 36, "                 §a§lATM §7(D)");
            ItemStack glass = (createGuiItem(Material.STAINED_GLASS_PANE, " ", " "));
            ItemStack close = (createGuiItem(Material.ARROW, "§cRetourner a l'ATM", " ", "§7Cliqué(e) pour retourner au menu de l'ATM."));
            ItemStack oneDollarBill = (createGuiItem(oneBill, "§aDéposer 1$", " ", "§7Vous avez §6" + InventoryUtils.getAmount(p.getPlayer(), new ItemStack(oneBill)) + " §7billet(s) de §e1$", " ", "§7Clique §e§ndroit§7 pour tous déposer.", "§7Clique §e§ngauche§7 pour déposer 1 par 1."));
            ItemStack twoDollarBill = (createGuiItem(twoBill, "§aDéposer 2$", " ", "§7Vous avez §6" + InventoryUtils.getAmount(p.getPlayer(), new ItemStack(twoBill)) + " §7billet(s) de §e2$", " ", "§7Clique §e§ndroit§7 pour tous déposer.", "§7Clique §e§ngauche§7 pour déposer 1 par 1."));
            ItemStack fiveDollarBill = (createGuiItem(fiveBill, "§aDéposer 5$", " ", "§7Vous avez §6" + InventoryUtils.getAmount(p.getPlayer(), new ItemStack(fiveBill)) + " §7billet(s) de §e5$", " ", "§7Clique §e§ndroit§7 pour tous déposer.", "§7Clique §e§ngauche§7 pour déposer 1 par 1."));
            ItemStack tenDollarBill = (createGuiItem(tenBill, "§aDéposer 10$", " ", "§7Vous avez §6" + InventoryUtils.getAmount(p.getPlayer(), new ItemStack(tenBill)) + " §7billet(s) de §e10$", " ", "§7Clique §e§ndroit§7 pour tous déposer.", "§7Clique §e§ngauche§7 pour déposer 1 par 1."));
            ItemStack twentyDollarBill = (createGuiItem(twentyBill, "§aDéposer 20$", " ", "§7Vous avez §6" + InventoryUtils.getAmount(p.getPlayer(), new ItemStack(twentyBill)) + " §7billet(s) de §e20$", " ", "§7Clique §e§ndroit§7 pour tous déposer.", "§7Clique §e§ngauche§7 pour déposer 1 par 1."));
            ItemStack fiftyDollarBill = (createGuiItem(fiftyBill, "§aDéposer 50$", " ", "§7Vous avez §6" + InventoryUtils.getAmount(p.getPlayer(), new ItemStack(fiftyBill)) + " §7billet(s) de §e50$", " ", "§7Clique §e§ndroit§7 pour tous déposer.", "§7Clique §e§ngauche§7 pour déposer 1 par 1."));
            ItemStack oneHundredDollarBill = (createGuiItem(oneHundredBill, "§aDéposer 100$", " ", "§7Vous avez §6" + InventoryUtils.getAmount(p.getPlayer(), new ItemStack(oneHundredBill)) + " §7billet(s) de §e100$", " ", "§7Clique §e§ndroit§7 pour tous déposer.", "§7Clique §e§ngauche§7 pour déposer 1 par 1."));
            ItemStack twoHundredDollarBill = (createGuiItem(twoHundredBill, "§aDéposer 200$", " ", "§7Vous avez §6" + InventoryUtils.getAmount(p.getPlayer(), new ItemStack(twoHundredBill)) + " §7billet(s) de §e200$", " ", "§7Clique §e§ndroit§7 pour tous déposer.", "§7Clique §e§ngauche§7 pour déposer 1 par 1."));
            ItemStack fiveHundredDollarBill = (createGuiItem(fiveHundredBill, "§aDéposer 500$", " ", "§7Vous avez §6" + InventoryUtils.getAmount(p.getPlayer(), new ItemStack(fiveHundredBill)) + " §7billet(s) de §e500$", " ", "§7Clique §e§ndroit§7 pour tous déposer.", "§7Clique §e§ngauche§7 pour déposer 1 par 1."));
            ItemStack oneThousandDollarBill = (createGuiItem(oneThousandBill, "§aDéposer 1000$", " ", "§7Vous avez §6" + InventoryUtils.getAmount(p.getPlayer(), new ItemStack(oneThousandBill)) + " §7billet(s) de §e1000$", " ", "§7Clique §e§ndroit§7 pour tous déposer.", "§7Clique §e§ngauche§7 pour déposer 1 par 1."));
            ItemStack twoThousandDollarBill = (createGuiItem(twoThousandBill, "§aDéposer 2000$", " ", "§7Vous avez §6" + InventoryUtils.getAmount(p.getPlayer(), new ItemStack(twoThousandBill)) + " §7billet(s) de §e2000$", " ", "§7Clique §e§ndroit§7 pour tous déposer.", "§7Clique §e§ngauche§7 pour déposer 1 par 1."));
            ItemStack fiveThousandDollarBill = (createGuiItem(fiveThousandBill, "§aDéposer 5000$", " ", "§7Vous avez §6" + InventoryUtils.getAmount(p.getPlayer(), new ItemStack(fiveThousandBill)) + " §7billet(s) de §e5000$", " ", "§7Clique §e§ndroit§7 pour tous déposer.", "§7Clique §e§ngauche§7 pour déposer 1 par 1."));

            for(int i = 0; i < 36; i++) {
                ATMDeposit.setItem(i,glass);
            }
            ATMDeposit.setItem(35, close);

            ATMDeposit.setItem(10, oneDollarBill);
            ATMDeposit.setItem(11, twoDollarBill);
            ATMDeposit.setItem(12, fiveDollarBill);
            ATMDeposit.setItem(13, tenDollarBill);
            ATMDeposit.setItem(14, twentyDollarBill);
            ATMDeposit.setItem(15, fiftyDollarBill);
            ATMDeposit.setItem(16, oneHundredDollarBill);
            ATMDeposit.setItem(20, twoHundredDollarBill);
            ATMDeposit.setItem(21, fiveHundredDollarBill);
            ATMDeposit.setItem(22, oneThousandDollarBill);
            ATMDeposit.setItem(23, twoThousandDollarBill);
            ATMDeposit.setItem(24, fiveThousandDollarBill);

            p.getPlayer().openInventory(ATMDeposit);

        });

    }

    public void ATMWithdraw(Player p) {
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {

            PlayerData data = Data.data.getUserData(p);

            p.closeInventory();
            Inventory ATMWithdraw = Bukkit.createInventory(p.getPlayer(), 36, "                 §c§lATM §7(W)");
            ItemStack glass = (createGuiItem(Material.STAINED_GLASS_PANE, " ", " "));
            ItemStack close = (createGuiItem(Material.ARROW, "§cRetourner a l'ATM", " ", "§7Cliqué(e) pour retourner au menu de l'ATM."));
            ItemStack oneDollarBill = (createGuiItem(oneBill, "§cRetirer 1$", " ", "§7Vous avez §6$" + data.rpBank + " §7(" + ConvertUtils.format(data.rpBank) + "§7) en banque", " ", "§7Clique §e§ndroit§7 pour tous retirer.", "§7Clique §e§ngauche§7 pour retirer 1 par 1."));
            ItemStack twoDollarBill = (createGuiItem(twoBill, "§cRetirer 2$", " ", "§7Vous avez §6$" + data.rpBank + " §7(" + ConvertUtils.format(data.rpBank) + "§7) en banque", " ", "§7Clique §e§ndroit§7 pour tous retirer.", "§7Clique §e§ngauche§7 pour retirer 1 par 1."));
            ItemStack fiveDollarBill = (createGuiItem(fiveBill, "§cRetirer 5$", " ", "§7Vous avez §6$" + data.rpBank + " §7(" + ConvertUtils.format(data.rpBank) + "§7) en banque", " ", "§7Clique §e§ndroit§7 pour tous retirer.", "§7Clique §e§ngauche§7 pour retirer 1 par 1."));
            ItemStack tenDollarBill = (createGuiItem(tenBill, "§cRetirer 10$", " ", "§7Vous avez §6$" + data.rpBank + " §7(" + ConvertUtils.format(data.rpBank) + "§7) en banque", " ", "§7Clique §e§ndroit§7 pour tous retirer.", "§7Clique §e§ngauche§7 pour retirer 1 par 1."));
            ItemStack twentyDollarBill = (createGuiItem(twentyBill, "§cRetirer 20$", " ", "§7Vous avez §6$" + data.rpBank + " §7(" + ConvertUtils.format(data.rpBank) + "§7) en banque", " ", "§7Clique §e§ndroit§7 pour tous retirer.", "§7Clique §e§ngauche§7 pour retirer 1 par 1."));
            ItemStack fiftyDollarBill = (createGuiItem(fiftyBill, "§cRetirer 50$", " ", "§7Vous avez §6$" + data.rpBank + " §7(" + ConvertUtils.format(data.rpBank) + "§7) en banque", " ", "§7Clique §e§ndroit§7 pour tous retirer.", "§7Clique §e§ngauche§7 pour retirer 1 par 1."));
            ItemStack oneHundredDollarBill = (createGuiItem(oneHundredBill, "§cRetirer 100$", " ", "§7Vous avez §6$" + data.rpBank + " §7(" + ConvertUtils.format(data.rpBank) + "§7) en banque", " ", "§7Clique §e§ndroit§7 pour tous retirer.", "§7Clique §e§ngauche§7 pour retirer 1 par 1."));
            ItemStack twoHundredDollarBill = (createGuiItem(twoHundredBill, "§cRetirer 200$", " ", "§7Vous avez §6$" + data.rpBank + " §7(" + ConvertUtils.format(data.rpBank) + "§7) en banque", " ", "§7Clique §e§ndroit§7 pour tous retirer.", "§7Clique §e§ngauche§7 pour retirer 1 par 1."));
            ItemStack fiveHundredDollarBill = (createGuiItem(fiveHundredBill, "§cRetirer 500$", " ", "§7Vous avez §6$" + data.rpBank + " §7(" + ConvertUtils.format(data.rpBank) + "§7) en banque", " ", "§7Clique §e§ndroit§7 pour tous retirer.", "§7Clique §e§ngauche§7 pour retirer 1 par 1."));
            ItemStack oneThousandDollarBill = (createGuiItem(oneThousandBill, "§cRetirer 1000$", " ", "§7Vous avez §6$" + data.rpBank + " §7(" + ConvertUtils.format(data.rpBank) + "§7) en banque", " ", "§7Clique §e§ndroit§7 pour tous retirer.", "§7Clique §e§ngauche§7 pour retirer 1 par 1."));
            ItemStack twoThousandDollarBill = (createGuiItem(twoThousandBill, "§cRetirer 2000$", " ", "§7Vous avez §6$" + data.rpBank + " §7(" + ConvertUtils.format(data.rpBank) + "§7) en banque", " ", "§7Clique §e§ndroit§7 pour tous retirer.", "§7Clique §e§ngauche§7 pour retirer 1 par 1."));
            ItemStack fiveThousandDollarBill = (createGuiItem(fiveThousandBill, "§cRetirer 5000$", " ", "§7Vous avez §6$" + data.rpBank + " §7(" + ConvertUtils.format(data.rpBank) + "§7) en banque", " ", "§7Clique §e§ndroit§7 pour tous retirer.", "§7Clique §e§ngauche§7 pour retirer 1 par 1."));

            for(int i = 0; i < 36; i++) {
                ATMWithdraw.setItem(i,glass);
            }
            ATMWithdraw.setItem(35, close);

            ATMWithdraw.setItem(10, oneDollarBill);
            ATMWithdraw.setItem(11, twoDollarBill);
            ATMWithdraw.setItem(12, fiveDollarBill);
            ATMWithdraw.setItem(13, tenDollarBill);
            ATMWithdraw.setItem(14, twentyDollarBill);
            ATMWithdraw.setItem(15, fiftyDollarBill);
            ATMWithdraw.setItem(16, oneHundredDollarBill);
            ATMWithdraw.setItem(20, twoHundredDollarBill);
            ATMWithdraw.setItem(21, fiveHundredDollarBill);
            ATMWithdraw.setItem(22, oneThousandDollarBill);
            ATMWithdraw.setItem(23, twoThousandDollarBill);
            ATMWithdraw.setItem(24, fiveThousandDollarBill);

            p.getPlayer().openInventory(ATMWithdraw);

        });
    }

    int depMoney = 0;
    int whdMoney = 0;
    int amount = 0;

    @EventHandler
    public void onATMClick(InventoryClickEvent e) {

        //Cancel inventory clicks if the container's name is part of the ATM system
        if(e.getClickedInventory() != null && e.getClickedInventory().getName() != null) {
            if (e.getClickedInventory().getName().contains("ATM")) e.setCancelled(true);

            //Run async
            Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {

                //Get Data & Player
                PlayerData data = Data.data.getUserData((Player) e.getWhoClicked());
                Player p = (Player) e.getWhoClicked();

                //Main ATM
                if (e.getClickedInventory().getName().equalsIgnoreCase("                 §2§lATM")) {
                    if (e.getRawSlot() == 11) ATMDeposit(p);
                    if (e.getRawSlot() == 13) p.closeInventory();
                    if (e.getRawSlot() == 15) ATMWithdraw(p);
                }

                //Deposit ATM
                if (e.getClickedInventory().getName().equalsIgnoreCase("                 §a§lATM §7(D)")) {

                    //To save time & performance check if the return item is clicked now
                    if (e.getRawSlot() == 35) ATM(p);

                    //Get clicked item as Material and ItemStack
                    Material item = e.getCurrentItem().getType();
                    ItemStack im = new ItemStack(item);

                    //Get Item amount in player's inventory & the money he wants to deposit
                    amount = InventoryUtils.getAmount(p, im);
                    String s = e.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§aDéposer ", "");
                    String newS = s.replaceAll("\\$", "");
                    int money = Integer.parseInt(newS);

                    //If clicked slot is any of the ATM's items
                    if (e.getRawSlot() == 10 || e.getRawSlot() == 11 || e.getRawSlot() == 12 || e.getRawSlot() == 13 || e.getRawSlot() == 14 || e.getRawSlot() == 15 || e.getRawSlot() == 16 || e.getRawSlot() == 20 || e.getRawSlot() == 21 || e.getRawSlot() == 22 || e.getRawSlot() == 23 || e.getRawSlot() == 24) {

                        //Check if there's items earlier to save time & performance
                        if (amount > 0) {

                            //If its to deposit everything
                            if (e.isRightClick()) {

                                //Remove all items from player's inventory
                                e.getWhoClicked().getInventory().remove(item);

                                //Add amount of bills * the value of the bill to the player's bank AKA deposit money
                                MoneyUtils.addMoney(p,amount * money);

                                //Tell how much money the player deposited when exiting the ATM
                                depMoney += (amount * money);

                                //If its to deposite 1 by 1
                            } else if (e.isLeftClick()) {

                                //If there's only 1 item, use default remove all method
                                if (amount == 1) e.getWhoClicked().getInventory().remove(item);

                                    //If there's more than 1 item, remove only 1 from player's inventory
                                else if (amount > 1) InventoryUtils.removeOne(p.getInventory(), new ItemStack(item));

                                //Add the value of the bill to the player's bank AKA deposit money
                                MoneyUtils.addMoney(p, money);

                                //Tell how much money the player deposited when exiting the ATM
                                depMoney += money;

                            }

                            //If the player doesn't have any bills in his inventory
                        } else
                            e.getWhoClicked().sendMessage("§2§lLiberty§a§lCity §7» §cAttention! Vous n'avez pas assez de billets!");
                    }
                }

                //Withdraw ATM
                if (e.getClickedInventory().getName().equalsIgnoreCase("                 §c§lATM §7(W)")) {

                    //To save time & performance check if the return item is clicked
                    if (e.getRawSlot() == 35) ATM(p);

                    //Get clicked item as Material & ItemStack
                    Material item = e.getCurrentItem().getType();
                    ItemStack im = new ItemStack(item);

                    //Get the money the player wants to withdraw
                    String s = e.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§cRetirer ", "");
                    String newS = s.replaceAll("\\$", "");
                    int money = Integer.parseInt(newS);

                    //If clicked slot is any of the ATM's items
                    if (e.getRawSlot() == 10 || e.getRawSlot() == 11 || e.getRawSlot() == 12 || e.getRawSlot() == 13 || e.getRawSlot() == 14 || e.getRawSlot() == 15 || e.getRawSlot() == 16 || e.getRawSlot() == 20 || e.getRawSlot() == 21 || e.getRawSlot() == 22 || e.getRawSlot() == 23 || e.getRawSlot() == 24) {

                        //If its to withdraw 8 by 8
                        if (e.isRightClick()) {

                            //If the player has enough money to withdraw
                            if (MoneyUtils.removeMoney(p, money * 8, true)) {

                                //Set new variable as the clicked item with the amount of bills to withdraw
                                ItemStack imA = new ItemStack(e.getCurrentItem().getType(), 8);

                                //If player has enough space in his inventory
                                if (InventoryUtils.canStore(p, imA)) {

                                    //Tell how much money the player withdrew when exiting the ATM
                                    whdMoney += (money * 8);

                                    //Add withdrew bills to the player's inventory
                                    e.getWhoClicked().getInventory().addItem(imA);

                                    //If player doesn't have enough space in his inventory
                                } else
                                    e.getWhoClicked().sendMessage("§2§lLiberty§a§lCity §7» §cAttention! Vous n'avez pas assez de place dans votre inventaire!");
                                //If player doesn't have enough money in his bank account
                            } else
                                e.getWhoClicked().sendMessage("§2§lLiberty§a§lCity §7» §cAttention! Vous n'avez pas assez d'argent!");

                            //If its to withdraw 1 by 1
                        } else if (e.isLeftClick()) {

                            //If player has enough money to withdraw
                            if (MoneyUtils.removeMoney(p, money, true)) {

                                //Tell how much money the player withdrew when exiting the ATM
                                whdMoney += money;

                                //Add withdrew bill to the player's inventory
                                e.getWhoClicked().getInventory().addItem(im);

                                //If player doesn't have enough money in his bank account
                            } else
                                e.getWhoClicked().sendMessage("§2§lLiberty§a§lCity §7» §cAttention! Vous n'avez pas assez d'argent!");
                        }
                    }
                }

            });
        }
    }

    @EventHandler
    public void onATMClose(InventoryCloseEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {

            //Tell how much money the player withdrew/deposited when exiting the ATM
            if(depMoney > 0) e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §fVous avez §a§ndéposé§r §6" + depMoney + "§6$");
            if(whdMoney > 0) e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §fVous avez §c§nretiré§r §6" + whdMoney + "§6$");

            //Reset withdraw & deposit counts
            depMoney = 0;
            whdMoney = 0;

        });
    }

    @EventHandler
    public void forATMOpen(PlayerInteractEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {

            // TODO: Change ID to real CC ID
            // TODO: Change ID to real ATM block ID
            if(e.getClickedBlock() != null && e.getClickedBlock().getTypeId() == 478) {
                if(e.getPlayer().getInventory().getItemInMainHand().getTypeId() == 4324) ATM(e.getPlayer());
                else e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cVous devez utiliser votre carte banquaire pour accèder au distributeur!");
            }

        });
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //If command is /atm
        if(command.getName().equalsIgnoreCase("atm")) {

            //If player has permission "LibertyCity.forceatm"
            if(sender.hasPermission("LibertyCity.forceatm")) {

                //Open up the ATM's main menu
                ATM((Player) sender);

                //Tell the player the ATM's main menu has been opened with a debug
                sender.sendMessage("§2§lLiberty§a§lCity §7» §7§oOuvertue de l'ATM...");
                sender.sendMessage("§7§o(T=DG/TT | RESTR=Permission.node=LibertyCity.forceatm | RUN=Async | ID=7395)");
            }
        }

        return true;
    }
}
