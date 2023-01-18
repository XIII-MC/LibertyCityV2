package com.xiii.libertycity.core.commands.punish;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.data.ServerData;
import com.xiii.libertycity.core.utils.APIUtils;
import com.xiii.libertycity.core.utils.AlertUtil;
import com.xiii.libertycity.core.utils.TimeUtil;
import com.xiii.libertycity.core.utils.YMLUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class MuteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("mute")) {

            ServerData server = Data.data.getServerData(Bukkit.getServer());

            if(args.length < 2) sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! Usage: /mute <Joueur> <Perm/Durée> (Raison) (-s) (-ip)");

            // Only if player and duration are specified
            if(args.length >= 2) {

                UUID targetUUID = APIUtils.getSafeUUID(args[0]);
                OfflinePlayer target = Bukkit.getOfflinePlayer(targetUUID);
                PlayerData targetData = Data.data.getUserData(targetUUID.toString());

                // If duration is permanent
                if(args[1].equalsIgnoreCase("perm") && targetData != null) {

                    // Write down muted variables
                    targetData.isMuted = true;
                    targetData.isSilentMute = false;
                    targetData.muteReason = "Non Spécifiée";
                    targetData.muteDate = TimeUtil.getFullDate();
                    targetData.muteDuration = -1L;
                    targetData.mutedBy = sender.getName();

                    // If no custom reason is specified
                    if(args.length <= 2) {

                        // Run ban on the server
                        server.mutedPlayer.add(args[0]);
                        YMLUtil.log("MUTE | " + target.getName() + " a était rendu muet par " + sender.getName(), "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                        if(targetData.onlineState) ((Player) target).sendMessage("§8§m+--------------------------+§r" + "\n" + "            §4§lSANCTION§r" + "\n" + "\n" + "§f§l⋅ §7Muet(te) le §f» §c" + targetData.muteDate + "\n" + "§f§l⋅ §7Muet(te) par §f» §c" + targetData.mutedBy + "\n" + "§f§l⋅ §7Muet(te) jusqu'au §f» §cPermanant" + "\n" + "§f§l⋅ §7Raison §f» §cNon Specifiée" + "\n" + "\n" + "§f§l⋅ §7Contestations §f» §bdiscord.gg/LibertyCity" + "\n" + "§8§m+--------------------------+");
                        Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §c" + target.getName() + " §fa été rendu muet!");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu muet §8" + target.getName(), "LibertyCity.staff.alert", 0);

                    } else {

                        // Get FULL custom reason
                        String newStringConverted = "";
                        for (int i = 2; i < args.length; i++) {
                            newStringConverted += args[i] + " ";
                        }
                        String kickReason = "";
                        kickReason = newStringConverted.replace("-s", "").replace("-ip", "").replace("-hrp", "").replace("-rp", "").replace("-police", "").replace("-gang", "");

                        // Update variables
                        if(newStringConverted.contains("-s")) targetData.isSilentMute = true;
                        if(newStringConverted.contains("-rp")) targetData.chatBanRP = true;
                        if(newStringConverted.contains("-hrp")) targetData.chatBanHRP = true;
                        if(newStringConverted.contains("-police")) targetData.chatBanPolice = true;
                        if(newStringConverted.contains("-gang")) targetData.chatBanPolice = true;
                        if(kickReason.length() < 1) kickReason = "Non Spécifiée";
                        targetData.muteReason = kickReason;

                        // Run ban on the server
                        if(newStringConverted.contains("-ip")) {

                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (Objects.equals(p.getAddress().getHostName(), target.getPlayer().getAddress().getHostName())) {

                                    // Register new ban if multiple accounts with the same ip are online
                                    PlayerData pData = Data.data.getUserData(p);
                                    pData.isMuted = targetData.isMuted;
                                    pData.chatBanRP = targetData.chatBanRP;
                                    pData.chatBanHRP = targetData.chatBanHRP;
                                    pData.chatBanPolice = targetData.chatBanPolice;
                                    pData.chatBanGang = targetData.chatBanGang;
                                    pData.isSilentMute = targetData.isSilentMute;
                                    pData.muteReason = targetData.muteReason;
                                    pData.muteDate = targetData.muteDate;
                                    pData.muteDuration = targetData.muteDuration;
                                    pData.muteExpiration = targetData.muteExpiration;
                                    pData.muteDisplayDate = targetData.muteDisplayDate;
                                    pData.mutedBy = targetData.mutedBy;

                                    p.sendMessage("§8§m+--------------------------+§r" + "\n" + "            §4§lSANCTION§r" + "\n" + "\n" + "§f§l⋅ §7Muet(te) le §f» §c" + pData.muteDate + "\n" + "§f§l⋅ §7Muet(te) par §f» §c" + targetData.mutedBy + "\n" + "§f§l⋅ §7Muet(te) jusqu'au §f» §cPermanant" + "\n" + "§f§l⋅ §7Raison §f» §c" + pData.muteReason + "\n" + "\n" + "§f§l⋅ §7Contestations §f» §bdiscord.gg/LibertyCity" + "\n" + "§8§m+--------------------------+");
                                    server.mutedIPs.add(p.getAddress().getHostName());
                                    if(!pData.isSilentMute) YMLUtil.log("MUTE IP | " + p.getName() + " a était rendu muet par " + sender.getName() + ", raison: " + pData.muteReason, "/server/punishments/", "/server/punishments/" + p.getUniqueId() + ".yml");
                                    else YMLUtil.log("MUTE IP | " + p.getName() + " a était rendu muet silencieusement par " + sender.getName() + ", raison: " + pData.muteReason, "/server/punishments/", "/server/punishments/" + p.getUniqueId() + ".yml");
                                }
                            }
                        } else {
                            server.mutedPlayer.add(args[0]);
                            if(!targetData.isSilentMute) YMLUtil.log("MUTE | " + target.getName() + " a était rendu muet par " + sender.getName() + ", raison: " + targetData.muteReason, "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                            else YMLUtil.log("MUTE | " + target.getName() + " a était rendu muet silencieusement par " + sender.getName() + ", raison: " + targetData.muteReason, "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                            if(targetData.onlineState) ((Player) target).sendMessage("§8§m+--------------------------+§r" + "\n" + "            §4§lSANCTION§r" + "\n" + "\n" + "§f§l⋅ §7Muet(te) le §f» §c" + targetData.muteDate + "\n" + "§f§l⋅ §7Muet(te) par §f» §c" + targetData.mutedBy + "\n" + "§f§l⋅ §7Muet(te) jusqu'au §f» §cPermanant" + "\n" + "§f§l⋅ §7Raison §f» §c" + targetData.muteReason + "\n" + "\n" + "§f§l⋅ §7Contestations §f» §bdiscord.gg/LibertyCity" + "\n" + "§8§m+--------------------------+");
                            AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu muet §8" + target.getName() + " §7, raison §8" + targetData.banReason, "LibertyCity.staff.alert", 0);
                        }
                        if(!targetData.isSilentMute) Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §c" + target.getName() + " §fa été rendu muet!");
                        if(targetData.isSilentMute) AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu muet §8" + target.getName() + " §7silentcieusement, raison §8" + targetData.banReason, "LibertyCity.staff.alert", 0);
                        if(newStringConverted.contains("-ip")) AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu muet l'IP de §8" + target.getName() + " §7, raison §8" + targetData.banReason, "LibertyCity.staff.alert", 0);

                    }

                }

                if(!args[1].equalsIgnoreCase("perm") && targetData != null) {

                    // Write down muted variables
                    targetData.isMuted = true;
                    targetData.isSilentMute = false;
                    targetData.muteReason = "Non Spécifiée";
                    targetData.muteDate = TimeUtil.getFullDate();

                    // Calculate mute duration
                    long muteDuration = 0;
                    String muteDurationConverter = null;

                    // Seconds
                    if(args[1].contains("s")) {
                        muteDurationConverter = args[1].replace("s", "");
                        muteDuration = Integer.parseInt(muteDurationConverter) * 1000L;
                    }

                    // Minutes
                    if(args[1].contains("min")) {
                        muteDurationConverter = args[1].replace("min", "");
                        muteDuration = Integer.parseInt(muteDurationConverter) * 60000L;
                    }

                    // Hours
                    if(args[1].contains("h")) {
                        muteDurationConverter = args[1].replace("h", "");
                        muteDuration = Integer.parseInt(muteDurationConverter) * 3600000L;
                    }

                    // Days
                    if(args[1].contains("d")) {
                        muteDurationConverter = args[1].replace("d", "");
                        muteDuration = Integer.parseInt(muteDurationConverter) * 86400000L;
                    }

                    // Weeks
                    if(args[1].contains("w")) {
                        muteDurationConverter = args[1].replace("w", "");
                        muteDuration = Integer.parseInt(muteDurationConverter) * 604800016L;
                    }

                    // Months
                    if(args[1].contains("mo")) {
                        muteDurationConverter = args[1].replace("mo", "");
                        muteDuration = Integer.parseInt(muteDurationConverter) * 2629800000L;
                    }

                    // Years
                    if(args[1].contains("y")) {
                        muteDurationConverter = args[1].replace("y", "");
                        muteDuration = Integer.parseInt(muteDurationConverter) * 31557600000L;
                    }

                    Date muteExpireDate = new Date(System.currentTimeMillis() + muteDuration);
                    targetData.muteExpiration = muteExpireDate;
                    targetData.muteDuration = System.currentTimeMillis() + muteDuration;

                    // Convert to proper date
                    final String displayTime = muteExpireDate.toString().substring(11, muteExpireDate.toString().length() - 9);
                    final String displayMonth = muteExpireDate.toString().substring(4, muteExpireDate.toString().length() - 21).replace("Jan", "01").replace("Feb", "02").replace("Mar", "03").replace("Apr", "04").replace("May", "05").replace("Jun", "06").replace("Jul", "07").replace("Aug", "08").replace("Sep", "09").replace("Oct", "10").replace("Nov", "11").replace("Dec", "12");
                    final String displayDay = muteExpireDate.toString().substring(8, muteExpireDate.toString().length() - 18);
                    final String displayYear = muteExpireDate.toString().substring(24);
                    targetData.muteDisplayDate = displayDay + "/" + displayMonth + "/" + displayYear + " " + displayTime;

                    targetData.mutedBy = sender.getName();

                    // If no custom reason is specified
                    if(args.length <= 2) {

                        server.mutedPlayer.add(args[0]);
                        YMLUtil.log("MUTE | " + target.getName() + " a était rendu muet par " + sender.getName() + ", jusqu'au " + targetData.muteDisplayDate, "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                        if(targetData.onlineState) ((Player) target).sendMessage("§8§m+--------------------------+§r" + "\n" + "            §4§lSANCTION§r" + "\n" + "\n" + "§f§l⋅ §7Muet(te) le §f» §c" + targetData.muteDate + "\n" + "§f§l⋅ §7Muet(te) par §f» §c" + targetData.mutedBy + "\n" + "§f§l⋅ §7Muet(te) jusqu'au §f» §c" + targetData.muteDisplayDate + "\n" + "§f§l⋅ §7Raison §f» §cNon Specifiée" + "\n" + "\n" + "§f§l⋅ §7Contestations §f» §bdiscord.gg/LibertyCity" + "\n" + "§8§m+--------------------------+");
                        Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §c" + target.getName() + " §fa été rendu muet!");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu muet §8" + target.getName() + " §7jusqu'au §8" + targetData.muteDisplayDate, "LibertyCity.staff.alert", 0);

                    } else {

                        // Get FULL custom reason
                        String newStringConverted = "";
                        for (int i = 2; i < args.length; i++) {
                            newStringConverted += args[i] + " ";
                        }
                        String kickReason = "";
                        kickReason = newStringConverted.replace("-s", "").replace("-ip", "").replace("-hrp", "").replace("-rp", "").replace("-police", "").replace("-gang", "");

                        // Update variables
                        if(newStringConverted.contains("-s")) targetData.isSilentMute = true;
                        if(newStringConverted.contains("-rp")) targetData.chatBanRP = true;
                        if(newStringConverted.contains("-hrp")) targetData.chatBanHRP = true;
                        if(newStringConverted.contains("-police")) targetData.chatBanPolice = true;
                        if(newStringConverted.contains("-gang")) targetData.chatBanPolice = true;
                        if(kickReason.length() < 1) kickReason = "Non Spécifiée";
                        targetData.muteReason = kickReason;

                        // Run ban on the server
                        if(newStringConverted.contains("-ip")) {

                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (Objects.equals(p.getAddress().getHostName(), target.getPlayer().getAddress().getHostName())) {

                                    // Register new ban if multiple accounts with the same ip are online
                                    PlayerData pData = Data.data.getUserData(p);
                                    pData.isMuted = targetData.isMuted;
                                    pData.chatBanRP = targetData.chatBanRP;
                                    pData.chatBanHRP = targetData.chatBanHRP;
                                    pData.chatBanPolice = targetData.chatBanPolice;
                                    pData.chatBanGang = targetData.chatBanGang;
                                    pData.isSilentMute = targetData.isSilentMute;
                                    pData.muteReason = targetData.muteReason;
                                    pData.muteDate = targetData.muteDate;
                                    pData.muteDuration = targetData.muteDuration;
                                    pData.muteExpiration = targetData.muteExpiration;
                                    pData.muteDisplayDate = targetData.muteDisplayDate;
                                    pData.mutedBy = targetData.mutedBy;

                                    p.sendMessage("§8§m+--------------------------+§r" + "\n" + "            §4§lSANCTION§r" + "\n" + "\n" + "§f§l⋅ §7Muet(te) le §f» §c" + targetData.muteDate + "\n" + "§f§l⋅ §7Muet(te) par §f» §c" + targetData.mutedBy + "\n" + "§f§l⋅ §7Muet(te) jusqu'au §f» §c" + targetData.muteDisplayDate + "\n" + "§f§l⋅ §7Raison §f» §c" + targetData.muteReason + "\n" + "\n" + "§f§l⋅ §7Contestations §f» §bdiscord.gg/LibertyCity" + "\n" + "§8§m+--------------------------+");
                                    server.mutedIPs.add(p.getAddress().getHostName());
                                    if(!pData.isSilentMute) YMLUtil.log("MUTE IP | " + p.getName() + " a était rendu muet par " + sender.getName() + ", jusqu'au " + pData.banDisplayDate + ", raison: " + pData.muteReason,  "/server/punishments/", "/server/punishments/" + p.getUniqueId() + ".yml");
                                    else YMLUtil.log("MUTE IP | " + p.getName() + " a était rendu muet silencieusement par " + sender.getName() + ", jusqu'au " + pData.banDisplayDate + ", raison: " + pData.muteReason,  "/server/punishments/", "/server/punishments/" + p.getUniqueId() + ".yml");
                                }
                            }
                        } else {
                            server.mutedPlayer.add(args[0]);
                            if(!targetData.isSilentMute) YMLUtil.log("MUTE | " + target.getName() + " a était rendu muet par " + sender.getName() + ", jusqu'au " + targetData.muteDisplayDate + ", raison: " + targetData.muteReason, "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                            else YMLUtil.log("MUTE | " + target.getName() + " a était rendu muet silencieusement par " + sender.getName() + ", jusqu'au " + targetData.muteDisplayDate + ", raison: " + targetData.muteReason, "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                            if(targetData.onlineState) ((Player) target).sendMessage("§8§m+--------------------------+§r" + "\n" + "            §4§lSANCTION§r" + "\n" + "\n" + "§f§l⋅ §7Muet(te) le §f» §c" + targetData.muteDate + "\n" + "§f§l⋅ §7Muet(te) par §f» §c" + targetData.mutedBy + "\n" + "§f§l⋅ §7Muet(te) jusqu'au §f» §c" + targetData.muteDisplayDate + "\n" + "§f§l⋅ §7Raison §f» §c" + targetData.muteReason + "\n" + "\n" + "§f§l⋅ §7Contestations §f» §bdiscord.gg/LibertyCity" + "\n" + "§8§m+--------------------------+");
                            AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu muet §8" + target.getName() + " §7silentcieusement, raison §8" + targetData.banReason + " §7jusqu'au §8" + targetData.muteDisplayDate, "LibertyCity.staff.alert", 0);
                        }
                        if(!targetData.isSilentMute) Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §c" + target.getName() + " §fa été rendu muet!");
                        if(targetData.isSilentMute) AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu muet §8" + target.getName() + " §7silentcieusement, raison §8" + targetData.banReason + " §7jusqu'au §8" + targetData.muteDisplayDate, "LibertyCity.staff.alert", 0);
                        if(newStringConverted.contains("-ip")) AlertUtil.staffAlert("§8" + sender.getName() + " §7a rendu muet l'IP de §8" + target.getName() + " §7, raison §8" + targetData.banReason + " §7jusqu'au §8" + targetData.muteDisplayDate, "LibertyCity.staff.alert", 0);

                    }
                }

            }

        }

        return true;
    }
}
