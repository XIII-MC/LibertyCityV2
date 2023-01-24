package com.xiii.libertycity.roleplay.items;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.utils.IDUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class IDCard implements Listener {

    @EventHandler
    public void checkIDCard(PlayerInteractEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {

            // TODO: Change ID to real IDCard ID
            if(e.getPlayer().getInventory().getItemInMainHand() != null && e.getPlayer().getInventory().getItemInMainHand().getTypeId() == 4325) {

                if(IDUtils.isValidID(e.getPlayer().getInventory().getItemInMainHand())) {

                    e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §fCarte valide.");

                } else {

                    e.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                    e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cCarte invalide, elle a donc était détruite.");

                }

            }

        });
    }

}
