package com.xiii.libertycity.core.commands.chat;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.ServerData;
import com.xiii.libertycity.core.utils.AlertUtil;
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
                    Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §fLe chat §6global §fa était rendu muet.");
                    AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu le chat §8global§7 muet.", "LibertyCity.staff.alerts", 0);
                } else {
                    server.chatStateGlobal = true;
                    Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §fLe chat §6global §fn'est plus muet.");
                    AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu la voix au chat §8global§7.", "LibertyCity.staff.alerts", 0);
                }
            } else {

                if(args[0].equalsIgnoreCase("rp")) {
                    if(server.chatStateRP) {
                        server.chatStateRP = false;
                        Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §fLe chat §6RP §fa était rendu muet.");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu le chat §8RP§7 muet.", "LibertyCity.staff.alerts", 0);
                    } else {
                        server.chatStateRP = true;
                        Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §fLe chat §6RP §fn'est plus muet.");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu la voix au chat §8RP§7.", "LibertyCity.staff.alerts", 0);
                    }
                }

                if(args[0].equalsIgnoreCase("hrp")) {
                    if(server.chatStateHRP) {
                        server.chatStateHRP = false;
                        Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §fLe chat §6HRP §fa était rendu muet.");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu le chat §8HRP§7 muet.", "LibertyCity.staff.alerts", 0);
                    } else {
                        server.chatStateHRP = true;
                        Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §fLe chat §6HRP §fn'est plus muet.");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu la voix au chat §8HRP§7.", "LibertyCity.staff.alerts", 0);
                    }
                }

                if(args[0].equalsIgnoreCase("police")) {
                    if(server.chatStatePolice) {
                        server.chatStatePolice = false;
                        Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §fLe chat §6Police §fa était rendu muet.");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu le chat §8Police§7 muet.", "LibertyCity.staff.alerts", 0);
                    } else {
                        server.chatStatePolice = true;
                        Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §fLe chat §6Police §fn'est plus muet.");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu la voix au chat §8Police§7.", "LibertyCity.staff.alerts", 0);
                    }
                }

                if(args[0].equalsIgnoreCase("gang")) {
                    if(server.chatStateGang) {
                        server.chatStateGang = false;
                        Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §fLe chat §6Gang §fa était rendu muet.");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu le chat §8Gang§7 muet.", "LibertyCity.staff.alerts", 0);
                    } else {
                        server.chatStateGang = true;
                        Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §fLe chat §6Gang §fn'est plus muet.");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu la voix au chat §8Gang§7.", "LibertyCity.staff.alerts", 0);
                    }
                }

            }
        }

        return true;
    }
}
