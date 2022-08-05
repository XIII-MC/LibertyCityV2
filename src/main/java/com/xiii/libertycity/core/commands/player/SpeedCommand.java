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
                if(!args[0].contains("walk") || !args[0].contains("fly")) {
                    int newIntConverted = 0;
                    newIntConverted = Integer.parseInt(args[0]);
                    if (p.isOnGround()) {
                        p.setWalkSpeed((float) newIntConverted);
                        p.sendMessage("§2§lLiberty§a§lCity §7» §fVotre vitesse de marche est désormais §6" + newIntConverted);
                    } else {
                        p.setFlySpeed((float) newIntConverted);
                        p.sendMessage("§2§lLiberty§a§lCity §7» §fVotre vitesse de vol est désormais §6" + newIntConverted);
                    }
                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Usage: /speed (walk/fly) <Vitesse> (Joueur)");
            } else if(args.length == 2) {
                int newIntConverted = 0;
                newIntConverted = Integer.parseInt(args[1]);
                if(args[0].equalsIgnoreCase("walk")) {
                    p.setWalkSpeed((float) newIntConverted);
                    p.sendMessage("§2§lLiberty§a§lCity §7» §fVotre vitesse de marche est désormais §6" + newIntConverted);
                } else if(args[0].equalsIgnoreCase("fly")) {
                    p.setFlySpeed((float) newIntConverted);
                    p.sendMessage("§2§lLiberty§a§lCity §7» §fVotre vitesse de vol est désormais §6" + newIntConverted);
                }
            } else if(args.length == 3) {
                Player target = Bukkit.getServer().getPlayer(args[2]);
                if(target.isOnline()) {
                    int newIntConverted = 0;
                    newIntConverted = Integer.parseInt(args[1]);
                    if (args[0].equalsIgnoreCase("walk")) {
                        target.setWalkSpeed((float) newIntConverted);
                        target.sendMessage("§2§lLiberty§a§lCity §7» §fVotre vitesse de marche est désormais §6" + newIntConverted);
                        p.sendMessage("§2§lLiberty§a§lCity §7» §fVitesse de marche pour §e" + target.getName() + " §fmise à §6" + newIntConverted);
                    } else if (args[0].equalsIgnoreCase("fly")) {
                        target.setFlySpeed((float) newIntConverted);
                        target.sendMessage("§2§lLiberty§a§lCity §7» §fVotre vitesse de vol est désormais §6" + newIntConverted);
                        p.sendMessage("§2§lLiberty§a§lCity §7» §fVitesse de vol pour §e" + target.getName() + " §fmise à §6" + newIntConverted);
                    }
                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! " + target.getName() + " n'est pas en ligne!");
            }
        }

        return true;
    }
}
