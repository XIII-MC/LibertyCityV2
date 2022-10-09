package com.xiii.libertycity.core;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.data.ServerData;
import com.xiii.libertycity.core.utils.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
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
                    p.hidePlayer(LibertyCity.INSTANCE, (Player) Bukkit.getOnlinePlayers());
                    p.showPlayer(LibertyCity.INSTANCE, (Player) server.vanishedPlayers);
                    p.setDisplayName("§7[V] §f" + p.getName());
                }
            }
        });
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {
            PlayerData data = Data.data.getUserData(e.getPlayer());
            if(data.playerID > 0) FileUtils.savePlayerData(Data.data.getUserData(e.getPlayer()));
        });
    }

}
