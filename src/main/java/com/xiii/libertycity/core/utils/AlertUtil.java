package com.xiii.libertycity.core.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AlertUtil {

    public static void staffAlert(String message, String permission, int type) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            switch (type) {
                case 0:
                    if(p.hasPermission(permission)) p.sendMessage("§4§lSTAFF §7» " + message);
                case 1:
                    if(p.hasPermission(permission)) p.sendMessage("§c§l[ALERT]§r " + message);
                case 2:
                    if(p.hasPermission(permission)) p.sendMessage("§4§l⚠ §7» " + message);
            }
        }
    }

}
