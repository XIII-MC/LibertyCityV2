package com.xiii.libertycity.core.commands.punish;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.utils.AlertUtil;
import com.xiii.libertycity.core.utils.TimeUtil;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class BanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("ban")) {

            BanList banList = Bukkit.getBanList(BanList.Type.NAME);
            BanList banListIP = Bukkit.getBanList(BanList.Type.IP);

            if(args.length < 2) sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Usage: /ban <Joueur> <Perm/Durée> (Raison) (-s) (-ip) (-uuid)");

            // Only player and duration are specified
            if(args.length >= 2) {

                OfflinePlayer target = Bukkit.getServer().getPlayer(args[0]);
                PlayerData targetData = Data.data.getUserData((Player) target);

                // Null safety
                if(targetData == null) {
                    banList.addBan(target.getName(), "§8§m+--------------------------+" + "\n" + "\n" + "§f§l⋅ §7Banni(e) le §f» §c" + TimeUtil.getFullDate() + "\n" + "§f§l⋅ §7Banni(e) par §f» §c" + sender.getName() + "\n" + "§f§l⋅ §7Raison §f» §cNon Specifiée" + "\n" + "\n" + "§f§l⋅ §c§lCette sanction vous est permanente!" + "\n" + "\n" + "§f§l⋅ §7Si vous souhaiter contésté cette sanction §f» §bdiscord.gg/LibertyCity" + "\n" + "\n" + "§8§m+--------------------------+", null, sender.getName());
                    AlertUtil.staffAlert("§8" + sender.getName() + " §7a banni §8" + target.getName(), "LibertyCity.staff.alert", 0);
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! Comme ce joueur ne c'est jamais connecté, il a était banni a vie avec une raison non spécifiée. Vous ne pouvez pas changer la durée ni la raison du bannissement.");
                }

                // If duration is permanent
                if(args[1].equalsIgnoreCase("perm") && targetData != null) {

                    // Write down banned variables
                    targetData.isSilentBan = false;
                    targetData.banReason = "Non Spécifiée";
                    targetData.banDate = TimeUtil.getFullDate();
                    targetData.banDuration = "Permanent";
                    targetData.bannedBy = sender.getName();

                    // If no custom reason is specified
                    if(args.length <= 2) {

                        // Run ban on the server
                        ((Player) target).kickPlayer("§8§m+--------------------------+" + "\n" + "\n" + "§f§l⋅ §7Banni(e) le §f» §c" + targetData.banDate + "\n" + "§f§l⋅ §7Banni(e) par §f» §c" + targetData.bannedBy + "\n" + "§f§l⋅ §7Raison §f» §cNon Specifiée" + "\n" + "\n" + "§f§l⋅ §c§lCette sanction vous est permanente!" + "\n" + "\n" + "§f§l⋅ §7Si vous souhaiter contésté cette sanction §f» §bdiscord.gg/LibertyCity" + "\n" + "\n" + "§8§m+--------------------------+");
                        banList.addBan(target.getName(), "§8§m+--------------------------+" + "\n" + "\n" + "§f§l⋅ §7Banni(e) le §f» §c" + targetData.banDate + "\n" + "§f§l⋅ §7Banni(e) par §f» §c" + targetData.bannedBy + "\n" + "§f§l⋅ §7Raison §f» §cNon Specifiée" + "\n" + "\n" + "§f§l⋅ §c§lCette sanction vous est permanente!" + "\n" + "\n" + "§f§l⋅ §7Si vous souhaiter contésté cette sanction §f» §bdiscord.gg/LibertyCity" + "\n" + "\n" + "§8§m+--------------------------+", null, sender.getName());
                        Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §c" + target.getName() + " §fa été banni du serveur!");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7a banni §8" + target.getName(), "LibertyCity.staff.alert", 0);

                    } else {

                        // Get FULL custom reason
                        String newStringConverted = "";
                        for (int i = 2; i < args.length; i++) {
                            newStringConverted += args[i] + " ";
                        }
                        String kickReason = "";
                        kickReason = newStringConverted.replaceAll("-s -ip", "");

                        // Update variables
                        if(newStringConverted.contains("-s")) targetData.isSilentBan = true;
                        if(kickReason.length() < 1) kickReason = "Non Spécifiée";
                        targetData.banReason = kickReason;

                        // Run ban on the server
                        ((Player) target).kickPlayer("§8§m+--------------------------+" + "\n" + "\n" + "§f§l⋅ §7Banni(e) le §f» §c" + targetData.banDate + "\n" + "§f§l⋅ §7Banni(e) par §f» §c" + targetData.bannedBy + "\n" + "§f§l⋅ §7Raison §f» §c" + targetData.banReason + "\n" + "\n" + "§f§l⋅ §c§lCette sanction vous est permanente!" + "\n" + "\n" + "§f§l⋅ §7Si vous souhaiter contésté cette sanction §f» §bdiscord.gg/LibertyCity" + "\n" + "\n" + "§8§m+--------------------------+");
                        if(newStringConverted.contains("-ip")) banListIP.addBan(target.getPlayer().getAddress().getHostName(), "§8§m+--------------------------+" + "\n" + "\n" + "§f§l⋅ §7Banni(e) le §f» §c" + targetData.banDate + "\n" + "§f§l⋅ §7Banni(e) par §f» §c" + targetData.bannedBy + "\n" + "§f§l⋅ §7Raison §f» §c" + targetData.banReason + "\n" + "\n" + "§f§l⋅ §c§lCette sanction vous est permanente!" + "\n" + "\n" + "§f§l⋅ §7Si vous souhaiter contésté cette sanction §f» §bdiscord.gg/LibertyCity" + "\n" + "\n" + "§8§m+--------------------------+", null, sender.getName());
                        banList.addBan(target.getName(), "§8§m+--------------------------+" + "\n" + "\n" + "§f§l⋅ §7Banni(e) le §f» §c" + targetData.banDate + "\n" + "§f§l⋅ §7Banni(e) par §f» §c" + targetData.bannedBy + "\n" + "§f§l⋅ §7Raison §f» §c" + targetData.banReason + "\n" + "\n" + "§f§l⋅ §c§lCette sanction vous est permanente!" + "\n" + "\n" + "§f§l⋅ §7Si vous souhaiter contésté cette sanction §f» §bdiscord.gg/LibertyCity" + "\n" + "\n" + "§8§m+--------------------------+", null, sender.getName());
                        if(!targetData.isSilentBan) Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §c" + target.getName() + " §fa été banni du serveur!");
                        if(targetData.isSilentBan) AlertUtil.staffAlert("§8" + sender.getName() + " §7a banni §8" + target.getName() + " §7silentcieusement, raison §8" + targetData.banReason, "LibertyCity.staff.alert", 0);
                        if(newStringConverted.contains("-ip")) AlertUtil.staffAlert("§8" + sender.getName() + " §7a banni l'IP de §8" + target.getName() + " §7, raison §8" + targetData.banReason, "LibertyCity.staff.alert", 0);

                    }
                }

                // If duration isn't permanent
                if(!args[1].equalsIgnoreCase("perm") && targetData != null) {

                    // Write down banned variables
                    targetData.isSilentBan = false;
                    targetData.banReason = "Non Spécifiée";
                    targetData.banDate = TimeUtil.getFullDate();

                    // Calculate ban duration
                    int banDuration = 0;
                    String banDurationConverter = null;

                    // Seconds
                    if(args[1].contains("s")) {
                        banDurationConverter = args[1].replace("s", "");
                        banDuration = Integer.parseInt(banDurationConverter) * 1000;
                    }

                    // Minutes
                    if(args[1].contains("m")) {
                        banDurationConverter = args[1].replace("m", "");
                        banDuration = Integer.parseInt(banDurationConverter) * 60000;
                    }

                    // Hours
                    if(args[1].contains("h")) {
                        banDurationConverter = args[1].replace("h", "");
                        banDuration = Integer.parseInt(banDurationConverter) * 3600000;
                    }

                    // Days
                    if(args[1].contains("h")) {
                        banDurationConverter = args[1].replace("d", "");
                        banDuration = Integer.parseInt(banDurationConverter) * 86400000;
                    }

                    // Weeks
                    if(args[1].contains("w")) {
                        banDurationConverter = args[1].replace("w", "");
                        banDuration = Integer.parseInt(banDurationConverter) * 604800016;
                    }

                    // Months
                    if(args[1].contains("mo")) {
                        banDurationConverter = args[1].replace("mo", "");
                        banDuration = (int) (Integer.parseInt(banDurationConverter) * 26298E5);
                    }

                    // Years
                    if(args[1].contains("y")) {
                        banDurationConverter = args[1].replace("y", "");
                        banDuration = (int) (Integer.parseInt(banDurationConverter) * 315576E5);
                    }

                    Date banExpireDate = new Date(System.currentTimeMillis() + banDuration);
                    targetData.banExpiration = banExpireDate;

                    // Convert ms to date
                    long milliSeconds = banDuration;
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date(milliSeconds);

                    targetData.banDuration = dateFormat.format(date);
                    targetData.bannedBy = sender.getName();

                    // If no custom reason is specified
                    if(args.length <= 2) {

                        // Run ban on the server
                        ((Player) target).kickPlayer("§8§m+--------------------------+" + "\n" + "\n" + "§f§l⋅ §7Banni(e) le §f» §c" + targetData.banDate + "\n" + "§f§l⋅ §7Banni(e) par §f» §c" + targetData.bannedBy + "\n" + "§f§l⋅ §7Raison §f» §cNon Specifiée" + "\n" + "\n" + "§f§l⋅ §c§lCette sanction expira le " + targetData.banExpiration + "\n" + "\n" + "§f§l⋅ §7Si vous souhaiter contésté cette sanction §f» §bdiscord.gg/LibertyCity" + "\n" + "\n" + "§8§m+--------------------------+");
                        banList.addBan(target.getName(), "§8§m+--------------------------+" + "\n" + "\n" + "§f§l⋅ §7Banni(e) le §f» §c" + targetData.banDate + "\n" + "§f§l⋅ §7Banni(e) par §f» §c" + targetData.bannedBy + "\n" + "§f§l⋅ §7Raison §f» §cNon Specifiée" + "\n" + "\n" + "§f§l⋅ §c§lCette sanction expira le " + targetData.banExpiration + "\n" + "\n" + "§f§l⋅ §7Si vous souhaiter contésté cette sanction §f» §bdiscord.gg/LibertyCity" + "\n" + "\n" + "§8§m+--------------------------+", targetData.banExpiration, sender.getName());
                        Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §c" + target.getName() + " §fa été banni du serveur!");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7a banni §8" + target.getName() + " §7pour une durée de §8" + args[1], "LibertyCity.staff.alert", 0);

                    } else {

                        // Get FULL custom reason
                        String newStringConverted = "";
                        for (int i = 2; i < args.length; i++) {
                            newStringConverted += args[i] + " ";
                        }
                        String kickReason = "";
                        kickReason = newStringConverted.replaceAll("-s -ip", "");

                        // Update variables
                        if(newStringConverted.contains("-s")) targetData.isSilentBan = true;
                        if(kickReason.length() < 1) kickReason = "Non Spécifiée";
                        targetData.banReason = kickReason;

                        // Run ban on the server
                        ((Player) target).kickPlayer("§8§m+--------------------------+" + "\n" + "\n" + "§f§l⋅ §7Banni(e) le §f» §c" + targetData.banDate + "\n" + "§f§l⋅ §7Banni(e) par §f» §c" + targetData.bannedBy + "\n" + "§f§l⋅ §7Raison §f» §c" + targetData.banReason + "\n" + "\n" + "§f§l⋅ §c§lCette sanction expira le " + targetData.banExpiration + "\n" + "\n" + "§f§l⋅ §7Si vous souhaiter contésté cette sanction §f» §bdiscord.gg/LibertyCity" + "\n" + "\n" + "§8§m+--------------------------+");
                        if(newStringConverted.contains("-ip")) banListIP.addBan(target.getName(), "§8§m+--------------------------+" + "\n" + "\n" + "§f§l⋅ §7Banni(e) le §f» §c" + targetData.banDate + "\n" + "§f§l⋅ §7Banni(e) par §f» §c" + targetData.bannedBy + "\n" + "§f§l⋅ §7Raison §f» §c" + targetData.banReason + "\n" + "\n" + "§f§l⋅ §c§lCette sanction expira le " + targetData.banExpiration + "\n" + "\n" + "§f§l⋅ §7Si vous souhaiter contésté cette sanction §f» §bdiscord.gg/LibertyCity" + "\n" + "\n" + "§8§m+--------------------------+", targetData.banExpiration, sender.getName());
                        banList.addBan(target.getName(), "§8§m+--------------------------+" + "\n" + "\n" + "§f§l⋅ §7Banni(e) le §f» §c" + targetData.banDate + "\n" + "§f§l⋅ §7Banni(e) par §f» §c" + targetData.bannedBy + "\n" + "§f§l⋅ §7Raison §f» §c" + targetData.banReason + "\n" + "\n" + "§f§l⋅ §c§lCette sanction expira le " + targetData.banExpiration + "\n" + "\n" + "§f§l⋅ §7Si vous souhaiter contésté cette sanction §f» §bdiscord.gg/LibertyCity" + "\n" + "\n" + "§8§m+--------------------------+", targetData.banExpiration, sender.getName());
                        if(!targetData.isSilentBan) Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §c" + target.getName() + " §fa été banni du serveur!");
                        if(targetData.isSilentBan) AlertUtil.staffAlert("§8" + sender.getName() + " §7a banni §8" + target.getName() + " §7silentcieusement, raison §8" + targetData.banReason + " §7pour une durée de §8" + args[1], "LibertyCity.staff.alert", 0);
                        if(newStringConverted.contains("-ip")) AlertUtil.staffAlert("§8" + sender.getName() + " §7a banni l'IP de §8" + target.getName() + " §7, raison §8" + targetData.banReason + " §7pour une durée de §8" + args[1], "LibertyCity.staff.alert", 0);

                    }
                }

            }

        }

        return true;
    }
}
