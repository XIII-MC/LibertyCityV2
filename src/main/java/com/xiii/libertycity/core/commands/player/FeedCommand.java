package com.xiii.libertycity.core.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("feed")) {
            if(args.length == 0) {
                if(sender instanceof Player) {
                    Player p = (Player) sender;
                    p.setFoodLevel(20);
                    p.sendMessage("§2§lLiberty§a§lCity §7» §fVous avez été rassasié");
                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");
            } else {
                try {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target.isOnline()) {
                        target.setFoodLevel(20);
                        target.sendMessage("§2§lLiberty§a§lCity §7» §fVous avez été rassasié");
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fa été rassasié");
                    } else
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! " + target.getName() + " n'est pas en ligne!");
                } catch (Exception e) {
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Joueur introuvable.");
                }
            }
        }

        return true;
    }
}
