package com.xiii.libertycity.core.displays;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.commands.server.ClearLagCommand;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class BossBarDisplay {

    public static BukkitTask bossBarTask;
    public static BukkitTask progressBar;
    public static BukkitTask clearLagTask;

    public static void init() {

        BossBar bossBar = LibertyCity.bossBar;

        bossBar.setVisible(false);

        progressBar = new BukkitRunnable() {

            @Override
            public void run() {
                // 15 seconds: 1 / (20*15)
                if((bossBar.getProgress() >= 0.0033)) bossBar.setProgress(bossBar.getProgress() - 0.0033);
            }

        }.runTaskTimerAsynchronously(LibertyCity.INSTANCE, 1, 1);

        // Reset progress bar (after 15 seconds)
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () -> bossBar.setProgress(1), 20 * 15, 20 * 15);

        bossBarTask = new BukkitRunnable() {

            @Override
            public void run() {

                bossBar.setVisible(true);

                bossBar.setTitle("§8» §fUne question, un problème ? Utiliser §6/helpop §f!");
                bossBar.setColor(BarColor.GREEN);

                Bukkit.getScheduler().runTaskLaterAsynchronously(LibertyCity.INSTANCE, () -> {
                    bossBar.setTitle("§8» §fUn FreeKill, joueur non RP ? Utilisez §6/report <Joueur> <Raison>");
                    bossBar.setColor(BarColor.RED);
                }, 20*15);

                Bukkit.getScheduler().runTaskLaterAsynchronously(LibertyCity.INSTANCE, () -> {
                    bossBar.setTitle("§8» §fLes recrutement staff sont §a§nouvert§r§f !");
                    bossBar.setColor(BarColor.BLUE);
                }, 20*30);

                Bukkit.getScheduler().runTaskLaterAsynchronously(LibertyCity.INSTANCE, () -> {
                    bossBar.setVisible(false);
                }, 20*45);

            }

        }.runTaskTimerAsynchronously(LibertyCity.INSTANCE, (20*1800), (20*1800));

        clearLagTask = new BukkitRunnable() {

            @Override
            public void run() {

                bossBar.setVisible(true);

                bossBar.setTitle("§8» §fLes éboueurs ramasseront tout les déchets dans §615 seconde§f!");
                bossBar.setColor(BarColor.YELLOW);

                Bukkit.getScheduler().runTaskLater(LibertyCity.INSTANCE, () -> {
                    int clearedItems = ClearLagCommand.getItemsOnTheGround();
                    if (ClearLagCommand.getItemsOnTheGround() > 0)
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "clearlag");
                    bossBar.setTitle("§8» §fLes éboueurs ont ramassé §6" + clearedItems + " §fdéchets!");
                    bossBar.setColor(BarColor.GREEN);
                }, 20 * 15);

                Bukkit.getScheduler().runTaskLaterAsynchronously(LibertyCity.INSTANCE, () -> {
                    bossBar.setVisible(false);
                }, 20*30);

            }

        }.runTaskTimerAsynchronously(LibertyCity.INSTANCE, 20*225, 20*225);

    }

    public static void updateBossBar(Player p) {

        LibertyCity.bossBar.addPlayer(p);

    }

}
