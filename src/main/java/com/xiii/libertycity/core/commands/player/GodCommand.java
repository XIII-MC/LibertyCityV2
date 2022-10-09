package com.xiii.libertycity.core.commands.player;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("god")) {
            if(args.length == 0) {
                if(sender instanceof Player) {
                    PlayerData data = Data.data.getUserData((Player) sender);
                    if (!data.isGodMode) {
                        data.isGodMode = true;
                        ((Player) sender).setInvulnerable(true);
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §fGod mode §aactivé");
                    } else {
                        data.isGodMode = false;
                        ((Player) sender).setInvulnerable(false);
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §fGod mode §cdésactivé");
                    }
                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");
            } else {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if(target.isOnline()) {
                    PlayerData data = Data.data.getUserData(target);
                    if(!data.isGodMode) {
                        data.isGodMode = true;
                        target.setInvulnerable(true);
                        target.sendMessage("§2§lLiberty§a§lCity §7» §fGod mode §aactivé");
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §fGod mode pour §e" + target.getName() + " §aactivé");
                    } else {
                        data.isGodMode = false;
                        target.setInvulnerable(false);
                        target.sendMessage("§2§lLiberty§a§lCity §7» §fGod mode §cdésactivé");
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §fGod mode pour §e" + target.getName() + " §cdésactivé");
                    }
                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! " + target.getName() + " n'est pas en ligne!");
            }
        }

        return true;
    }
}
