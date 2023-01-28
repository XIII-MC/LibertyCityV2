package com.xiii.libertycity.core.utils;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.data.ServerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class IDUtils {

    public static void createNewID(Player player, PlayerData data) {
        // TODO: Change id to real id
        // TODO: Change ID to real Wallet ID
        ItemStack wallet = new ItemStack(Material.getMaterial(4329));
        ItemMeta wmeta = wallet.getItemMeta();
        wmeta.setDisplayName("§rPorte feuille");
        List<String> wlore = new ArrayList<>();
        wlore.add("");
        wlore.add("§rPropriétaire » §a" + data.rpPrenom + " §2" + data.rpNom + " §7(" + player.getName() + ")");
        ServerData server = Data.data.getServerData(Bukkit.getServer());
        wlore.add("§rID PTF » §8" + server.globalInventoryID);
        wmeta.setLore(wlore);
        wallet.setItemMeta(wmeta);
        server.globalInventoryID++;
        player.getInventory().addItem(wallet);

        ItemStack ID = new ItemStack(Material.getMaterial(4325));
        ItemMeta meta = ID.getItemMeta();
        meta.setDisplayName("§rCarte d'identitée");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§rNom » §2" + data.rpNom);
        lore.add("§rPrénom » §a" + data.rpPrenom);
        lore.add("§rÂge » §c" + data.rpAge + " ans");
        lore.add("§rPseudo » §4" + player.getName());
        lore.add("§rUUID » §8" + player.getUniqueId());
        Date date = new Date();
        date.setMonth(date.getMonth() + 6);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.format(date);
        lore.add("§rExpiration » §6" + date);
        double pin = Math.random() * 1000;
        lore.add("§rCode PIN » §8" + pin);
        lore.add("");
        lore.add("§7Clique droit avec le carte en main pour la verifier.");
        data.pin = pin;
        meta.setLore(lore);
        ID.setItemMeta(meta);
        player.getInventory().addItem(ID);
    }

    public static boolean isValidID(ItemStack idCard) {
        ItemMeta meta = idCard.getItemMeta();
        if (meta.hasLore()) {
            List<String> lore = meta.getLore();
            // TODO: Fix lore after mod fix removing extra lines
            String line5 = lore.get(5).replace("§rUUID » §8", "");
            String line7 = lore.get(7).replace("§rCode PIN » §8", "");
            PlayerData data = Data.data.getUserData(line5);
            if(Double.parseDouble(line7) == data.pin) return true;
        }
        return false;
    }

}
