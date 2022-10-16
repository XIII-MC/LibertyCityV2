package com.xiii.libertycity.core.utils;

import com.xiii.libertycity.LibertyCity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YMLUtil {

    public static Location loadWarp(String warpName) {
        File warpFile = new File(LibertyCity.INSTANCE.getDataFolder() + "/server/warps/" + warpName + ".yml");
        if(warpFile.exists()) {
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(warpFile);
            return new Location(Bukkit.getServer().getWorld(cfg.get("world").toString()), cfg.getDouble("x"), cfg.getDouble("y"), cfg.getDouble("z"), (float) cfg.getDouble("yaw"), (float) cfg.getDouble("pitch"));
        }
        return null;
    }

    public static List<String> getWarps() {
        List<String> names = new ArrayList<>();
        File warpsFolder = new File(LibertyCity.INSTANCE.getDataFolder() + "/server/warps/");
        for(File file : warpsFolder.listFiles()) {
            if(file.getName().endsWith("yml")) names.add(file.getName().replace(".yml", ""));
        }
        return names;
    }

    public static void deleteWarp(String warpName) {
        File warpFile = new File(LibertyCity.INSTANCE.getDataFolder() + "/server/warps/" + warpName + ".yml");
        if(warpFile.exists()) {
            warpFile.delete();
        }
    }

    public static void saveWarp(String warpName, Location loc) {

        File fileFolder = new File(LibertyCity.INSTANCE.getDataFolder() + "/server/warps/");
        File warpFile = new File(LibertyCity.INSTANCE.getDataFolder() + "/server/warps/" + warpName + ".yml");
        if (!fileFolder.exists()) fileFolder.mkdir();

        if (!warpFile.exists()) {
            try {
                warpFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(warpFile);

        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        double yaw = loc.getYaw();
        double pitch = loc.getPitch();

        String world = loc.getWorld().getName();
        cfg.set("x", x);
        cfg.set("y", y);
        cfg.set("z", z);
        cfg.set("yaw", yaw);
        cfg.set("pitch", pitch);
        cfg.set("world", world);

        try {
            cfg.save(warpFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

