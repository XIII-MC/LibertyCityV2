package com.xiii.libertycity.core.commands.punish;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.utils.AlertUtil;
import com.xiii.libertycity.core.utils.TimeUtil;
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
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if(target.isOnline()) {
                    if(args.length == 1) {
                        target.kickPlayer("§8§m+--------------------------+" + "\n" + "\n" + "§f§l. §7Explusé(e) le §f» §c" + TimeUtil.getDate() + "\n" + "§f§l. Explusé(e) par §f» §c" + sender.getName() + "\n" + "§f§l. Raison §f» §cNon Specifiée" + "\n" + "\n" + "§f§l. §c§lCette sanction vous est permanente!" + "\n" + "\n" + "§f§l. §7Vous pouvez contesté cette sanction ici §f» §bdiscord.gg/LibertyCity");
                        Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §c" + target.getName() + " §fa été explusé(e) du serveur!");
                        AlertUtil.staffAlert("§8" + sender.getName() + " §7a explusé(e) §8" + target.getName(), "LibertyCity.staff.alert", 0);
                    } else {

                        PlayerData data = Data.data.getUserData(target);

                        String newStringConverted = "";
                        for (int i = 1; i < args.length; i++) {
                            newStringConverted += args[i] + " ";
                        }
                        String kickReason = "";
                        kickReason = newStringConverted.replace("-s", "");

                        if(newStringConverted.contains("-s")) {
                            data.silentKick = true;
                            if(args.length > 2) {
                                target.kickPlayer("§8§m+--------------------------+" + "\n" + "\n" + "§f§l. §7Explusé(e) le §f» §c" + TimeUtil.getDate() + "\n" + "§f§l. Explusé(e) par §f» §c" + sender.getName() + "\n" + "§f§l. Raison §f» §c" + kickReason + "\n" + "\n" + "§f§l. §c§lCette sanction vous est permanente!" + "\n" + "\n" + "§f§l. §7Vous pouvez contesté cette sanction ici §f» §bdiscord.gg/LibertyCity");
                                AlertUtil.staffAlert("§8" + sender.getName() + " §7a explusé(e) §8" + target.getName() + " §7, raison: §c" + kickReason, "LibertyCity.staff.alert", 0);
                            } else {
                                target.kickPlayer("§8§m+--------------------------+" + "\n" + "\n" + "§f§l. §7Explusé(e) le §f» §c" + TimeUtil.getDate() + "\n" + "§f§l. Explusé(e) par §f» §c" + sender.getName() + "\n" + "§f§l. Raison §f» §cNon Specifiée" + "\n" + "\n" + "§f§l. §c§lCette sanction vous est permanente!" + "\n" + "\n" + "§f§l. §7Vous pouvez contesté cette sanction ici §f» §bdiscord.gg/LibertyCity");
                                AlertUtil.staffAlert("§8" + sender.getName() + " §7a explusé(e) §8" + target.getName(), "LibertyCity.staff.alert", 0);
                            }
                        } else {
                            target.kickPlayer("§8§m+--------------------------+" + "\n" + "\n" + "§f§l. §7Explusé(e) le §f» §c" + TimeUtil.getDate() + "\n" + "§f§l. Explusé(e) par §f» §c" + sender.getName() + "\n" + "§f§l. Raison §f» §c" + kickReason + "\n" + "\n" + "§f§l. §c§lCette sanction vous est permanente!" + "\n" + "\n" + "§f§l. §7Vous pouvez contesté cette sanction ici §f» §bdiscord.gg/LibertyCity");
                            Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §c" + target.getName() + " §fa été explusé(e) du serveur!");
                            AlertUtil.staffAlert("§8" + sender.getName() + " §7a explusé(e) §8" + target.getName() + "§7, raison: §8" + kickReason, "LibertyCity.staff.alert", 0);
                        }
                    }
                } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! " + target.getName() + " n'est pas en ligne!");
            }
        }

        return true;
    }
}
