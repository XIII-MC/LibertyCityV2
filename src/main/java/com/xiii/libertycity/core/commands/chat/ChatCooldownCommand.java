package com.xiii.libertycity.core.commands.chat;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.ServerData;
import com.xiii.libertycity.core.utils.AlertUtil;
import com.xiii.libertycity.core.utils.ConvertUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatCooldownCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ServerData server = Data.data.getServerData(Bukkit.getServer());

        if(command.getName().equalsIgnoreCase("cooldown")) {
            if(args.length == 0) sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Usage: /cooldown <Temps>");
            else {
                int newIntConverted = 0;
                newIntConverted = Integer.parseInt(args[0]);
                try {
                    newIntConverted = (int) ConvertUtils.parseDateDiff(String.valueOf(newIntConverted), false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                server.chatCooldownGlobal = newIntConverted;
                sender.sendMessage("§2§lLiberty§a§lCity §7» §fLe chat a désormais un délais de §6" + args[0]);
                AlertUtil.staffAlert("§8" + sender.getName() + " §7a mis le délais pour parler à §8" + args[0], "LibertyCity.staff.alerts", 0);
            }
        }

        return true;
    }
}
