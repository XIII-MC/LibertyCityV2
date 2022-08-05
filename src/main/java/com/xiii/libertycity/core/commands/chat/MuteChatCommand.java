package com.xiii.libertycity.core.commands.chat;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.ServerData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("mutechat")) {
            ServerData server = Data.data.getServerData(Bukkit.getServer());
            if(args.length < 1) {
                if(server.chatStateGlobal) {
                    server.chatStateGlobal = false;
                    Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §fLe chat §eglobal §fa été rendu muet.");
                    for(Player p : Bukkit.getOnlinePlayers()) if(p.hasPermission("LibertyCity.staffalerts")) p.sendMessage("§4§lSTAFF §7» §8" + p.getName() + " §7a mute le chat §8Global");
                } else {
                    server.chatStateGlobal = true;
                    Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §fLe chat §eglobal §fn'est plus muet.");
                    for(Player p : Bukkit.getOnlinePlayers()) if(p.hasPermission("LibertyCity.staffalerts")) p.sendMessage("§4§lSTAFF §7» §8" + p.getName() + " §7a unmute le chat §8Global");
                }
            }
        }

        return true;
    }
}
