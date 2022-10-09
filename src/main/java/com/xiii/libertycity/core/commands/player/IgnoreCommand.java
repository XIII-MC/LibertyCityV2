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
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    PlayerData data = Data.data.getUserData((Player) sender);
                    if (!data.ignoredPlayers.contains(target.getName())) {
                        data.ignoredPlayers.add(target.getName());
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fa été ignoré");
                    } else {
                        data.ignoredPlayers.remove(target.getName());
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fa n'est plus ignoré");
                    }
                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");
            } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! Usage: /ignore <Joueur>");
        }

        return true;
    }
}
