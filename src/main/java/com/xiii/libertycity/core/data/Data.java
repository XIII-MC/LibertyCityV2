package com.xiii.libertycity.core.data;

import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public enum Data {
    data;

    public final ArrayList<PlayerData> users = new ArrayList<>();
    public final ArrayList<ServerData> servers = new ArrayList<>();

    public void registerUser(Player p) {
        if (!isAlreadyRegistered(p)) {
            PlayerData pd = new PlayerData(p.getName(), p.getUniqueId());
            this.users.add(pd);
        }
    }

    public void registerServer(Server p) {
        if (!isServerAlreadyRegistered(p)) {
            ServerData pd = new ServerData();
            this.servers.add(pd);
        }
    }

    public boolean isServerAlreadyRegistered(Server p) {
        return getServerData(p) != null;
    }

    public boolean isAlreadyRegistered(Player p) {
        return getUserData(p) != null;
    }

    public ArrayList<PlayerData> getUsers() {
        return this.users;
    }

    public PlayerData getUserData(Player p) {
        for (PlayerData user : users) {
            if (user.uuid.toString().contains(p.getUniqueId().toString())) {

                return user;
            }
        }
        return null;
    }

    public ServerData getServerData(Server p) {
        for (ServerData user : servers) {
            return user;
        }
        return null;
    }

}
