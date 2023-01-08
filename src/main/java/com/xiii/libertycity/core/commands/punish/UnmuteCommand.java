package com.xiii.libertycity.core.commands.punish;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.ServerData;
import com.xiii.libertycity.core.utils.AlertUtil;
import com.xiii.libertycity.core.utils.YMLUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnmuteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("unmute")) {

            ServerData server = Data.data.getServerData(Bukkit.getServer());

            if(args.length < 1) sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! Usage: /unmute <Joueur> (-ip)");

            if(args.length == 1) {

                OfflinePlayer target = Bukkit.getServer().getPlayer(args[0]);

                if(server.mutedPlayer.contains(args[0])) {

                    server.mutedPlayer.remove(args[0]);
                    YMLUtil.log("UNMUTE - " + target.getName() + " n'est plus muet par " + sender.getName(), "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                    AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu la voix à §8" + target.getName(), "LibertyCity.staff.alerts", 0);
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fn'est plus muet(te)");

                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! §e" + target.getName() + " §cn'est pas muet(te)");

            } else if(args.length == 2) {

                OfflinePlayer target = Bukkit.getServer().getPlayer(args[0]);

                if(server.mutedPlayer.contains(target.getPlayer().getName())) {

                    server.mutedPlayer.remove(target.getPlayer().getName());
                    YMLUtil.log("UNMUTE - " + target.getName() + " n'est plus muet par " + sender.getName(), "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                    AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu la voix à §8" + target.getName(), "LibertyCity.staff.alerts", 0);
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fn'est plsu muet(te)");

                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! §e" + target.getName() + " §cn'est pas muet(te)'");

                if(server.mutedIPs.contains(target.getPlayer().getAddress().getHostName())) {

                    server.mutedIPs.remove(target.getPlayer().getAddress().getHostName());
                    YMLUtil.log("UNMUTE - " + target.getName() + " n'est plus muet par " + sender.getName() + " | note: UNMUTE_IP", "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                    AlertUtil.staffAlert("§8" + sender.getName() + " §7a a rendu la voix à l'IP de §8" + target.getName(), "LibertyCity.staff.alerts", 0);
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §fl'IP de §e" + target.getName() + " §fn'est plus muette");

                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! L'IP de §e" + target.getName() + " §cn'est pas muette");

            }

        }

        return true;
    }
}
