package com.xiii.libertycity.core.utils;

import com.xiii.libertycity.LibertyCity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        try {
            for (File file : warpsFolder.listFiles()) {
                if (file.getName().endsWith("yml")) names.add(file.getName().replace(".yml", ""));
            }
        } catch(java.lang.NullPointerException e) {
            return new ArrayList<>();
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

    public static void log(String message, String customFolderPath, String customFilePath) {
        //new SimpleDateFormat("dd/MM/yyyy); | DateFormat
        //new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); | SimpleDateFormat

        Date date = new Date();
        SimpleDateFormat displayDate = new SimpleDateFormat("dd_MM_yyyy");
        SimpleDateFormat fullDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss-SSS");

        File fileFolder = new File(LibertyCity.INSTANCE.getDataFolder() + "/server/logs/");
        File logFile = new File(LibertyCity.INSTANCE.getDataFolder() + "/server/logs/" + displayDate.format(date) + ".yml");

        if (customFolderPath != null) fileFolder = new File(LibertyCity.INSTANCE.getDataFolder() + customFolderPath);
        if (customFilePath != null) logFile = new File(LibertyCity.INSTANCE.getDataFolder() + customFilePath);

        if (!fileFolder.exists()) fileFolder.mkdir();

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(logFile);
        File finalLogFile = logFile;

        cfg.set(fullDate.format(date), message);

        try {
            cfg.save(finalLogFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

