package com.xiii.libertycity.core.displays;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.utils.MoneyUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import static org.bukkit.Bukkit.getServer;

public class ScoreboardDisplay {

    final ScoreboardManager manager = Bukkit.getScoreboardManager();
    final Scoreboard board = manager.getNewScoreboard();

    public static String getEntryFromScore(Objective o, int score) {
        if(o == null) return null;
        if(!hasScoreTaken(o, score)) return null;
        for (String s : o.getScoreboard().getEntries()) {
            if(o.getScore(s).getScore() == score) return o.getScore(s).getEntry();
        }
        return null;
    }

    public static boolean hasScoreTaken(Objective o, int score) {
        for (String s : o.getScoreboard().getEntries()) {
            if(o.getScore(s).getScore() == score) return true;
        }
        return false;
    }

    public static void replaceScore(Objective o, int score, String name) {
        if(hasScoreTaken(o, score)) {
            if(getEntryFromScore(o, score).equalsIgnoreCase(name)) return;
            if(!(getEntryFromScore(o, score).equalsIgnoreCase(name))) o.getScoreboard().resetScores(getEntryFromScore(o, score));
        }
        o.getScore(name).setScore(score);
    }

    public static void updateScoreboard(Player p) {

        if(p.getScoreboard().equals(getServer().getScoreboardManager().getMainScoreboard())) p.setScoreboard(getServer().getScoreboardManager().getNewScoreboard());
        final Scoreboard score = p.getScoreboard();
        final Objective objective = score.getObjective(p.getName()) == null ? score.registerNewObjective(p.getName(), "dummy") : score.getObjective(p.getName());
        final PlayerData data = Data.data.getUserData(p);
        String currentChat = "§4§LERROR49";

        // Chat interpreter
        if(data.rpCurrentChat == 0) currentChat = "§3§LHRP";
        if(data.rpCurrentChat == 1) currentChat = "§2§LRP";
        if(data.rpCurrentChat == 2) currentChat = "§b§lPLC";
        if(data.rpCurrentChat == 3) currentChat = "§4§LGNG";

        objective.setDisplayName("§2§LLiberty§a§LCity §4§LRP");

        replaceScore(objective, 15, "§1§7§m--------------------");
        replaceScore(objective, 14,("§3§nIdentitée§r §7»"));
        replaceScore(objective, 13, "§4 ");
        replaceScore(objective, 12," Nom §7» §2§l" + data.rpNom);
        replaceScore(objective, 11," Prénom §7» §a§l" + data.rpPrenom);
        replaceScore(objective, 10," Âge §7» §c§l" + data.rpAge + " ans");
        replaceScore(objective, 9," Pseudo §7» §4§l" + p.getName());
        replaceScore(objective, 8, "§2§7§m--------------------");
        replaceScore(objective, 7,"§6§nInformations§r §7»");
        replaceScore(objective, 6, "§5 ");
        replaceScore(objective, 5," Argent §7» §6§l$" + MoneyUtils.getBank(p, true));
        replaceScore(objective, 4," Travail §7» " + data.rpCurrentJob);
        if(data.rpPoliceRank != null) replaceScore(objective, 3, " Rang §7» §B§l" + data.rpPoliceRank);
        if(data.rpGangRank != null) replaceScore(objective, 3, " Gang §7» §4§l" + data.rpGangRank);
        replaceScore(objective, 2," Chat sélectionné §7» §9§l" + currentChat);
        replaceScore(objective, 1, "§3§7§m--------------------");
        replaceScore(objective, 0,"        §2liberty§acity§7.§4fr");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        p.setScoreboard(score);
    }

}
