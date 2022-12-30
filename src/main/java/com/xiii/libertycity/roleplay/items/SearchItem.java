package com.xiii.libertycity.roleplay.items;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SearchItem implements Listener {

    @EventHandler
    public void searchItem(PlayerInteractEntityEvent e){
        Bukkit.getScheduler().scheduleSyncDelayedTask(LibertyCity.INSTANCE, () -> {

            PlayerData data = Data.data.getUserData(e.getPlayer());

            if(data.rpCurrentJob == "§bPolicier") {

                Entity target = e.getRightClicked();
                Player player = e.getPlayer();

                if (target.getType().equals(EntityType.PLAYER) && player.getInventory().getItemInMainHand().getTypeId() == 7428) {
                    HumanEntity targetPlayer = (HumanEntity) target;
                    Inventory targetInventory = targetPlayer.getInventory();

                    player.sendMessage("§2§lLiberty§a§lCity §7» §fVous fouillé §e" + targetPlayer);
                    PlayerData temp = Data.data.getUserData((Player) targetPlayer);
                    temp.inSearch = true;
                    data.isSearching = true;
                    target.sendMessage("§2§lLiberty§a§lCity §7» §fVous vous faites fouillé par §e" + player);
                    player.openInventory(targetInventory);
                }

            } else e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas de la Police !");
        });
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void moveCheck(PlayerMoveEvent e) {
        PlayerData data = Data.data.getUserData(e.getPlayer());
        if(data.inSearch) e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void inventoryClose(InventoryCloseEvent e) {
        PlayerData data = Data.data.getUserData((Player) e.getPlayer());
    }

}
