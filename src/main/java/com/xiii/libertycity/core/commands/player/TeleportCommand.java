package com.xiii.libertycity.core.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("teleport")) {
            if (args.length == 0) sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Usage: /teleport <Joueur> (Joueur)");
            else {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                Player p = (Player) sender;
                if(target.isOnline()) {
                    if(args.length == 1) {
                        if(sender instanceof Player) {
                            p.teleport(target);
                            p.sendMessage("§2§lLiberty§a§lCity §7» §fVous avez été téléporter à §e" + target.getName());
                        } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");
                    } else if(args.length == 2) {
                        Player target2 = Bukkit.getServer().getPlayer(args[1]);
                        if(target2.isOnline()) {
                            target.teleport(target2);
                            target.sendMessage("§2§lLiberty§a§lCity §7» §fVous avez été téléporter à §e" + target2.getName());
                            p.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fa été téléporter à §e" + target2.getName());
                        } else p.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! " + target.getName() + " n'est pas en ligne!");
                    }
                } else p.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! " + target.getName() + " n'est pas en ligne!");
            }
        }

        if(command.getName().equalsIgnoreCase("tpall")) {
            if(sender instanceof Player) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.teleport((Player) sender);
                    p.sendMessage("§2§lLiberty§a§lCity §7» §fVous avez été téléporter vers §e" + sender.getName());
                }
            } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");
        }

        if(command.getName().equalsIgnoreCase("tphere")) {
            if(sender instanceof Player) {

            } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");

        }

        return true;
    }
}
