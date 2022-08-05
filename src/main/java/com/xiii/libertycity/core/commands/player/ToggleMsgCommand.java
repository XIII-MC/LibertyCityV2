package com.xiii.libertycity.core.commands.player;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleMsgCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("togglemsg")) {
            PlayerData data = Data.data.getUserData((Player) sender);
            if(!data.allowMsg) {
                data.allowMsg = true;
                sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous avez §aactivé§f vos messages privé");
            } else {
                data.allowMsg = false;
                sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous avez §cdésactivé§f vos messages privé");
            }
        }

        return true;
    }
}
