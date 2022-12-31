package com.xiii.libertycity.core.utils;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import org.bukkit.entity.Player;

public class NullSafetyUtils {

    public static PlayerData preventNullData(PlayerData data, Player player) {
        if(data == null) Data.data.registerUser(player);
        return Data.data.getUserData(player);
    }

}
