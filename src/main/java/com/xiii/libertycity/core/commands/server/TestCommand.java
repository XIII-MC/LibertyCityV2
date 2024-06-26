package com.xiii.libertycity.core.commands.server;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.data.ServerData;
import com.xiii.libertycity.core.utils.IDUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("LCDBB1BETA")) {

            // Initialize player and data.
            Player player = (Player) sender;
            ServerData serverData = Data.data.getServerData(Bukkit.getServer());
            PlayerData playerData = Data.data.getUserData(player);

            // Previous tests list :

                // - TEST_1 : testing PlayerData flexibility to new variables | SUCCESS.
                // - DEBUG_1 : debugging player data output to fix playerData being global | SUCCESS.
                // - TEST_2 : test AntiAFK system | SUCCESS.
                // - TEST_3 : log message to yml file | SUCCESS.
                // - DEV_1 : Dev debug for custom boss bar
                // - DEBUG_2 : debugging player rpname, rpprenom db | SUCCESS.

            // Test section. | CASE: UTIL_1 : generating a new ID card
            IDUtils.createNewID(player, playerData);
            player.sendMessage("ID & Wallet Created.");


        }

        return true;
    }
}
