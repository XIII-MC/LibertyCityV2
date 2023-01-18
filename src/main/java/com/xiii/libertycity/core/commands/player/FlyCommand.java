package com.xiii.libertycity.core.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("fly")) {
            if(args.length == 0) {
                if(sender instanceof Player) {
                    Player p = (Player) sender;
                    if (!p.getAllowFlight()) {
                        p.setAllowFlight(true);
                        p.sendMessage("§2§lLiberty§a§lCity §7» §fVous pouvez désormais volé");
                    } else {
                        p.setAllowFlight(false);
                        p.sendMessage("§2§lLiberty§a§lCity §7» §fVous ne pouvez plus volé");
                    }
                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");
            } else {
                try {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target.isOnline()) {
                        if (!target.getAllowFlight()) {
                            target.setAllowFlight(true);
                            target.sendMessage("§2§lLiberty§a§lCity §7» §fVous pouvez désormais volé");
                            sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fpeut désormais volé");
                        } else {
                            target.setAllowFlight(false);
                            target.sendMessage("§2§lLiberty§a§lCity §7» §fVous ne pouvez plus volé");
                            sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fne peut plus volé");
                        }
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
