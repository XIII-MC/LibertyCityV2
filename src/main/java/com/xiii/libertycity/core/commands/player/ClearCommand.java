package com.xiii.libertycity.core.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if(command.getName().equalsIgnoreCase("clear")) {
            if(args.length == 0) {
                if(sender instanceof Player) {
                    p.getInventory().clear();
                    p.sendMessage("§2§lLiberty§a§lCity §7» §fVotre inventaire a été vidé");
                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");
            } else {
                try {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target.isOnline()) {
                        target.getInventory().clear();
                        target.sendMessage("§2§lLiberty§a§lCity §7» §fVotre inventaire a été vidé");
                        p.sendMessage("§2§lLiberty§a§lCity §7» Inventaire de §e" + target.getName() + " §fa été vidé");
                    } else
                        p.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! " + target.getName() + " n'est pas en ligne!");
                } catch (Exception e) {
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Joueur introuvable.");
                }
            }
        }

        return true;
    }
}
