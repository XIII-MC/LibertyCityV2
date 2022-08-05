package com.xiii.libertycity.core.commands.server;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class ClearLagCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int i = 0;

        if(command.getName().equalsIgnoreCase("clearlag")) {
            Player p = (Player) sender;
            for(Entity current : p.getWorld().getEntities()) {
                if(current.getType() == EntityType.DROPPED_ITEM) {
                    i++;
                    current.remove();
                }
            }
            Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §fLes éboueurs ont ramassé §6" + i + " §fordures");
        }

        return true;
    }
}
