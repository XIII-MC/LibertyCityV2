package com.xiii.libertycity.core.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class NearCommand implements CommandExecutor {

    List<String> playersWithinRange = new ArrayList<>();
    Integer range;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("near")) {
            if(args.length >= 1) {
                range = Integer.valueOf(args[0]);
            } else range = 20;
            Player snder = (Player) sender;
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getLocation().distance(snder.getLocation()) <= range) {
                    playersWithinRange.add(p.getName());
                }
            }
            if (playersWithinRange.size() > 0) {
                sender.sendMessage("§2§lLiberty§a§lCity §7» §6" + playersWithinRange.size() + " §fjoueurs trouvés dans un rayon de §6" + range + " §f: " + playersWithinRange);
            } else sender.sendMessage("§2§lLiberty§a§lCity §7» §fAucun joueur trouvé dans un rayon de §6" + range);
        }

        return true;
    }
}
