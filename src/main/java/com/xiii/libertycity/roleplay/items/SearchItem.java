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
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SearchItem implements Listener {

    @EventHandler
    public void searchItem(PlayerInteractEntityEvent e){
        Bukkit.getScheduler().scheduleSyncDelayedTask(LibertyCity.INSTANCE, () -> {

            PlayerData data = Data.data.getUserData(e.getPlayer());
            Entity target = e.getRightClicked();
            Player player = e.getPlayer();

            if (target.getType().equals(EntityType.PLAYER) && player.getInventory().getItemInMainHand().getTypeId() == 7428) {

                if (data.rpCurrentJob == "§bPolicier") {

                    HumanEntity targetPlayer = (HumanEntity) target;
                    Inventory targetInventory = targetPlayer.getInventory();
                    PlayerData temp = Data.data.getUserData((Player) targetPlayer);

                    player.sendMessage("§2§lLiberty§a§lCity §7» §fVous fouillé §a" + temp.rpPrenom + " §2" + temp.rpNom + " §7(" + targetPlayer.getName() + ")");
                    data.lastSearchedPlayer = (Player) targetPlayer;
                    temp.inSearch = true;
                    data.isSearching = true;
                    target.sendMessage("§2§lLiberty§a§lCity §7» §fVous vous faites fouiller par §a" + data.rpPrenom + " §2" + data.rpNom + " §7(" + player.getName() + ")");
                    player.openInventory(targetInventory);

                } else e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas de la Police !");
            }
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
        if(data.isSearching) {
            PlayerData temp = Data.data.getUserData(data.lastSearchedPlayer);
            data.isSearching = false;
            temp.inSearch = false;
            data.lastSearchedPlayer.sendMessage("§2§lLiberty§a§lCity §7» §fLa fouille a été terminé par l'officier de Police.");

        }

    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void cancelSearchLeave(PlayerQuitEvent e) {
        PlayerData data = Data.data.getUserData(e.getPlayer());
        if(data.isSearching) {
            PlayerData temp = Data.data.getUserData(data.lastSearchedPlayer);
            data.isSearching = false;
            temp.inSearch = false;
            data.lastSearchedPlayer.sendMessage("§2§lLiberty§a§lCity §7» §fLa fouille c'est terminé car l'officier de Police c'est déconnecté.");
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void cancelSearchDamage(EntityDamageByEntityEvent e) {
        if(e.getEntity() instanceof Player) {
            PlayerData data = Data.data.getUserData((Player) e.getEntity());
            if (data.isSearching) {
                PlayerData temp = Data.data.getUserData(data.lastSearchedPlayer);
                data.isSearching = false;
                temp.inSearch = false;
                data.lastSearchedPlayer.sendMessage("§2§lLiberty§a§lCity §7» §fLa fouille c'est terminé car l'officier de Police a été attaqué.");
                ((Player) e.getEntity()).closeInventory();
            }
        }
    }

}
