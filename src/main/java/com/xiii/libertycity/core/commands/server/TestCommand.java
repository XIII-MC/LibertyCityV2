package com.xiii.libertycity.core.commands.server;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.utils.YMLUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("LCDBB1BETA")) {

            // Initialize player and data.
            Player player = (Player) sender;
            PlayerData playerData = Data.data.getUserData(player);

            // Previous tests list :

                // - TEST_1 : testing PlayerData flexibility to new variables | SUCCESS.
                // - DEBUG_1 : debugging player data output to fix playerData being global | SUCCESS.
                // - TEST_2 : test AntiAFK system | SUCCESS.

            // Test section. | CASE: TEST_3 : log message to yml file
            if(args[0].length() <= 0) player.sendMessage("Message null.");
            if(args[0].length() > 0) YMLUtil.log(args[0], null, null);
            player.sendMessage("Message logged.");

        }

        return true;
    }
}
