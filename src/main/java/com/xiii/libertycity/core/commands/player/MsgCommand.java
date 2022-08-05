package com.xiii.libertycity.core.commands.player;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if(command.getName().equalsIgnoreCase("msg")) {
            if(args.length < 2) p.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Usage: /msg <Joueur> <Message>");
            else {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                PlayerData send = Data.data.getUserData((Player) sender);
                if(target.isOnline()) {
                    PlayerData tar = Data.data.getUserData(target);
                    if (tar.allowMsg) {
                        tar.lastDm = (Player) sender;
                        send.lastDm = target;
                        String message = "";
                        for (int i = 1; i < args.length; i++) {
                            message += args[i] + " ";
                        }
                        target.sendMessage("§7[§a§l" + send.rpPrenom + " §2§l" + send.rpNom + " §7(" + sender.getName() + "§7)" + "§r §6--> §a§l" + tar.rpPrenom + " §2§l" + tar.rpNom + " §7(" + target.getName() + ")] §f" + message);
                    } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cCette personne n'autorise pas les messages privé");
                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! " + target.getName() + " n'est pas en ligne!");
            }
        }

        return true;
    }
}
