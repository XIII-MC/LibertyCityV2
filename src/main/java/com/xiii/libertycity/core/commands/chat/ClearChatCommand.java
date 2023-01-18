package com.xiii.libertycity.core.commands.chat;

import com.xiii.libertycity.core.utils.AlertUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("clearchat")) {
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(!p.hasPermission("LibertyCity.bypass.clearchat")) {
                    for(int i = 0; i < 524; i++) p.sendMessage("§a§b§k§o§n§2§0 ");
                    Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §fLe chat a été néttoyer !");

                } else p.sendMessage("§2§lLiberty§a§lCity §7» §7§oVotre chat n'a pas été néttoyer");
                AlertUtil.staffAlert("§8" + sender.getName() + " §7a nettoyé le chat", "LibertyCity.staff.alerts", 0);
            }
        }

        return true;
    }
}
