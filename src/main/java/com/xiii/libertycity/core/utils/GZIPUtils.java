package com.xiii.libertycity.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GZIPUtils {

    public static void compressGzipFile(File file, String gzipFile) {
        if(!file.getName().contains(".gz") && file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                FileOutputStream fos = new FileOutputStream(gzipFile);
                GZIPOutputStream gzipOS = new GZIPOutputStream(fos);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    gzipOS.write(buffer, 0, len);
                }
                //close resources
                gzipOS.close();
                fos.close();
                fis.close();
                file.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
