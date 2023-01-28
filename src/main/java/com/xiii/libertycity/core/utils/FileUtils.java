package com.xiii.libertycity.core.utils;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.data.ServerData;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.*;
import java.util.Objects;

public class FileUtils {

    public static void saveServerData(ServerData data) {
        try {
            if (!LibertyCity.INSTANCE.getDataFolder().exists())
                LibertyCity.INSTANCE.getDataFolder().mkdir();
            File fileFolder = new File(LibertyCity.INSTANCE.getDataFolder() + "/server/");
            if (!fileFolder.exists()) fileFolder.mkdir();
            File file = new File(LibertyCity.INSTANCE.getDataFolder() + "/server/", "CONSOLE" + ".libertycity");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOut = new FileOutputStream(LibertyCity.INSTANCE.getDataFolder() + "/server/" + "CONSOLE" + ".libertycity");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void readServerData() {
        File dataFolder = new File(LibertyCity.INSTANCE.getDataFolder() + "/server/CONSOLE.libertycity");

        if (dataFolder.listFiles() == null || Objects.requireNonNull(dataFolder.listFiles()).length < 1) {
            System.out.println("§eWARN: No ServerData found");
            return;
        } else {
            for (final File file : Objects.requireNonNull(dataFolder.listFiles())) {
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

    public static void savePlayerData(PlayerData data) {
        try {
            if (!LibertyCity.INSTANCE.getDataFolder().exists())
                LibertyCity.INSTANCE.getDataFolder().mkdir();
            File fileFolder = new File(LibertyCity.INSTANCE.getDataFolder() + "/players/");
            if (!fileFolder.exists()) fileFolder.mkdir();
            File file = new File(LibertyCity.INSTANCE.getDataFolder() + "/players/", data.getUuid() + ".libertycity");
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
                file.createNewFile();
            }
            FileOutputStream fileOut = new FileOutputStream(LibertyCity.INSTANCE.getDataFolder() + "/players/" + data.getUuid() + ".libertycity");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void readPlayerData() {
        File dataFolder = new File(LibertyCity.INSTANCE.getDataFolder() + "/players/");

        if (dataFolder.listFiles() == null || Objects.requireNonNull(dataFolder.listFiles()).length < 1) {
            System.out.println("§eWARN: No PlayerData found");
            return;
        } else {
            for (final File file : Objects.requireNonNull(dataFolder.listFiles())) {
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

    public static String saveInventory(Inventory inventory) throws IllegalStateException {

        try {

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeInt(inventory.getSize());

            for (int i = 0; i < inventory.getSize(); i++) {

                dataOutput.writeObject(inventory.getItem(i));

            }

            dataOutput.close();

            return Base64Coder.encodeLines(outputStream.toByteArray());

        } catch (Exception e) {

            throw new IllegalStateException("Unable to save item stacks.", e);

        }

    }



    public static Inventory readInventory(String data) throws IOException {

        try {

            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));

            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt(), "Porte feuille");

            for (int i = 0; i < inventory.getSize(); i++) {

                inventory.setItem(i, (ItemStack) dataInput.readObject());

            }

            dataInput.close();

            return inventory;

        } catch (ClassNotFoundException e) {

            throw new IOException("Unable to decode class type.", e);

        }

    }
}
