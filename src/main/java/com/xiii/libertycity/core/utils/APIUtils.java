package com.xiii.libertycity.core.utils;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;

import java.util.UUID;

public class APIUtils {

    public static PlayerData getSafeData(String player) {
        String uuid;
        try {
            uuid = HTTPUtils.readUrl("https://api.mojang.com/users/profiles/minecraft/" + player).substring(24).replace("}", "").replace("\"", "");
        } catch (Exception e) {
            return null;
        }
        final StringBuilder stringBuilder = new StringBuilder(uuid);
        stringBuilder.insert(8, "-").insert(13, "-").insert(18, "-").insert(23, "-");
        Data.data.registerUser(player, stringBuilder.toString());
        return Data.data.getUserData(stringBuilder.toString());
    }

    public static UUID getSafeUUID(String player) {
        final String uuid = HTTPUtils.readUrl("https://api.mojang.com/users/profiles/minecraft/" + player).substring(24).replace("}", "").replace("\"", "");
        final StringBuilder stringBuilder = new StringBuilder(uuid);
        stringBuilder.insert(8, "-").insert(13, "-").insert(18, "-").insert(23, "-");
        return UUID.fromString(stringBuilder.toString());
    }

    public static String getSafeUUIDasString(String player) {
        final String uuid = HTTPUtils.readUrl("https://api.mojang.com/users/profiles/minecraft/" + player).substring(24).replace("}", "").replace("\"", "");
        final StringBuilder stringBuilder = new StringBuilder(uuid);
        stringBuilder.insert(8, "-").insert(13, "-").insert(18, "-").insert(23, "-");
        return stringBuilder.toString();
    }

}
