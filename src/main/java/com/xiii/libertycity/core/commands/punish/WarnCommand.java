package com.xiii.libertycity.core.commands.punish;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class WarnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("warn")) {

            if(args.length < 2) sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Usage: /warn <Joueur> <Perm/Durée> (Raison) (-s)");

            // Only player and duration are specified
            if(args.length >= 2) {

                UUID targetUUID = APIUtils.getSafeUUID(args[0]);
                OfflinePlayer target = Bukkit.getOfflinePlayer(targetUUID);
                PlayerData targetData = Data.data.getUserData(targetUUID.toString());

                // Null safety
                if(targetData == null) sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! Comme ce joueur ne c'est jamais connecté, aucun avertissement n'a était appliqué.");

                // If duration is permanent
                if(args[1].equalsIgnoreCase("perm") && targetData != null) {

                    // Write down banned variables
                    targetData.isSilentWarn = false;
                    targetData.warnReason = "Non Spécifiée";
                    targetData.warnDate = TimeUtil.getFullDate();
                    targetData.warnDuration = "Permanent";
                    targetData.warnedBy = sender.getName();

                    // If no custom reason is specified
                    if(args.length <= 2) {

                        // Run warn on the server
                        if(targetData.onlineState) ((Player)target).sendMessage("§8§m+--------------------------+§r" + "\n" + "            §4§lSANCTION§r" + "\n" + "\n" + "§f§l⋅ §7Avertis le §f» §c" + targetData.warnDate + "\n" + "§f§l⋅ §7Avertis par §f» §c" + targetData.warnedBy + "\n" + "§f§l⋅ §7Avertissement jusqu'au §f» §cPermanant" + "\n" + "§f§l⋅ §7Raison §f» §c" + targetData.warnReason + "\n" + "\n" + "§f§l⋅ §7Contestations §f» §bdiscord.gg/LibertyCity" + "\n" + "§8§m+--------------------------+");
                        YMLUtil.log("WARN | " + target.getName() + " a était avertis par " + sender.getName(), "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                        targetData.warns.add(target.getName() + " a était avertis par " + sender.getName());
                        Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §c" + target.getName() + " §fa reçu un avertissement!");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7a avertis §8" + target.getName(), "LibertyCity.staff.alert", 0);

                    } else {

                        // Get FULL custom reason
                        String newStringConverted = "";
                        for (int i = 2; i < args.length; i++) {
                            newStringConverted += args[i] + " ";
                        }
                        String kickReason = "";
                        kickReason = newStringConverted.replace("-s", "");

                        // Update variables
                        if(newStringConverted.contains("-s")) targetData.isSilentBan = true;
                        if(kickReason.length() < 1) kickReason = "Non Spécifiée";
                        targetData.warnReason = kickReason;

                        if(targetData.onlineState) ((Player)target).sendMessage("§8§m+--------------------------+§r" + "\n" + "            §4§lSANCTION§r" + "\n" + "\n" + "§f§l⋅ §7Avertis le §f» §c" + targetData.warnDate + "\n" + "§f§l⋅ §7Avertis par §f» §c" + targetData.warnedBy + "\n" + "§f§l⋅ §7Avertissement jusqu'au §f» §cPermanant" + "\n" + "§f§l⋅ §7Raison §f» §c" + targetData.warnReason + "\n" + "\n" + "§f§l⋅ §7Contestations §f» §bdiscord.gg/LibertyCity" + "\n" + "§8§m+--------------------------+");
                        if(!targetData.isSilentWarn) {
                            AlertUtil.staffAlert("§8" + sender.getName() + " §7a avertis §8" + target.getName() + "§7, raison: " + targetData.warnReason, "LibertyCity.staff.alert", 0);
                            YMLUtil.log("WARN | " + target.getName() + " a était avertis par " + sender.getName() + ", raison: " + targetData.warnReason, "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                            Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §c" + target.getName() + " §fa reçu un avertissement!");
                            targetData.warns.add(target.getName() + " a était avertis par " + sender.getName() + ", raison: " + targetData.warnReason);
                        } else {
                            AlertUtil.staffAlert("§8 " + sender.getName() + " §7a avertis silencieusement §8" + target.getName() + "§7, raison: " + targetData.warnReason, "LibertyCity.staff.alert", 0);
                            YMLUtil.log("WARN | " + target.getName() + " a était avertis silencieusement par " + sender.getName() + ", raison: " + targetData.warnReason, "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                            targetData.warns.add(target.getName() + " a était avertis silencieusement par " + sender.getName() + ", raison: " + targetData.warnReason);
                        }
                    }
                }

                // If duration isn't permanent
                if(!args[1].equalsIgnoreCase("perm") && targetData != null) {

                    // Write down banned variables
                    targetData.isSilentWarn = false;
                    targetData.warnReason = "Non Spécifiée";
                    targetData.warnDate = TimeUtil.getFullDate();

                    // Calculate ban duration
                    long banDuration = 0;
                    String banDurationConverter = null;

                    // Seconds
                    if(args[1].contains("s")) {
                        banDurationConverter = args[1].replace("s", "");
                        banDuration = Integer.parseInt(banDurationConverter) * 1000L;
                    }

                    // Minutes
                    if(args[1].contains("min")) {
                        banDurationConverter = args[1].replace("min", "");
                        banDuration = Integer.parseInt(banDurationConverter) * 60000L;
                    }

                    // Hours
                    if(args[1].contains("h")) {
                        banDurationConverter = args[1].replace("h", "");
                        banDuration = Integer.parseInt(banDurationConverter) * 3600000L;
                    }

                    // Days
                    if(args[1].contains("d")) {
                        banDurationConverter = args[1].replace("d", "");
                        banDuration = Integer.parseInt(banDurationConverter) * 86400000L;
                    }

                    // Weeks
                    if(args[1].contains("w")) {
                        banDurationConverter = args[1].replace("w", "");
                        banDuration = Integer.parseInt(banDurationConverter) * 604800016L;
                    }

                    // Months
                    if(args[1].contains("mo")) {
                        banDurationConverter = args[1].replace("mo", "");
                        banDuration = Integer.parseInt(banDurationConverter) * 2629800000L;
                    }

                    // Years
                    if(args[1].contains("y")) {
                        banDurationConverter = args[1].replace("y", "");
                        banDuration = Integer.parseInt(banDurationConverter) * 31557600000L;
                    }

                    Date banExpireDate = new Date(System.currentTimeMillis() + (banDuration + 100L));
                    targetData.warnExpiration = banExpireDate;

                    // Convert ms to date
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date(banDuration);

                    // Convert to proper date
                    final String displayTime = banExpireDate.toString().substring(11, banExpireDate.toString().length() - 9);
                    final String displayMonth = banExpireDate.toString().substring(4, banExpireDate.toString().length() - 21).replace("Jan", "01").replace("Feb", "02").replace("Mar", "03").replace("Apr", "04").replace("May", "05").replace("Jun", "06").replace("Jul", "07").replace("Aug", "08").replace("Sep", "09").replace("Oct", "10").replace("Nov", "11").replace("Dec", "12");
                    final String displayDay = banExpireDate.toString().substring(8, banExpireDate.toString().length() - 18);
                    final String displayYear = banExpireDate.toString().substring(24);
                    targetData.warnDisplayDate = displayDay + "/" + displayMonth + "/" + displayYear + " " + displayTime;

                    targetData.warnDuration = dateFormat.format(date);
                    targetData.warnedBy = sender.getName();

                    // If no custom reason is specified
                    if(args.length <= 2) {

                        // Run warn on the server
                        ((Player)target).sendMessage("§8§m+--------------------------+§r" + "\n" + "            §4§lSANCTION§r" + "\n" + "\n" + "§f§l⋅ §7Avertis le §f» §c" + targetData.warnDate + "\n" + "§f§l⋅ §7Avertis par §f» §c" + targetData.warnedBy + "\n" + "§f§l⋅ §7Avertissement jusqu'au §f» §c" + targetData.warnDisplayDate + "\n" + "§f§l⋅ §7Raison §f» §c" + targetData.warnReason + "\n" + "\n" + "§f§l⋅ §7Contestations §f» §bdiscord.gg/LibertyCity" + "\n" + "§8§m+--------------------------+");
                        YMLUtil.log("WARN | " + target.getName() + " a était avertis par " + sender.getName() + ", jusqu'au " + targetData.warnDisplayDate, "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                        Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §c" + target.getName() + " §fa reçu un avertissement!");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7a avertis §8" + target.getName() + ", jusqu'au " + targetData.warnDisplayDate, "LibertyCity.staff.alert", 0);
                        targetData.warns.add(target.getName() + " a était avertis par " + sender.getName() + ", jusqu'au " + targetData.warnDisplayDate);

                    } else {

                        // Get FULL custom reason
                        String newStringConverted = "";
                        for (int i = 2; i < args.length; i++) {
                            newStringConverted += args[i] + " ";
                        }
                        String kickReason = "";
                        kickReason = newStringConverted.replace("-s", "").replace("-ip", "");

                        // Update variables
                        if(newStringConverted.contains("-s")) targetData.isSilentWarn = true;
                        if(kickReason.length() < 1) kickReason = "Non Spécifiée";
                        targetData.warnReason = kickReason;

                        if(targetData.onlineState) ((Player)target).sendMessage("§8§m+--------------------------+§r" + "\n" + "            §4§lSANCTION§r" + "\n" + "\n" + "§f§l⋅ §7Avertis le §f» §c" + targetData.warnDate + "\n" + "§f§l⋅ §7Avertis par §f» §c" + targetData.warnedBy + "\n" + "§f§l⋅ §7Avertissement jusqu'au §f» §c" + targetData.warnDisplayDate + "\n" + "§f§l⋅ §7Raison §f» §c" + targetData.warnReason + "\n" + "\n" + "§f§l⋅ §7Contestations §f» §bdiscord.gg/LibertyCity" + "\n" + "§8§m+--------------------------+");
                        if(!targetData.isSilentWarn) {
                            AlertUtil.staffAlert("§8" + sender.getName() + " §7a avertis §8" + target.getName() + "§7, jusqu'au " + targetData.warnDisplayDate + ", raison: " + targetData.warnReason, "LibertyCity.staff.alert", 0);
                            YMLUtil.log("WARN | " + target.getName() + " a était avertis par " + sender.getName() + ", jusqu'au " + targetData.warnDisplayDate + ", raison: " + targetData.warnReason, "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                            Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §c" + target.getName() + " §fa reçu un avertissement!");
                            targetData.warns.add(target.getName() + " a était avertis par " + sender.getName() + ", jusqu'au " + targetData.warnDisplayDate + ", raison: " + targetData.warnReason);
                        } else {
                            AlertUtil.staffAlert("§8 " + sender.getName() + " §7a avertis silencieusement §8" + target.getName() + "§7, jusqu'au " + targetData.warnDisplayDate + ", raison: " + targetData.warnReason, "LibertyCity.staff.alert", 0);
                            YMLUtil.log("WARN | " + target.getName() + " a était avertis silencieusement par " + sender.getName() + ", jusqu'au " + targetData.warnDisplayDate + ", raison: " + targetData.warnReason, "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                            targetData.warns.add(target.getName() + " a était avertis silencieusement par " + sender.getName() + ", jusqu'au " + targetData.warnDisplayDate + ", raison: " + targetData.warnReason);
                        }

                    }
                }

            }

        }

        return true;
    }
}
