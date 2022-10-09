package com.xiii.libertycity.core.commands.server;

import com.xiii.libertycity.core.utils.YMLUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WarpsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("warps")) {
            if(YMLUtil.getWarps().size() > 0) {
                sender.sendMessage("§2§lLiberty§a§lCity §7» §fListes des warps: §6" + YMLUtil.getWarps());
            } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Il n'y a aucun warp existant");
        }

        return true;
    }
}
