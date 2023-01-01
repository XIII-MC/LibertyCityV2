package com.xiii.libertycity.core;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.data.ServerData;
import com.xiii.libertycity.core.displays.ScoreboardDisplay;
import com.xiii.libertycity.core.utils.AlertUtil;
import com.xiii.libertycity.core.utils.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {

    @EventHandler
    public void forVanish(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {
            Data.data.registerUser(e.getPlayer());
            ServerData server = Data.data.getServerData(Bukkit.getServer());
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (server.vanishedPlayers.contains(p)) {
                    // TODO: Test
                    p.hidePlayer(LibertyCity.INSTANCE, (Player) Bukkit.getOnlinePlayers());
                    p.showPlayer(LibertyCity.INSTANCE, (Player) server.vanishedPlayers);
                    p.setDisplayName("§7[V] §f" + p.getName());
                }
            }
        });
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        PlayerData data = Data.data.getUserData(e.getPlayer());
        if (data.playerID > 0) FileUtils.savePlayerData(Data.data.getUserData(e.getPlayer()));
        if (data.playerID <= 0) e.setQuitMessage("");
        else if (!e.getPlayer().hasPermission("LibertyCity.silentquit"))
            e.setQuitMessage("§7(§4§l-§7) §a§l" + data.rpPrenom + " §2§l" + data.rpNom + " §8(" + e.getPlayer().getName() + ")");
        if (data.playerID > 0 && e.getPlayer().hasPermission("LibertyCity.silentjoin")) {
            AlertUtil.staffAlert("§8" + e.getPlayer().getName() + " §7a quitté le serveur", "LibertyCity.staff.alert", 0);
            e.setQuitMessage("");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        Data.data.registerUser(e.getPlayer());
        PlayerData data = Data.data.getUserData(e.getPlayer());
        if (data.playerID <= 0) e.setJoinMessage("");
        else if (!e.getPlayer().hasPermission("LibertyCity.silentjoin"))
            e.setJoinMessage("§7(§2§l+§7) §a§l" + data.rpPrenom + " §2§l" + data.rpNom + " §8(" + e.getPlayer().getName() + ")");
        if (data.playerID > 0 && e.getPlayer().hasPermission("LibertyCity.silentjoin")) {
            AlertUtil.staffAlert("§8" + e.getPlayer().getName() + " §7a rejoint le serveur", "LibertyCity.staff.alert", 0);
            e.setJoinMessage("");
        }
        if(data.playerID > 0) ScoreboardDisplay.updateScoreboard(e.getPlayer());
    }

}
