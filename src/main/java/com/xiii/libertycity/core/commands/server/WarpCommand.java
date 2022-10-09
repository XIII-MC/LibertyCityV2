package com.xiii.libertycity.core.commands.server;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.ServerData;
import com.xiii.libertycity.core.utils.YMLUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class WarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("warp")) {
            if (sender instanceof Player) {
                if (args.length >= 1) {
                    ServerData server = Data.data.getServerData(Bukkit.getServer());
                    Player p = (Player) sender;
                    File warpFile = new File(LibertyCity.INSTANCE.getDataFolder() + "\\server\\warps\\" + args[0] + ".yml");
                    if (warpFile.exists()) {
                        p.teleport(YMLUtil.loadWarp(args[0]));
                        p.sendMessage("§2§lLiberty§a§lCity §7» §fVous avez été téléporter vers le warp §6" + args[0]);
                    } else p.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Ce warp n'existe pas");
                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! Usage: /warp <Nom>");
            } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");
        }

        return true;
    }
}
