package com.xiii.libertycity.core;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Events implements Listener {

    @EventHandler
    public void forGodMode(EntityDamageEvent e) {
        if(e.getEntity() != null && e.getEntity().getType() == EntityType.PLAYER) {
            PlayerData data = Data.data.getUserData((Player) e.getEntity());
            if(data.isGodMode) e.setCancelled(true);
        }
    }
}
