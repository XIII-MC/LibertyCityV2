package com.xiii.libertycity.core.utils;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.ServerData;

import java.io.*;

public class TestUtils {

    public static void writeObjectToFile(ServerData obj, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    public static void readObjectFromFile(File file) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(fis)) {
            Data.data.servers.add((ServerData) in.readObject());
        }
    }

}
