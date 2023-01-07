package com.xiii.libertycity.core.data;

import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServerData implements Serializable {
    private static final long serialVersionUID = 1213799434508676095L;

    public int globalID;
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
