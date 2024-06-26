package com.xiii.libertycity.core.commands.player;

import com.xiii.libertycity.core.utils.PingUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("ping")) {
            if(sender instanceof Player) {
                if (args.length == 0) {
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §fCalcule en cours, veuillez patientez...");
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous avez une latence de §6" + PingUtil.getPing((Player) sender) + "ms");
                } else {
                    try {
                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        if (target.isOnline()) {
                            sender.sendMessage("§2§lLiberty§a§lCity §7» §fCalcule en cours, veuillez patientez...");
                            sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fa une latence de §6" + PingUtil.getPing(target) + "ms");
                        } else
                            sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! " + target.getName() + " n'est pas en ligne!");
                    } catch (Exception e) {
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Joueur introuvable.");
                    }
                }
            } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");
        }

        return true;
    }
}
