package com.xiii.libertycity.roleplay.items;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Wallet implements Listener {

    @EventHandler
    public void forWalletInteract(PlayerInteractEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {

            // TODO: Replace ID with real Wallet ID
            if(e.getPlayer().getInventory().getItemInMainHand().getTypeId() == 4329) {

                ItemMeta meta = e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
                if(meta.hasLore()) {
                    List<String> lore = meta.getLore();
                    String lore2 = lore.get(2).replace("§rUUID » §8", "");
                    PlayerData data = Data.data.getUserData(lore2);
                    e.getPlayer().openInventory(data.wallet);
                } else e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cPorte feuille invalide.");

            }

        });
    }

    @EventHandler
    public void forWalletClose(InventoryCloseEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {

            // TODO: Replace ID with real Wallet ID
            if(e.getInventory().getName().equalsIgnoreCase("Porte feuille") && e.getPlayer().getInventory().getItemInMainHand().getTypeId() == 4329) {

                ItemMeta meta = e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
                if(meta.hasLore()) {
                    List<String> lore = meta.getLore();
                    String lore2 = lore.get(2).replace("§rUUID » §8", "");
                    PlayerData data = Data.data.getUserData(lore2);
                    data.wallet = e.getInventory();
                } else e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cPorte feuille invalide.");

            }

        });
    }

}
