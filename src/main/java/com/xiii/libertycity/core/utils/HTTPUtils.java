package com.xiii.libertycity.core.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class HTTPUtils {

    public static String readUrl(String URL) {
        HttpGet httpGet = new HttpGet(URL);
        try (CloseableHttpClient CHC = HttpClientBuilder.create().build()) {
            CloseableHttpResponse closeableHttpResponse = CHC.execute(httpGet);
            HttpEntity httpEntity = closeableHttpResponse.getEntity();
            return EntityUtils.toString(httpEntity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return "CPE_E";
        } catch (IOException e) {
            e.printStackTrace();
            return "UNKNOWN";
        }
    }

    public static boolean validate(String string) {
        String str = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
        return string.matches(str);
    }

    public static void downloadFile(String URL, File file) {
        try {
            URL uRL = new URL(URL);
            ReadableByteChannel readableByteChannel = Channels.newChannel(uRL.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0L, Long.MAX_VALUE);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
