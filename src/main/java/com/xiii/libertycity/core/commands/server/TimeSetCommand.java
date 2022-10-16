package com.xiii.libertycity.core.commands.server;

import com.xiii.libertycity.core.utils.AlertUtil;
import com.xiii.libertycity.core.utils.TimeUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeSetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if(command.getName().equalsIgnoreCase("day")) {
            p.getWorld().setTime(1000);
            AlertUtil.staffAlert("§8" + p.getName() + " §7a mis l'heure a §8Jour", "LibertyCity.staff.alert", 0);
        }

        if(command.getName().equalsIgnoreCase("night")) {
            p.getWorld().setTime(13000);
            AlertUtil.staffAlert("§8" + p.getName() + " §7a mis l'heure a §8Nuit", "LibertyCity.staff.alert", 0);
        }

        if(command.getName().equalsIgnoreCase("time")) {
            p.sendMessage("§2§lLiberty§a§lCity §7» §fIl est actuellement §6" + p.getWorld().getTime() + " §7(" + TimeUtil.getTime() + ")");
        }

        return true;
    }
}
