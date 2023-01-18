package com.xiii.libertycity.core.commands.punish;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("report")) {
            if(args.length == 0) sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Usage: /report <Joueur> (Raison)");
            else {
                try {
                    PlayerData reporter = Data.data.getUserData((Player) sender);
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    PlayerData reported = Data.data.getUserData(target);
                    if (target.isOnline()) {
                        if (System.currentTimeMillis() - reporter.lastReport > 60000) {
                            sender.sendMessage("§2§lLiberty§a§lCity §7» §fVotre signalement a bien été envoyé");
                            reporter.lastReport = System.currentTimeMillis();
                            if (args.length == 1) {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.hasPermission("LibertyCity.staff.seereports")) {
                                        p.sendMessage(" ");
                                        p.sendMessage("§c=-=-=-=-=-= §4§lREPORT §c=-=-=-=-=-=");
                                        p.sendMessage(" ");
                                        p.sendMessage("     §a" + sender.getName() + " §7(" + reporter.rpPrenom + " " + reporter.rpNom + "§7) a signlé(e) §c" + target.getName() + " §7(" + reported.rpPrenom + " " + reported.rpNom + "§7)");
                                        p.sendMessage("     §7Raison » §cNon Specifiée");
                                        p.sendMessage(" ");
                                        p.sendMessage("§c=-=-=-=-=-= §4§lREPORT §c=-=-=-=-=-=");
                                        p.sendMessage(" ");
                                    }
                                }
                            } else {
                                String reportReason = "";
                                for (int i = 1; i < args.length; i++) {
                                    reportReason += args[i] + " ";
                                }
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.hasPermission("LibertyCity.staff.seereports")) {
                                        p.sendMessage(" ");
                                        p.sendMessage("§c=-=-=-=-=-= §4§lREPORT §c=-=-=-=-=-=");
                                        p.sendMessage(" ");
                                        p.sendMessage("     §a" + sender.getName() + " §7(" + reporter.rpPrenom + " " + reporter.rpNom + "§7) a signlé(e) §c" + target.getName() + " §7(" + reported.rpPrenom + " " + reported.rpNom + "§7)");
                                        p.sendMessage("     §7Raison » §c" + reportReason);
                                        p.sendMessage(" ");
                                        p.sendMessage("§c=-=-=-=-=-= §4§lREPORT §c=-=-=-=-=-=");
                                        p.sendMessage(" ");
                                    }
                                }
                            }
                        } else
                            sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Veuillez patientez entre chaque signalement");
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
