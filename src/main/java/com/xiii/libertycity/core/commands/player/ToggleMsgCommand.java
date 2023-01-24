package com.xiii.libertycity.core.commands.player;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.utils.InventoryUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ToggleMsgCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("togglemsg")) {
            if(sender instanceof Player) {
                if(InventoryUtils.hasItem(((Player) sender), new ItemStack(Material.getMaterial(7385)))) {
                    PlayerData data = Data.data.getUserData((Player) sender);
                    if (!data.allowMsg) {
                        data.allowMsg = true;
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §fMode ne pas déranger §aactivé§f!");
                    } else {
                        data.allowMsg = false;
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §fMode ne pas déranger §cdésactivé§f!");
                    }
                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! Vous ne disposez pas d'un teléphone!");
            } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");
        }

        return true;
    }
}
