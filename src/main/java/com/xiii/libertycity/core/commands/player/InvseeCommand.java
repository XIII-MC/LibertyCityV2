package com.xiii.libertycity.core.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvseeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if(command.getName().equalsIgnoreCase("invsee")) {
            if(sender instanceof Player) {
                if (args.length == 0) sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Usage: /invsee <Joueur>");
                else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target.isOnline()) {
                        p.openInventory(target.getInventory());
                        p.sendMessage("§2§lLiberty§a§lCity §7» §fOuverture de l'inventaire de §e" + target.getName());
                    } else
                        p.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! " + target.getName() + " n'est pas en ligne!");
                }
            } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");
        }

        return true;
    }
}
