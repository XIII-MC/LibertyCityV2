package com.xiii.libertycity.core.utils;

import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class PingUtil {

    public static int getPing(Player p) {
        try {
            Object entityPlayer = p.getClass().getDeclaredMethod("getHandle").invoke(p);
            Field f = entityPlayer.getClass().getDeclaredField("ping");
            return f.getInt(entityPlayer);
        } catch (NoSuchMethodException | NoSuchFieldException | InvocationTargetException | IllegalAccessException e ) {
            e.printStackTrace();
        }
        return -0;
    }

}
