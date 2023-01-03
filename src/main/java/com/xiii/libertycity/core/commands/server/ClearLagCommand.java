package com.xiii.libertycity.core.commands.server;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class ClearLagCommand implements CommandExecutor {

    public static int getItemsOnTheGround() {
        int i = 0;
        for(Entity current : Bukkit.getWorld("world").getEntities()) {
            if (current.getType() == EntityType.DROPPED_ITEM) {
                i++;
            }
        }
        return i;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int i = 0;

        if(command.getName().equalsIgnoreCase("clearlag")) {
            for(Entity current : Bukkit.getWorld("world").getEntities()) {
                if(current.getType() == EntityType.DROPPED_ITEM) {
                    i++;
                    current.remove();
                }
            }
        }

        return true;
    }
}
