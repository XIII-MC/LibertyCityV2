package com.xiii.libertycity.core.commands.player;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("r")) {
            PlayerData data = Data.data.getUserData((Player) sender);
            if(data.lastDm != null) {
                if(args.length < 2) sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Usage: /r <Message>");
                else {
                    if(data.lastDm.isOnline()) {
                        PlayerData send = Data.data.getUserData((Player) sender);
                        PlayerData tar = Data.data.getUserData(data.lastDm);
                        if (tar.allowMsg) {
                            String message = "";
                            for (int i = 0; i < args.length; i++) {
                                message += args[i] + " ";
                            }
                            sender.sendMessage("§7[§a§lVous§7" + "§r §6--> §a§l" + tar.rpPrenom + " §2§l" + tar.rpNom + " §7(" + data.lastDm.getName() + ")] §f" + message);
                            if(!tar.ignoredPlayers.contains(sender.getName())) {
                                data.lastDm.sendMessage("§7[§a§l" + send.rpPrenom + " §2§l" + send.rpNom + " §7(" + sender.getName() + "§7)" + "§r §6--> §a§lMoi§7] §f" + message);
                            }
                        } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cCette personne n'autorise pas les messages privé");
                    } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! " + data.lastDm.getName() + " n'est pas en ligne!");
                }
            }
        }

        return true;
    }
}
