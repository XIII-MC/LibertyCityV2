package com.xiii.libertycity.core.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {

    public static void removeOne(Inventory inventory, ItemStack itemStack) {
        ItemStack one = itemStack.clone();
        one.setAmount(1);
        inventory.removeItem(one);
    }

    public static int getAmount(Player arg0, ItemStack arg1) {
        if (arg1 == null)
            return 0;
        int amount = 0;
        for (int i = 0; i < 36; i++) {
            ItemStack slot = arg0.getInventory().getItem(i);
            if (slot == null || !slot.isSimilar(arg1))
                continue;
            amount += slot.getAmount();
        }
        return amount;
    }

    public static boolean hasItem(Player p, ItemStack i) {
        for (ItemStack item : p.getInventory().getContents()) {
            if (item == null) continue;
            if (item.isSimilar(i)) return true;
        }
        return false;
    }

    public static boolean canStore(Player p, ItemStack item) {
        ItemStack[] invRef = p.getInventory().getStorageContents();
        int count = 0;
        for(ItemStack j : invRef) {
            if(j == null || j.getType() == Material.AIR) return true;
            if(j.isSimilar(item)) {
                count += j.getMaxStackSize() - j.getAmount();
            }
            if(count >= item.getAmount()) return true;
        }
        return false;
    }

}
