package com.xiii.libertycity.core.commands.server;

import com.xiii.libertycity.core.utils.AlertUtil;
import org.bukkit.WeatherType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeatherCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if(command.getName().equalsIgnoreCase("sun")) {
            p.setPlayerWeather(WeatherType.CLEAR);
            AlertUtil.staffAlert("§8" + p.getName() + " §7a mis la metéo a §8Ensoleillé", "LibertyCity.staff.alert", 0);
        }

        if(command.getName().equalsIgnoreCase("rain")) {
            p.setPlayerWeather(WeatherType.DOWNFALL);
            AlertUtil.staffAlert("§8" + p.getName() + " §7a mis la metéo a §8Pluivieux", "LibertyCity.staff.alert", 0);
        }

        return true;
    }
}
