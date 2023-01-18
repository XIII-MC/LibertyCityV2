package com.xiii.libertycity.core.commands.punish;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.utils.AlertUtil;
import com.xiii.libertycity.core.utils.TimeUtil;
import com.xiii.libertycity.core.utils.YMLUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("kick")) {
            if(args.length == 0) sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Usage: /kick <Joueur> (Raison) (-s)");
            else {
                try {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target.isOnline()) {
                        if (args.length == 1) {
                            target.kickPlayer("§8§m+--------------------------+" + "\n" + "\n" + "§f§l⋅ §7Explusé(e) le §f» §c" + TimeUtil.getFullDate() + "\n" + "§f§l⋅ §7Explusé(e) par §f» §c" + sender.getName() + "\n" + "§f§l⋅ §7Raison §f» §cNon Specifiée" + "\n" + "\n" + "§f§l⋅ §c§lCette sanction vous est permanente!" + "\n" + "\n" + "§f§l⋅ §7Si vous souhaiter contésté cette sanction §f» §bdiscord.gg/LibertyCity" + "\n" + "\n" + "§8§m+--------------------------+");
                            Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §c" + target.getName() + " §fa été explusé(e) du serveur!");
                            AlertUtil.staffAlert("§8" + sender.getName() + " §7a explusé(e) §8" + target.getName(), "LibertyCity.staff.alert", 0);
                            YMLUtil.log("KICK | " + target.getName() + " a était expulsée par " + sender.getName(), "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                        } else {

                            PlayerData data = Data.data.getUserData(target);

                            String newStringConverted = "";
                            for (int i = 1; i < args.length; i++) {
                                newStringConverted += args[i] + " ";
                            }
                            String kickReason = "";
                            kickReason = newStringConverted.replace("-s", "");

                            if (newStringConverted.contains("-s")) {
                                data.silentKick = true;
                                if (args.length > 2) {
                                    target.kickPlayer("§8§m+--------------------------+" + "\n" + "\n" + "§f§l⋅ §7Explusé(e) le §f» §c" + TimeUtil.getFullDate() + "\n" + "§f§l⋅ §7Explusé(e) par §f» §c" + sender.getName() + "\n" + "§f§l⋅ §7Raison §f» §c" + kickReason + "\n" + "\n" + "§f§l⋅ §c§lCette sanction vous est permanente!" + "\n" + "\n" + "§f§l⋅ §7Si vous souhaiter contésté cette sanction §f» §bdiscord.gg/LibertyCity" + "\n" + "\n" + "§8§m+--------------------------+");
                                    AlertUtil.staffAlert("§8" + sender.getName() + " §7a explusé(e) §8" + target.getName() + " §7, raison: §c" + kickReason, "LibertyCity.staff.alert", 0);
                                    YMLUtil.log("KICK | " + target.getName() + " a était expulsée silencieusement par " + sender.getName() + ", raison: " + kickReason, "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                                } else {
                                    target.kickPlayer("§8§m+--------------------------+" + "\n" + "\n" + "§f§l⋅ §7Explusé(e) le §f» §c" + TimeUtil.getFullDate() + "\n" + "§f§l⋅ §7Explusé(e) par §f» §c" + sender.getName() + "\n" + "§f§l⋅ §7Raison §f» §cNon Specifiée" + "\n" + "\n" + "§f§l⋅ §c§lCette sanction vous est permanente!" + "\n" + "\n" + "§f§l⋅ §7Si vous souhaiter contésté cette sanction §f» §bdiscord.gg/LibertyCity" + "\n" + "\n" + "§8§m+--------------------------+");
                                    AlertUtil.staffAlert("§8" + sender.getName() + " §7a explusé(e) §8" + target.getName(), "LibertyCity.staff.alert", 0);
                                    YMLUtil.log("KICK | " + target.getName() + " a était expulsée par " + sender.getName() + ", raison: " + kickReason, "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                                }
                            } else {
                                target.kickPlayer("§8§m+--------------------------+" + "\n" + "\n" + "§f§l⋅ §7Explusé(e) le §f» §c" + TimeUtil.getFullDate() + "\n" + "§f§l⋅ §7Explusé(e) par §f» §c" + sender.getName() + "\n" + "§f§l⋅ §7Raison §f» §c" + kickReason + "\n" + "\n" + "§f§l⋅ §c§lCette sanction vous est permanente!" + "\n" + "\n" + "§f§l⋅ §7Si vous souhaiter contésté cette sanction §f» §bdiscord.gg/LibertyCity" + "\n" + "\n" + "§8§m+--------------------------+");
                                Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §c" + target.getName() + " §fa été explusé(e) du serveur!");
                                AlertUtil.staffAlert("§8" + sender.getName() + " §7a explusé(e) §8" + target.getName() + "§7, raison: §8" + kickReason, "LibertyCity.staff.alert", 0);
                                YMLUtil.log("KICK | " + target.getName() + " a était expulsée par " + sender.getName() + ", raison: " + kickReason, "/server/punishments/", "/server/punishments/" + target.getUniqueId() + ".yml");
                            }
                        }
                    } else
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! " + target.getName() + " n'est pas en ligne!");
                } catch (Exception e) {
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Joueur introuvable.");
                }
            }
        }

        return true;
    }
}
