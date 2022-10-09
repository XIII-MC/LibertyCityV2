package com.xiii.libertycity.core.commands.server;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ListCommand implements CommandExecutor {

    List<String> playerNames = new ArrayList<>();
    int playerAmount = 0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("list")) {
            for(Player p : Bukkit.getOnlinePlayers()) {
                playerAmount++;
                playerNames.add(p.getName());
            }
            sender.sendMessage("§2§lLiberty§a§lCity §7» §fIl y a §6" + playerAmount + " §fjoueurs en ligne : §e" + playerNames);
        }

        return true;
    }
}
