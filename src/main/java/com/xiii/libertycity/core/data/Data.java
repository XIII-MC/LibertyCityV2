package com.xiii.libertycity.core.data;

import com.xiii.libertycity.core.utils.NullSafetyUtils;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public enum Data {
    data;

    public final ArrayList<PlayerData> users = new ArrayList<>();
    public final ArrayList<ServerData> servers = new ArrayList<>();

    public boolean isServerAlreadyRegistered(Server p) {
        return getServerData(p) != null;
    }
    public boolean isAlreadyRegistered(Player p) {
        return getUserData(p) != null;
    }

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


    public PlayerData getUserData(Player p) {
        for (PlayerData user : users) {
            if (user.uuid.toString().equals(p.getUniqueId().toString())) {
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

    public void deletePlayerData(Player p) {
        if(isAlreadyRegistered(p)) this.users.remove(p);
    }

}
