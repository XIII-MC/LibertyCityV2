package com.xiii.libertycity.core.commands.player;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.data.ServerData;
import com.xiii.libertycity.core.displays.TABDisplay;
import com.xiii.libertycity.core.utils.AlertUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if(command.getName().equalsIgnoreCase("vanish")) {
            ServerData server = Data.data.getServerData(Bukkit.getServer());
            if(args.length <= 0) {
                if(sender instanceof Player) {
                    Player snder = (Player) sender;
                    PlayerData data = Data.data.getUserData(snder);
                    if (data.isVanished && server.vanishedPlayers.contains(snder)) {
                        data.isVanished = false;
                        server.vanishedPlayers.remove(snder);
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.showPlayer(LibertyCity.INSTANCE, snder);
                        }
                        snder.setPlayerListName(snder.getName());
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous n'êtes plus invisible");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7n'est plus invisible", "LibertyCity.staff", 0);
                    } else {
                        data.isVanished = true;
                        assert server != null;
                        server.vanishedPlayers.add(snder);
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (!server.vanishedPlayers.contains(p) || !p.hasPermission("LibertyCity.seevanishedplayers")) p.hidePlayer(LibertyCity.INSTANCE, snder);
                        }
                        snder.setPlayerListName("§7[V] §f" + snder.getName());
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais invisible");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7est désormais invisible", "LibertyCity.staff", 0);
                    }
                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");
            } else {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                PlayerData trget = Data.data.getUserData(target);
                if(target.isOnline()) {
                    if(trget.isVanished) {
                        trget.isVanished = false;
                        server.vanishedPlayers.remove(target);
                        for(Player p : Bukkit.getOnlinePlayers()) {
                            p.showPlayer(LibertyCity.INSTANCE, target);
                        }
                        target.setPlayerListName(target.getName());
                        target.sendMessage("§2§lLiberty§a§lCity §7» §fVous n'êtes plus invisible");
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + "§f n'est plus invisible");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu §8" + target.getName() + " §7visible", "LibertyCity.staff", 0);
                    } else {
                        trget.isVanished = true;
                        server.vanishedPlayers.add(target);
                        for(Player p : Bukkit.getOnlinePlayers()) {
                            if(!server.vanishedPlayers.contains(p) || !p.hasPermission("LibertyCity.seevanishedplayers")) p.hidePlayer(LibertyCity.INSTANCE, target);
                        }
                        target.setPlayerListName("§7[V] §f" + target.getName());
                        target.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais invisible");
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + "§f est désormais invisible");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu §8" + target.getName() + " §7invisible", "LibertyCity.staff", 0);
                    }
                }

            }
        }
        TABDisplay.updatePlayerList();
        return true;
    }
}
