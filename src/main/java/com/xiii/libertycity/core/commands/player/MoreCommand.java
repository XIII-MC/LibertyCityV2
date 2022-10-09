package com.xiii.libertycity.core.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("more")) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if (p.getItemInHand() != null && p.getItemInHand().getType() != null) {
                    p.getItemInHand().setAmount(64);
                }
            } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");
        }

        return true;
    }
}
