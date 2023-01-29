package com.xiii.libertycity.core.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlayerData implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public String uuid;
    public String name;

    public boolean isSilentWarn;
    public String warnReason;
    public String warnDate;
    public String warnDuration;
    public Date warnExpiration;
    public String warnDisplayDate;
    public String warnedBy;
    public List<String> warns = new ArrayList<>();

    public boolean isMuted;
    public boolean isSilentMute;
    public String muteReason;
    public String muteDate;
    public long muteDuration;
    public Date muteExpiration;
    public String muteDisplayDate;
    public String mutedBy;

    public boolean isVanished = false;
    public boolean isGodMode = false;
    public boolean allowMsg = true;
    public List<String> ignoredPlayers = new ArrayList<>();

    public int playerID = -1;
    public String rpPrenom;
    public String rpNom;
    public int rpAge;
    public long rpBank;
    public int rpCurrentChat;
    public String rpCurrentJob = "§eCitoyen";
    public String rpPoliceRank;
    public String rpGangRank;
    public boolean isVerified;
    public String joinDate;
    public double pin;

    public boolean chatBanHRP = false;
    public boolean chatBanRP = false;
    public boolean chatBanGang = false;
    public boolean chatBanPolice = false;

    public boolean spyChatGlobal = false;
    public boolean spyChatHRP = false;
    public boolean spyChatRP = false;
    public boolean spyChatPolice = false;
    public boolean spyChatGang = false;
    public boolean spyMsg = false;

    //TEMP
    public transient boolean isSearching = false;
    public transient boolean inSearch = false;
    public transient long lastReport = 90000L;
    public transient Player lastDm;
    public transient long lastChat = 999999L;
    public transient boolean silentKick = false;
    public transient Player lastSearchedPlayer;
    public transient int tempAge = 0;
    public transient String tempPrenom = null;
    public transient String tempName = null;
    public transient String tempNom = null;
    public transient boolean isWaitingPrenom = true;
    public transient boolean isWaitingNom = true;
    public transient boolean isWaitingAge = true;
    public transient Player player;
    public transient boolean onlineState = false;
    public transient boolean isSilentBan;
    public transient String banReason;
    public transient String banDate;
    public transient String banDuration;
    public transient Date banExpiration;
    public transient String banDisplayDate;
    public transient String bannedBy;
    public transient boolean modMode;

    public PlayerData(String name, String uuid) {
        this.uuid = uuid;
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return player;
    }

}
