package com.xiii.libertycity.roleplay.items;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.utils.FileUtils;
import com.xiii.libertycity.core.utils.YMLUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
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
                    String lore2 = lore.get(2).replace("§rID PTF » §8", "");
                    FileConfiguration backpacks = YMLUtil.getBackpackYml();

                    Inventory backpack;

                    if (backpacks.contains(lore2)) {

                        try {

                            backpack = FileUtils.readInventory(backpacks.getString(lore2));

                        } catch (IOException i) {

                            backpack = Bukkit.getServer().createInventory(null, 9, "Porte feuille");

                        }

                    } else {

                        backpack = Bukkit.getServer().createInventory(null, 9, "Porte feuille");

                    }

                    e.getPlayer().openInventory(backpack);
                } else e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cPorte feuille invalide.");

            }

        });
    }

    @EventHandler
    public void forWalletOpen(InventoryOpenEvent e) {
        // TODO: Replace with real Wallet ID
        if(e.getInventory().getName().equalsIgnoreCase("Porte feuille") && !(e.getPlayer().getInventory().getItemInMainHand().getTypeId() == 4329)) {
            e.setCancelled(true);
            e.getPlayer().closeInventory();
            e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cErreur d'écriture. Veuillez garder le porte feuille en main pendant l'interaction.");
        }
    }

    @EventHandler
    public void forWalletClose(InventoryCloseEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {

            // TODO: Replace ID with real Wallet ID
            if(e.getInventory().getName().equalsIgnoreCase("Porte feuille") && e.getPlayer().getInventory().getItemInMainHand().getTypeId() == 4329) {

                ItemMeta meta = e.getPlayer().getInventory().getItemInMainHand().getItemMeta();
                if(meta.hasLore()) {
                    List<String> lore = meta.getLore();
                    String lore2 = lore.get(2).replace("§rID PTF » §8", "");
                    FileConfiguration backpacks = YMLUtil.getBackpackYml();

                    String inventoryString = FileUtils.saveInventory(e.getInventory());
                    backpacks.set(lore2, inventoryString);

                    YMLUtil.saveBackpackYml(backpacks);
                } else e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cPorte feuille invalide.");

            }

            if(e.getInventory().getName().equalsIgnoreCase("Porte feuille") && !(e.getPlayer().getInventory().getItemInMainHand().getTypeId() == 4329)) e.getPlayer().sendMessage("§2§lLiberty§a§lCity §7» §cErreur d'écriture. Veuillez garder le porte feuille en main pendant l'interaction.");

        });
    }

}
