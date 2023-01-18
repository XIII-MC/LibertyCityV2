package com.xiii.libertycity.core.commands.punish;

import com.xiii.libertycity.core.utils.APIUtils;
import com.xiii.libertycity.core.utils.AlertUtil;
import com.xiii.libertycity.core.utils.YMLUtil;
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

                OfflinePlayer target = Bukkit.getOfflinePlayer(APIUtils.getSafeUUID(args[0]));

                if(banList.isBanned(args[0])) {

                    banList.pardon(args[0]);
                    YMLUtil.log("UNBAN | " + target.getName() + " a était débanni par " + sender.getName(), "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                    AlertUtil.staffAlert("§8" + sender.getName() + " §7a débanni §8" + args[0], "LibertyCity.staff.alerts", 0);
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + args[0] + " §fa été débanni");

                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! §e" + args[0] + " §cn'est pas banni");

            } else if(args.length == 2) {

                OfflinePlayer target = Bukkit.getServer().getPlayer(args[0]);

                if(banList.isBanned(args[0])) {

                    banList.pardon(args[0]);
                    AlertUtil.staffAlert("§8" + sender.getName() + " §7a débanni §8" + args[0], "LibertyCity.staff.alerts", 0);
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + args[0] + " §fa été débanni");
                    YMLUtil.log("UNBAN | " + target.getName() + " a était débanni par " + sender.getName(), "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");

                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! §e" + args[0] + " §cn'est pas banni");

                if(banListIP.isBanned(((Player) target).getAddress().getHostName())) {

                    banListIP.pardon(((Player) target).getAddress().getHostName());
                    YMLUtil.log("UNBAN IP | " + target.getName() + " a était débanni par " + sender.getName(), "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                    AlertUtil.staffAlert("§8" + sender.getName() + " §7a débanni l'IP de §8" + args[0], "LibertyCity.staff.alerts", 0);
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §fl'IP de §e" + args[0] + " §fa été débanni");

                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! L'IP de §e" + args[0] + " §cn'est pas banni");

            }

        }

        return true;
    }
}
