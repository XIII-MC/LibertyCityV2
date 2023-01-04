package com.xiii.libertycity.core.utils;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.displays.ScoreboardDisplay;
import org.bukkit.entity.Player;

public class MoneyUtils {

    public static String getBank(Player p, boolean format) {
        PlayerData data = Data.data.getUserData(p);
        if(format) return ConvertUtils.format(data.rpBank);
        else return String.valueOf(data.rpBank);
    }

    public static void addMoney(Player p, long amount) {
        PlayerData data = Data.data.getUserData(p);
        if(amount > 0) data.rpBank += amount;
        ScoreboardDisplay.updateScoreboard(p);
    }

    public static boolean removeMoney(Player p, long amount, boolean checkHasMoney) {
        PlayerData data = Data.data.getUserData(p);
        if(amount > 0)
            if(checkHasMoney && (data.rpBank >= amount)) {
                data.rpBank -= amount;
                ScoreboardDisplay.updateScoreboard(p);
                return true;
            }
        if(!(data.rpBank >= amount)) return false;
        if(!checkHasMoney) {
            data.rpBank -= amount;
            ScoreboardDisplay.updateScoreboard(p);
            return true;
        }
        ScoreboardDisplay.updateScoreboard(p);
        return false;
    }

}
