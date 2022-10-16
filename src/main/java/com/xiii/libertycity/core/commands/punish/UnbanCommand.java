package com.xiii.libertycity.core.commands.punish;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.utils.AlertUtil;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnbanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("unban")) {

            BanList banList = Bukkit.getBanList(BanList.Type.NAME);
            BanList banListIP = Bukkit.getBanList(BanList.Type.IP);

            if(args.length < 1) sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Usage: /unban <Joueur> (-ip)");

            if(args.length == 1) {

                OfflinePlayer target = Bukkit.getServer().getPlayer(args[0]);

                if(banList.isBanned(target.getName())) {

                    banList.pardon(target.getName());
                    AlertUtil.staffAlert("§8" + sender.getName() + " §7a débanni §8" + target.getName(), "LibertyCity.staff.alerts", 0);
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fa été débanni");

                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! §e" + target.getName() + " §cn'est pas banni");

            } else if(args.length == 2) {

                OfflinePlayer target = Bukkit.getServer().getPlayer(args[0]);

                if(banList.isBanned(target.getName())) {

                    banList.pardon(target.getName());
                    AlertUtil.staffAlert("§8" + sender.getName() + " §7a débanni §8" + target.getName(), "LibertyCity.staff.alerts", 0);
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fa été débanni");

                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! §e" + target.getName() + " §cn'est pas banni");

                if(banListIP.isBanned(((Player) target).getAddress().getHostName())) {

                    banList.pardon(((Player) target).getAddress().getHostName());
                    AlertUtil.staffAlert("§8" + sender.getName() + " §7a débanni l'IP de §8" + target.getName(), "LibertyCity.staff.alerts", 0);
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §fl'IP de §e" + target.getName() + " §fa été débanni");

                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! L'IP de §e" + target.getName() + " §cn'est pas banni");

            }

        }

        return true;
    }
}
