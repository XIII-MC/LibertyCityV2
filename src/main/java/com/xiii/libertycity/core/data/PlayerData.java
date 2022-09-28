package com.xiii.libertycity.core.data;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerData implements java.io.Serializable {

    public UUID uuid;
    public Player player;
    public String name;

    public boolean isBanned;
    public boolean isMuted;
    public double muteDuration;
    public String muteTime;
    public double muteCalc;
    public String muteLeft;
    public String muteReason;
    public boolean silentKick = false;

    public double lastReport = 90000;
    public boolean isVanished = false;
    public boolean isGodMode = false;
    public boolean allowMsg = true;
    public Player lastDm;
    public List<String> ignoredPlayers = new ArrayList<>();

    public int playerID;
    public String rpPrenom;
    public String rpNom;
    public int rpAge;
    public int rpBank;
    public int rpCurrentChat;
    public String rpCurrentJob = "§eCitoyen";
    public String rpPoliceRank = "§b§kAucun";
    public String rpGangRank = "§c§kAucun";
    public boolean isVerified;
    public boolean inSearch = false;
    public String joinDate;
    public boolean isSearching = false;

    public boolean chatBanHRP = false;
    public boolean chatBanRP = false;
    public boolean chatBanGang = false;
    public boolean chatBanPolice = false;
    public boolean chatBanGlobal = false;
    public double lastChat = 999999;

    public boolean spyChatGlobal = false;
    public boolean spyChatHRP = false;
    public boolean spyChatRP = false;
    public boolean spyChatPolice = false;
    public boolean spyChatGang = false;
    public boolean spyMsg = false;

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
