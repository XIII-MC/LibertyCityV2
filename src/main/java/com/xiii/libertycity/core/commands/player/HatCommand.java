package com.xiii.libertycity.core.commands.player;

import com.xiii.libertycity.core.utils.InventoryUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if(command.getName().equalsIgnoreCase("hat")) {
            if(p.getItemInHand() != null && p.getItemInHand().getType() != null) {
                p.getInventory().setHelmet(p.getItemInHand());
                InventoryUtils.removeOne(p.getInventory(), p.getItemInHand());
            }
        }

        return true;
    }
}
