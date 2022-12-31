package com.xiii.libertycity.core.commands.player;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.utils.YMLUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MsgCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if(command.getName().equalsIgnoreCase("sms")) {
            if(args.length < 2) p.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Usage: /sms <Joueur> <Message>");
            else {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (sender instanceof Player) {
                    PlayerData send = Data.data.getUserData((Player) sender);
                    if (target.isOnline()) {
                        PlayerData tar = Data.data.getUserData(target);
                        if (tar.allowMsg) {
                            tar.lastDm = (Player) sender;
                            send.lastDm = target;
                            String message = "";
                            for (int i = 1; i < args.length; i++) {
                                message += args[i] + " ";
                            }
                            sender.sendMessage("§7[§a§lVous" + "§r §6--> §a§l" + tar.rpPrenom + " §2§l" + tar.rpNom + " §7(" + target.getName() + ")] §f" + message);

                            Pattern pt = Pattern.compile("\\§+.");
                            Matcher match = pt.matcher("§7[§a§l" + send.rpPrenom + " §2§l" + send.rpNom + " §7(" + sender.getName() + "§7)" + "§r §6--> §a§l" + tar.rpPrenom + " §2§l" + tar.rpNom + " §7(" + target.getName() + ")] §f" + message);
                            String output = match.replaceAll("");
                            YMLUtil.log(output, null, null);

                            for(Player pl : Bukkit.getOnlinePlayers()) {
                                PlayerData pData = Data.data.getUserData(pl);
                                if(pData.spyMsg || pData.spyChatGlobal) pl.sendMessage("§c§l[SC] §7[§a§l" + send.rpPrenom + " §2§l" + send.rpNom + " §7(" + sender.getName() + "§7)" + "§r §6--> §a§l" + tar.rpPrenom + " §2§l" + tar.rpNom + " §7(" + target.getName() + ")] §f" + message);
                            }
                            PlayerData data = Data.data.getUserData(target);
                            if (!data.ignoredPlayers.contains(sender.getName())) {
                                target.sendMessage("§7[§a§l" + send.rpPrenom + " §2§l" + send.rpNom + " §7(" + sender.getName() + "§7)" + "§r §6--> §a§lMoi§7] §f" + message);
                            }
                        } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cCette personne n'autorise pas les messages privé");
                    } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! " + target.getName() + " n'est pas en ligne!");
                } else {
                    if (target.isOnline()) {
                        PlayerData tar = Data.data.getUserData(target);
                        if (tar.allowMsg) {
                            tar.lastDm = (Player) sender;
                            String message = "";
                            for (int i = 1; i < args.length; i++) {
                                message += args[i] + " ";
                            }
                            sender.sendMessage("§7[§a§lVous" + "§r §6--> §a§l" + tar.rpPrenom + " §2§l" + tar.rpNom + " §7(" + target.getName() + ")] §f" + message);
                            PlayerData data = Data.data.getUserData(target);
                            if (!data.ignoredPlayers.contains(sender.getName())) {
                                target.sendMessage("§7[§c§lCONSOLE" + "§r §6--> §a§lMoi§7] §f" + message);
                            }
                        } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cCette personne n'autorise pas les messages privé");
                    } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! " + target.getName() + " n'est pas en ligne!");
                }
            }
        }

        return true;
    }
}
