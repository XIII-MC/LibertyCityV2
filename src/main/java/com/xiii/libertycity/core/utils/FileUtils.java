package com.xiii.libertycity.core.utils;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.data.ServerData;

import java.io.*;
import java.util.Objects;

public class FileUtils {

    public static void saveServerData(ServerData data) {
        try {
            if (!LibertyCity.instance.getDataFolder().exists())
                LibertyCity.instance.getDataFolder().mkdir();
            File fileFolder = new File(LibertyCity.instance.getDataFolder() + "\\server\\");
            if (!fileFolder.exists()) fileFolder.mkdir();
            File file = new File(LibertyCity.instance.getDataFolder() + "\\server\\", "CONSOLE" + ".libertycity");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOut = new FileOutputStream(LibertyCity.instance.getDataFolder() + "\\server\\" + "CONSOLE" + ".libertycity");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void readServerData() {
        File locateFolder = new File(LibertyCity.instance.getDataFolder() + "\\server\\");

        if (locateFolder.listFiles() == null || Objects.requireNonNull(locateFolder.listFiles()).length < 1) {
            System.out.println("§eWARN: No ServerData found");
            return;
        } else {
            for (final File file : Objects.requireNonNull(locateFolder.listFiles())) {
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

    public static void savePlayerData(PlayerData data) {
        try {
            if (!LibertyCity.instance.getDataFolder().exists())
                LibertyCity.instance.getDataFolder().mkdir();
            File fileFolder = new File(LibertyCity.instance.getDataFolder() + "\\players\\");
            if (!fileFolder.exists()) fileFolder.mkdir();
            File file = new File(LibertyCity.instance.getDataFolder() + "\\players\\", data.getUuid() + ".libertycity");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOut = new FileOutputStream(LibertyCity.instance.getDataFolder() + "\\players\\" + data.getUuid() + ".libertycity");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void readPlayerData() {
        File locateFolder = new File(LibertyCity.instance.getDataFolder() + "\\players\\");

        if (locateFolder.listFiles() == null || Objects.requireNonNull(locateFolder.listFiles()).length < 1) {
            System.out.println("§eWARN: No PlayerData found");
            return;
        } else {
            for (final File file : Objects.requireNonNull(locateFolder.listFiles())) {
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
