package com.xiii.libertycity.core.data;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PlayerData implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public UUID uuid;
    public Player player;
    public String name;

    public boolean isSilentBan;
    public String banReason;
    public String banDate;
    public String banDuration;
    public Date banExpiration;
    public String banDisplayDate;
    public String bannedBy;

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

    public PlayerData(String name, UUID uuid) {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return player;
    }

}
