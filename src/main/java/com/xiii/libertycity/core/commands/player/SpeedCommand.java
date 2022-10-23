package com.xiii.libertycity.core.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if(command.getName().equalsIgnoreCase("speed")) {
            if(args.length == 1) {
                if(sender instanceof Player) {
                    if (!args[0].contains("walk") || !args[0].contains("fly")) {
                        final float newIntConverted = Float.parseFloat(args[0]);
                        if (p.isOnGround()) {
                            p.setWalkSpeed((newIntConverted) / 10);
                            p.sendMessage("§2§lLiberty§a§lCity §7» §fVotre vitesse de marche est désormais §6" + args[0]);
                        } else {
                            p.setFlySpeed((newIntConverted) / 10);
                            p.sendMessage("§2§lLiberty§a§lCity §7» §fVotre vitesse de vol est désormais §6" + args[0]);
                        }
                    } else
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Usage: /speed (walk/fly) <Vitesse> (Joueur)");
                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");
            } else if(args.length == 2) {
                if(sender instanceof Player) {
                    final float newIntConverted = Float.parseFloat(args[1]);
                    if (args[0].equalsIgnoreCase("walk")) {
                        p.setWalkSpeed((newIntConverted) / 10);
                        p.sendMessage("§2§lLiberty§a§lCity §7» §fVotre vitesse de marche est désormais §6" + args[0]);
                    } else if (args[0].equalsIgnoreCase("fly")) {
                        p.setFlySpeed((newIntConverted) / 10);
                        p.sendMessage("§2§lLiberty§a§lCity §7» §fVotre vitesse de vol est désormais §6" + args[0]);
                    }
                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");
            } else if(args.length == 3) {
                Player target = Bukkit.getServer().getPlayer(args[2]);
                if(target.isOnline()) {
                    final float newIntConverted = Float.parseFloat(args[1]);
                    if (args[0].equalsIgnoreCase("walk")) {
                        target.setWalkSpeed((newIntConverted) / 10);
                        target.sendMessage("§2§lLiberty§a§lCity §7» §fVotre vitesse de marche est désormais §6" + args[3]);
                        p.sendMessage("§2§lLiberty§a§lCity §7» §fVitesse de marche pour §e" + target.getName() + " §fmise à §6" + args[3]);
                    } else if (args[0].equalsIgnoreCase("fly")) {
                        target.setFlySpeed((newIntConverted) / 10);
                        target.sendMessage("§2§lLiberty§a§lCity §7» §fVotre vitesse de vol est désormais §6" + args[3]);
                        p.sendMessage("§2§lLiberty§a§lCity §7» §fVitesse de vol pour §e" + target.getName() + " §fmise à §6" + args[3]);
                    }
                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! " + target.getName() + " n'est pas en ligne!");
            }
        }

        return true;
    }
}
