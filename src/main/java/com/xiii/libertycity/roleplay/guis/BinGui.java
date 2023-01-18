package com.xiii.libertycity.roleplay.guis;

import com.xiii.libertycity.LibertyCity;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class BinGui implements Listener {

    @EventHandler
    public void onBinInteract(PlayerInteractEvent e) {
        Bukkit.getScheduler().runTask(LibertyCity.INSTANCE, () -> {

            if(e.getClickedBlock() != null && e.getClickedBlock().getTypeId() == 4665) {
                e.setCancelled(true);
                Inventory bin = Bukkit.createInventory(e.getPlayer(), 54, "§8Poubelle");
                e.getPlayer().openInventory(bin);
            }

        });
    }

}
