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

public class KickallCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("kickall")) {

            for(Player p : Bukkit.getOnlinePlayers()) {

                if (!p.hasPermission("LibertyCity.bypass.kickall")) {

                    PlayerData data = Data.data.getUserData(p);
                    data.silentKick = true;

                    if (args.length == 0) {

                        p.kickPlayer("§8§m+--------------------------+" + "\n" + "\n" + "§f§l⋅ §7Explusé(e) le §f» §c" + TimeUtil.getFullDate() + "\n" + "§f§l⋅ §7Explusé(e) par §f» §c" + sender.getName() + "\n" + "§f§l⋅ §7Raison §f» §cNon Specifiée" + "\n" + "\n" + "§f§l⋅ §c§lCette sanction vous est permanente!" + "\n" + "\n" + "§f§l⋅ §7Si vous souhaiter contésté cette sanction §f» §bdiscord.gg/LibertyCity" + "\n" + "\n" + "§8§m+--------------------------+");
                        YMLUtil.log("KICKALL | " + p.getName() + " a était expulsée par " + sender.getName(), "/server/punishments/", "/server/punishments/" + p.getUniqueId() + ".yml");

                    } else {

                        String newStringConverted = "";
                        for (int i = 0; i < args.length; i++) {
                            newStringConverted += args[i] + " ";
                        }
                        String kickReason = "";
                        kickReason = newStringConverted;

                        p.kickPlayer("§8§m+--------------------------+" + "\n" + "\n" + "§f§l⋅ §7Explusé(e) le §f» §c" + TimeUtil.getFullDate() + "\n" + "§f§l⋅ §7Explusé(e) par §f» §c" + sender.getName() + "\n" + "§f§l⋅ §7Raison §f» §c" + kickReason + "\n" + "\n" + "§f§l⋅ §c§lCette sanction vous est permanente!" + "\n" + "\n" + "§f§l⋅ §7Si vous souhaiter contésté cette sanction §f» §bdiscord.gg/LibertyCity" + "\n" + "\n" + "§8§m+--------------------------+");
                        YMLUtil.log("KICKALL | " + p.getName() + " a était expulsée par " + sender.getName() + ", raison: " + kickReason, "/server/punishments/", "/server/punishments/" + p.getUniqueId() + ".yml");

                    }


                }
            }

            AlertUtil.staffAlert("§8" + sender.getName() + " §7a explusé tous les joueurs du serveur.", "LibertyCity.staff.alert", 0);

        }

        return true;
    }
}
