package com.xiii.libertycity.core.utils;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.data.ServerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.util.Objects;
import java.util.UUID;

public class FileUtils {

    public static void saveServerData(ServerData data) {
        try {
            if (!LibertyCity.INSTANCE.getDataFolder().exists())
                LibertyCity.INSTANCE.getDataFolder().mkdir();
            File fileFolder = new File(LibertyCity.INSTANCE.getDataFolder() + "//server//");
            if (!fileFolder.exists()) fileFolder.mkdir();
            File file = new File(LibertyCity.INSTANCE.getDataFolder() + "//server//", "CONSOLE" + ".LCT");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOut = new FileOutputStream(LibertyCity.INSTANCE.getDataFolder() + "//server//" + "CONSOLE" + ".LCT");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void readServerData() {
        File configFolder = new File(LibertyCity.INSTANCE.getDataFolder() + "//server//");

        if (configFolder.listFiles() == null || Objects.requireNonNull(configFolder.listFiles()).length < 1) {
            System.out.println("§eWARN: No ServerData found");
            return;
        } else {
            for (final File file : Objects.requireNonNull(configFolder.listFiles())) {
                if(!file.isDirectory()) {
                    try {
                        FileInputStream fileIn = new FileInputStream(file.getPath());
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        Data.data.servers.add((ServerData) in.readObject());
                        in.close();
                        fileIn.close();
                    } catch (IOException i) {
                        i.printStackTrace();
                        return;
                    } catch (ClassNotFoundException c) {
                        System.out.println("§eERROR: ServerData class not found");
                        c.printStackTrace();
                        return;
                    }
                }
            }
        }
    }

    public static void deletePlayerData(UUID uuid, Player p) {
        if(LibertyCity.INSTANCE.getDataFolder().exists()) {
            File fileFolder = new File(LibertyCity.INSTANCE.getDataFolder() + "//players//");
            if(fileFolder.exists()) {
                File file = new File(LibertyCity.INSTANCE.getDataFolder() + "//players//", uuid + ".LCT");
                if(file.exists() && !file.isDirectory()) {
                    file.delete();
                    Data.data.deletePlayerData(p);
                }
            }
        }
    }

    public static void savePlayerData(PlayerData data) {
        try {
            if (!LibertyCity.INSTANCE.getDataFolder().exists())
                LibertyCity.INSTANCE.getDataFolder().mkdir();
            File fileFolder = new File(LibertyCity.INSTANCE.getDataFolder() + "//players//");
            if (!fileFolder.exists()) fileFolder.mkdir();
            File file = new File(LibertyCity.INSTANCE.getDataFolder() + "//players//", data.getUuid() + ".LCT");
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
                file.createNewFile();
            }
            FileOutputStream fileOut = new FileOutputStream(LibertyCity.INSTANCE.getDataFolder() + "//players//" + data.getUuid() + ".LCT");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void readPlayerData() {
        File configFolder = new File(LibertyCity.INSTANCE.getDataFolder() + "//players//");

        if (configFolder.listFiles() == null || Objects.requireNonNull(configFolder.listFiles()).length < 1) {
            System.out.println("§eWARN: No PlayerData found");
        } else {
            for (final File file : Objects.requireNonNull(configFolder.listFiles())) {
                if(!file.isDirectory()) {
                    try {
                        FileInputStream fileIn = new FileInputStream(file.getPath());
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        Data.data.users.add((PlayerData) in.readObject());
                        in.close();
                        fileIn.close();
                    } catch (IOException i) {
                        i.printStackTrace();
                        return;
                    } catch (ClassNotFoundException c) {
                        System.out.println("§cERROR: PlayerData class not found");
                        c.printStackTrace();
                        return;
                    }
                }
            }
        }
    }
}
