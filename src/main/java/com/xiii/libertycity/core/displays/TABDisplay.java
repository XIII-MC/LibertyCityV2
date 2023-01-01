package com.xiii.libertycity.core.displays;

import com.keenant.tabbed.Tabbed;
import com.keenant.tabbed.item.PlayerTabItem;
import com.keenant.tabbed.item.TextTabItem;
import com.keenant.tabbed.tablist.TableTabList;
import com.keenant.tabbed.util.Skin;
import com.keenant.tabbed.util.Skins;
import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.utils.LagCalculator;
import com.xiii.libertycity.core.utils.PingUtil;
import com.xiii.libertycity.core.utils.TimeUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class TABDisplay {

    public static final Skin CHAT_BUBBLE = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTY3MjYxMTE5OTc3MCwKICAicHJvZmlsZUlkIiA6ICJhNTkyMjkwNDVjMjI0MGUyOTM0ZjMxZWFjMzNiY2IzNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTbHVnRGVhbGVyQWdhaW4iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQ2NDVhZGFkOWRlMDgyNDc5NTVkN2UwYjc3MWJiMWY4MjZmOTI1MWY2ZTA5MTNmYmJjNjdhODAxYzJjMTU0ZSIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9", "dZKZoFGwqs7WQ2zhQNiN8UEMRVdRNOr1kzhYBVGutOledr7VmxGCIfKNNil4Gs/zmm8+/jCZPvJn0/39XAUVuCGw6Mhr4+2Vatz1ivpC9JfT4Czs8op9JsVcYZ/pLh5j/CxpgQ3/xn3tYuw09aHwZPDZW+FXt8zMYKNzrXiBQrD2jGYe9FHSWmwghYSHAx7DVbg20B5ajpvGejMzvA2PBSQFe7YX/7umpnIXrst8LcDP6EdhlmNgBZ3x51H88cn9QqctXjPVwweW81B0ufHOTrbncAGFgCUykfny0dlcYYdiulm0dTNcSFAEzqbqNfNN6+XveEiss+POyQt+g6Vr9kfMokgk51x+gtBHnre7MAHBTfcANR5A1PMfQOrFJUCFt42MXYp2Y3iHtYAho86jOlKs/nX2rSS9uOJ5ov70aIYyNNe+5w4kll5tZmaaE16fqCY0oKOICzAzOWe12tOFPTlpLyM1+2UBdwGjMUaJmzG6X0YiS8JvH73HuAmVM/wQuZ/WRBnOEvcqZjUPyqkCvP3vNIqecPeXZvdDz7IJJ0Q2/iRZdE4m/mnbvItcpeZvCQeH058AHqGpYY7Ei0SwdU568EasShYwoOqgruC0NP2O7dAo/pQjEQiEZnGViDUFUaz0bbXpIvnDuBW+eyuEenWAN4ASVgtNSoy/F6093Bo=");

    public static TableTabList tab;

    public static void updateTablist(Player p) {

        tab = LibertyCity.tabInstance.newTableTabList(p);
        tab.setHeader("§2§LLiberty§a§lCity §4§lRP" + "\n");
        tab.setFooter("\n" + "§7Site Web » §7www.§2liberty§acity§7.§4fr" + "\n" + "§7TeamSpeak » ts.§2liberty§acity§7.§4fr" + "\n" + "§7Discord » discord.§2liberty§acity.§4fr");

        PlayerData data = Data.data.getUserData(p);
        if(data == null) return;
        String currentChat = "§4§LERROR23";

        // Establish server stability
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () -> {
            final double TPS = LagCalculator.getTPS();
            double TPSPercentage = (Math.round((TPS * 100) / 20) * 100.0) / 100.0;
            String percentageColor = "§0" + TPSPercentage;
            if(TPSPercentage > 100) TPSPercentage = 100;
            if(TPSPercentage >= 97.5) percentageColor = "§2" + TPSPercentage + "%";
            if(TPSPercentage < 97.5) percentageColor = "§a" + TPSPercentage + "%";
            if(TPSPercentage < 90) percentageColor = "§e" + TPSPercentage + "%";
            if(TPSPercentage < 85) percentageColor = "§6" + TPSPercentage + "%";
            if(TPSPercentage < 80) percentageColor = "§c" + TPSPercentage + "%";
            if(TPSPercentage < 75) percentageColor = "§4" + TPSPercentage + "%";
            if(TPSPercentage <= 50) percentageColor = "§0" + TPSPercentage + "%";
            tab.set(0, 7, new TextTabItem("§7Stabilitée » " + percentageColor));
        }, 20, 20);

        // Chat interpreter
        if(data.rpCurrentChat == 0) currentChat = "§3§LHRP";
        if(data.rpCurrentChat == 1) currentChat = "§2§LRP";
        if(data.rpCurrentChat == 2) currentChat = "§b§lPLC";
        if(data.rpCurrentChat == 3) currentChat = "§4§LGNG";

        // Column 0
        tab.set(0, 0, new TextTabItem("§2Serveur"));
        tab.set(0, 2, new TextTabItem("§7IP » §2liberty§acity§7.§4fr"));
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () -> tab.set(0, 3, new TextTabItem("§7Joueurs » §a" + Bukkit.getOnlinePlayers().size() + "§7/§2" + Bukkit.getMaxPlayers())), 20, 20);
        tab.set(0, 5, new TextTabItem("§2Informations"));
        // COL 0 | ROW 8 TAKEN
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () -> tab.set(0, 8, new TextTabItem("§7Ping » §a" + PingUtil.getPing(p))), 20, 20);
        tab.set(0, 10, new TextTabItem("§2Date & Heure"));
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () -> tab.set(0, 12, new TextTabItem("§7Heure » §a" + TimeUtil.getTime())), 20, 20);
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () -> tab.set(0, 13, new TextTabItem("§7Date » §a" + TimeUtil.getDate())), 20, 20);
        tab.set(0, 15, new TextTabItem("§2Commande d'aide"));
        tab.set(0, 17, new TextTabItem("§a/help"));
        tab.set(0, 18, new TextTabItem("§a/helpop"));
        tab.set(0, 19, new TextTabItem("§a/astuce"));

        // Column 1
        tab.set(1, 0, new TextTabItem("§3§nIdentitée"));
        tab.set(1, 2, new TextTabItem("§7Nom » §2§l" + data.rpNom));
        tab.set(1, 3, new TextTabItem("§7Prénom » §a§l" + data.rpPrenom));
        tab.set(1, 4, new TextTabItem("§7Âge » §c§l" + data.rpAge + " ans"));
        tab.set(1, 5, new TextTabItem("§7Pseudo » §4§l" + p.getName()));
        tab.set(1, 7, new TextTabItem("§6§nInformations"));
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () ->  tab.set(1, 9, new TextTabItem("§7Argent » §6§l$" + data.rpBank, 0, Skins.getPlayer("Monnee"))), 20, 20);
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () -> tab.set(1, 10, new TextTabItem("§7Travail » " + data.rpCurrentJob, 0, Skins.getPlayer("Dreadsteed"))), 20, 20);
        tab.set(1, 11, new TextTabItem("§7Niveau » §8§kAucun"));
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () -> {
            if (data.rpPoliceRank != null) tab.set(2, 11, new TextTabItem("§7Rang » §b" + data.rpPoliceRank));
            if (data.rpGangRank != null) tab.set(2, 11, new TextTabItem("§7Gang » §4" + data.rpGangRank));
        }, 20, 20);
        String finalCurrentChat = currentChat;
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () -> tab.set(1, 12, new TextTabItem("§7Chat sélectionné » " + finalCurrentChat, 0, CHAT_BUBBLE)), 20, 20);

        // Column 2 & 3
        tab.set(2, 0, new TextTabItem("§2Joueurs en ligne"));
        for(int i = 2; i < 18; i++) tab.set(2, i, new TextTabItem("§8§kXXXXXXXX"));
        for(int h = 0; h < 19; h++) tab.set(3, h, new TextTabItem("§8§kXXXXXXXX"));

        // Player list update
        int row = 2;
        int column = 2;
        for (Player player : Bukkit.getOnlinePlayers()) {

            // Switch column
            if(row > 18 && column == 2) {
                column++;
                row = 0;
            }

            // Player list is full
            if(row > 19 && column == 3) return;

            tab.set(column, row, new PlayerTabItem(player));
            row++;
        }
        tab.batchUpdate(); // sends the packets!

        // Add cool items
        //tab.set(2, 0, new TextTabItem("A cute cat :D", 0, Skins.getMob(EntityType.OCELOT)));
        //tab.set(3, 0, new TextTabItem("Notch (omg)", 0, Skins.getPlayer("Notch")));

    }

}
