package com.xiii.libertycity.core.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SudoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("sudo")) {
            if(args.length >= 2) {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if(target.isOnline()) {
                    if(args[1].contains("c:")) {
                        target.chat(args[1].substring(2));
                    } else {
                        target.performCommand(args[1]);
                    }
                }
            } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! Usage: /sudo <Joueur> <c:Message | Command>");
        }

        return true;
    }
}
