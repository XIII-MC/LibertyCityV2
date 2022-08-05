package com.xiii.libertycity.core.commands.chat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("clearchat")) {
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(!p.hasPermission("LibertyCity.bypass.clearchat")) {
                    for(int i = 0; i < 524; i++) p.sendMessage(" ");
                    Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §fLe chat a été néttoyer !");

                } else p.sendMessage("§2§lLiberty§a§lCity §7» §7§oVotre chat n'a pas été néttoyer");
            }
        }

        return true;
    }
}
