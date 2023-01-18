package com.xiii.libertycity.core.commands.punish;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.utils.APIUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class WarnsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("warns")) {

            if(args.length != 1) sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Usage: /warns <Joueur>");

            if(args.length == 1) {

                UUID targetUUID = APIUtils.getSafeUUID(args[0]);
                OfflinePlayer target = Bukkit.getOfflinePlayer(targetUUID);
                PlayerData targetData = Data.data.getUserData(targetUUID.toString());

                if(targetData.warns.size() == 0) sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! Ce joueur n'a pas d'historique d'avertissements.");
                else sender.sendMessage("§2§lLiberty§a§lCity §7» §fListe des §6" + targetData.warns.size() + " §favertissements de §e" + target.getName() + "§f: §6" + targetData.warns);

            }

        }

        return true;
    }
}
