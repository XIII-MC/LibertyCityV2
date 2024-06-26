package com.xiii.libertycity.core.data;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ServerData implements java.io.Serializable {
    private static final long serialVersionUID = 2L;

    public int globalID = 0;
    public int globalInventoryID = 0;
    public boolean chatStateGlobal = true;
    public boolean chatStateGang = true;
    public boolean chatStatePolice = true;
    public boolean chatStateRP = true;
    public boolean chatStateHRP = true;
    public int chatCooldownGlobal = 2500;

    public List<String> warpsNames = new ArrayList<>();

    public List<String> mutedPlayer = new ArrayList<>();
    public List<String> mutedIPs = new ArrayList<>();

    public List<String> rpPrenom = new ArrayList<>();
    public List<String> rpNom = new ArrayList<>();
    public List<Integer> averageAge = new ArrayList<>();

    //TEMP
    public List<Player> vanishedPlayers = new ArrayList<>();
}
