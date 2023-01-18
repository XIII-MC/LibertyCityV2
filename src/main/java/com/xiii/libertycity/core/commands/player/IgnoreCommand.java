package com.xiii.libertycity.core.commands.player;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IgnoreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("ignore")) {
            if(args.length >= 1) {
                if(sender instanceof Player) {
                    PlayerData data = Data.data.getUserData((Player) sender);
                    if (!data.ignoredPlayers.contains(args[0])) {
                        data.ignoredPlayers.add(args[0]);
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + args[0] + " §fa été ignoré");
                    } else {
                        data.ignoredPlayers.remove(args[0]);
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + args[0] + " §fa n'est plus ignoré");
                    }
                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");
            } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! Usage: /ignore <Joueur>");
        }

        return true;
    }
}
