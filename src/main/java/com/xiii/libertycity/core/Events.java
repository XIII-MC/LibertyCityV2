package com.xiii.libertycity.core;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.data.ServerData;
import com.xiii.libertycity.core.displays.BossBarDisplay;
import com.xiii.libertycity.core.displays.ScoreboardDisplay;
import com.xiii.libertycity.core.displays.TABDisplay;
import com.xiii.libertycity.core.utils.AlertUtil;
import com.xiii.libertycity.core.utils.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener {

    @EventHandler
    public void forModMode(PlayerInteractEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {

            PlayerData data = Data.data.getUserData(e.getPlayer());

            if(data.modMode) {

                if(e.getPlayer().getInventory().getItemInMainHand() == new ItemStack(Material.FEATHER)) {

                    if(e.getPlayer().isFlying()) {
                        e.getPlayer().setFlying(false);
                        e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §fVous ne pouvez plus voler.");
                    } else {
                        e.getPlayer().setFlying(true);
                        e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §fVous pouvez désormais voler.");
                    }

                }

            }


        });
    }

    @EventHandler
    public void forVanish(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {
            ServerData server = Data.data.getServerData(Bukkit.getServer());
            if (server.vanishedPlayers != null) {
                for (Player vp : server.vanishedPlayers) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.hidePlayer(LibertyCity.INSTANCE, vp);
                        if (p.hasPermission("LibertyCity.seevanishedplayers") || server.vanishedPlayers.contains(p))
                            p.showPlayer(LibertyCity.INSTANCE, vp);
                        vp.setDisplayName("§7[V] §f" + vp.getName());
                    }
                }
            }
            TABDisplay.updatePlayerList();
        });
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        PlayerData data = Data.data.getUserData(e.getPlayer());
        data.onlineState = false;
        if (data.playerID <= 0 || e.getPlayer().hasPermission("LibertyCity.silentquit")) e.setQuitMessage("");
        else if (!e.getPlayer().hasPermission("LibertyCity.silentquit")) e.setQuitMessage("§7(§4§l-§7) §a§l" + data.rpPrenom + " §2§l" + data.rpNom + " §8(" + e.getPlayer().getName() + ")");
        if (data.playerID > 0 && e.getPlayer().hasPermission("LibertyCity.silentquit")) AlertUtil.staffAlert("§8" + e.getPlayer().getName() + " §7a quitté le serveur", "LibertyCity.staff.alert", 0);
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {
            if (data.playerID > 0) FileUtils.savePlayerData(Data.data.getUserData(e.getPlayer()));
            LibertyCity.tabInstance.destroyTabList(e.getPlayer());
            Bukkit.getScheduler().runTaskLaterAsynchronously(LibertyCity.INSTANCE, () -> TABDisplay.updatePlayerList(), 2);
            if (TABDisplay.pingHandle != null) TABDisplay.pingHandle.cancel();
            LibertyCity.bossBar.removePlayer(e.getPlayer());
        });
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        Data.data.registerUser(e.getPlayer());
        PlayerData data = Data.data.getUserData(e.getPlayer());
        data.onlineState = true;
        if (data.playerID <= 0 || e.getPlayer().hasPermission("LibertyCity.silentjoin")) e.setJoinMessage("");
        else if (!e.getPlayer().hasPermission("LibertyCity.silentjoin")) e.setJoinMessage("§7(§2§l+§7) §a§l" + data.rpPrenom + " §2§l" + data.rpNom + " §8(" + e.getPlayer().getName() + ")");
        if (data.playerID > 0 && e.getPlayer().hasPermission("LibertyCity.silentjoin")) AlertUtil.staffAlert("§8" + e.getPlayer().getName() + " §7a rejoint le serveur", "LibertyCity.staff.alert", 0);
        if (data.playerID > 0) ScoreboardDisplay.updateScoreboard(e.getPlayer());
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {
            TABDisplay.updateTablist(e.getPlayer());
            TABDisplay.updatePlayerList();
            BossBarDisplay.updateBossBar(e.getPlayer());
        });
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () -> FileUtils.savePlayerData(Data.data.getUserData(e.getPlayer())), 20*10, 20*10);
    }

}
