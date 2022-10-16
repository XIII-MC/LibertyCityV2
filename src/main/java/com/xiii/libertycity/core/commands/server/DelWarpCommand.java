package com.xiii.libertycity.core.commands.server;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.ServerData;
import com.xiii.libertycity.core.utils.YMLUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;

public class DelWarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("delwarp")) {
            if(args.length >= 1) {
                ServerData server = Data.data.getServerData(Bukkit.getServer());
                File warpFile = new File(LibertyCity.INSTANCE.getDataFolder() + "/server/warps/" + args[0] + ".yml");
                if(warpFile.exists()) {
                    if(server.warpsNames.contains(args[0])) server.warpsNames.remove(args[0]);
                    YMLUtil.deleteWarp(args[0]);
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §fLe warp §6" + args[0] + " §fa été supprimer");
                } else {
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Ce warp n'existe pas");
                }
            } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! Usage: /delwarp <Nom>");
        }

        return true;
    }
}
