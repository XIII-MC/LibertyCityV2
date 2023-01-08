package com.xiii.libertycity;

import com.keenant.tabbed.Tabbed;
import com.xiii.libertycity.core.Events;
import com.xiii.libertycity.core.commands.chat.ChatCooldownCommand;
import com.xiii.libertycity.core.commands.chat.ClearChatCommand;
import com.xiii.libertycity.core.commands.chat.MuteChatCommand;
import com.xiii.libertycity.core.commands.chat.SpyChatCommand;
import com.xiii.libertycity.core.commands.player.*;
import com.xiii.libertycity.core.commands.punish.*;
import com.xiii.libertycity.core.commands.server.*;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.displays.ScoreboardDisplay;
import com.xiii.libertycity.core.utils.*;
import com.xiii.libertycity.roleplay.CustomChat;
import com.xiii.libertycity.roleplay.events.AnkleBreakEvent;
import com.xiii.libertycity.roleplay.events.DeathEvent;
import com.xiii.libertycity.roleplay.events.RegisterEvent;
import com.xiii.libertycity.roleplay.guis.ATMGui;
import com.xiii.libertycity.roleplay.guis.BinGui;
import com.xiii.libertycity.roleplay.items.SearchItem;
import com.xiii.libertycity.core.displays.BossBarDisplay;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class LibertyCity extends JavaPlugin {

    public static LibertyCity INSTANCE;
    public static Tabbed tabInstance;
    public static Plugin getInstance() {
        return INSTANCE;
    }
    public static BossBar bossBar;

    @Override
    public void onEnable() {
        INSTANCE = this;
        tabInstance = new Tabbed(this);
        bossBar = Bukkit.createBossBar("§4§LERROR_43", BarColor.RED, BarStyle.SEGMENTED_6);
        BossBarDisplay.init();

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new LagCalculator(), 100L, 1L);

        FileUtils.readPlayerData();
        FileUtils.readServerData();
        Data.data.registerServer(Bukkit.getServer());

        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Bukkit.getPluginManager().registerEvents(new ATMGui(), this);
        Bukkit.getPluginManager().registerEvents(new BinGui(), this);
        Bukkit.getPluginManager().registerEvents(new RegisterEvent(), this);
        Bukkit.getPluginManager().registerEvents(new AnkleBreakEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DeathEvent(), this);
        Bukkit.getPluginManager().registerEvents(new CustomChat(), this);
        Bukkit.getPluginManager().registerEvents(new SearchItem(), this);

        // PHASE 1
        Bukkit.getScheduler().runTaskLater(this, () -> {

            Bukkit.getPluginCommand("cooldown").setExecutor(new ChatCooldownCommand());
            Bukkit.getPluginCommand("clearchat").setExecutor(new ClearChatCommand());
            Bukkit.getPluginCommand("mutechat").setExecutor(new MuteChatCommand());
            Bukkit.getPluginCommand("spy").setExecutor(new SpyChatCommand());
            Bukkit.getPluginCommand("clear").setExecutor(new ClearCommand());
            Bukkit.getPluginCommand("feed").setExecutor(new FeedCommand());
            Bukkit.getPluginCommand("fly").setExecutor(new FlyCommand());
            Bukkit.getPluginCommand("gamemode").setExecutor(new GamemodeCommand());
            Bukkit.getPluginCommand("god").setExecutor(new GodCommand());
            Bukkit.getPluginCommand("hat").setExecutor(new HatCommand());
            Bukkit.getPluginCommand("heal").setExecutor(new HealCommand());
            Bukkit.getPluginCommand("ignore").setExecutor(new IgnoreCommand());
            Bukkit.getPluginCommand("invsee").setExecutor(new InvseeCommand());
            Bukkit.getPluginCommand("more").setExecutor(new MoreCommand());
            Bukkit.getPluginCommand("sms").setExecutor(new MsgCommand());
            Bukkit.getPluginCommand("atm").setExecutor(new ATMGui());
            Bukkit.getPluginCommand("kickall").setExecutor(new KickallCommand());
            Bukkit.getPluginCommand("time").setExecutor(new TimeSetCommand());
            Bukkit.getPluginCommand("ban").setExecutor(new BanCommand());
            Bukkit.getPluginCommand("unban").setExecutor(new UnbanCommand());
        }, 10);


        //PHASE 2
        Bukkit.getScheduler().runTaskLater(this, () -> {

            Bukkit.getPluginCommand("near").setExecutor(new NearCommand());
            Bukkit.getPluginCommand("ping").setExecutor(new PingCommand());
            Bukkit.getPluginCommand("r").setExecutor(new RCommand());
            Bukkit.getPluginCommand("repair").setExecutor(new RepairCommand());
            Bukkit.getPluginCommand("speed").setExecutor(new SpeedCommand());
            Bukkit.getPluginCommand("sudo").setExecutor(new SudoCommand());
            Bukkit.getPluginCommand("teleport").setExecutor(new TeleportCommand());
            Bukkit.getPluginCommand("togglemsg").setExecutor(new ToggleMsgCommand());
            Bukkit.getPluginCommand("vanish").setExecutor(new VanishCommand());
            Bukkit.getPluginCommand("ban").setExecutor(new BanCommand());
            Bukkit.getPluginCommand("kickall").setExecutor(new KickallCommand());
            Bukkit.getPluginCommand("kick").setExecutor(new KickCommand());
            Bukkit.getPluginCommand("mute").setExecutor(new MuteCommand());
            Bukkit.getPluginCommand("report").setExecutor(new ReportCommand());
            Bukkit.getPluginCommand("unban").setExecutor(new UnbanCommand());
            Bukkit.getPluginCommand("night").setExecutor(new TimeSetCommand());
            Bukkit.getPluginCommand("warp").setExecutor(new WarpCommand());
            Bukkit.getPluginCommand("warps").setExecutor(new WarpsCommand());
            Bukkit.getPluginCommand("sun").setExecutor(new WeatherCommand());
            Bukkit.getPluginCommand("rain").setExecutor(new WeatherCommand());
            Bukkit.getConsoleSender().sendMessage("Phase 2 PASSED.");

        }, 20);

        //PHASE 3
        Bukkit.getScheduler().runTaskLater(this, () -> {

            Bukkit.getPluginCommand("unmute").setExecutor(new UnmuteCommand());
            Bukkit.getPluginCommand("setwarp").setExecutor(new AddWarpCommand());
            Bukkit.getPluginCommand("clearlag").setExecutor(new ClearLagCommand());
            Bukkit.getPluginCommand("delwarp").setExecutor(new DelWarpCommand());
            Bukkit.getPluginCommand("list").setExecutor(new ListCommand());
            Bukkit.getPluginCommand("day").setExecutor(new TimeSetCommand());
            Bukkit.getPluginCommand("LCDBB1BETA").setExecutor(new TestCommand());
            Bukkit.getPluginCommand("chat").setExecutor(new CustomChat());
            Bukkit.getConsoleSender().sendMessage("Phase 3 PASSED.");

        }, 30);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> FileUtils.saveServerData(Data.data.getServerData(Bukkit.getServer())), 20, 20);
        Bukkit.getConsoleSender().sendMessage("Plugin loaded");

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage("§8§m+---------------------------------------+");
                p.sendMessage("§2§lLiberty§a§lCity §7» §fVous avez reçu §6$10 §7(Aide de l'Etat)");
                p.sendMessage("§8§m+---------------------------------------+");
                MoneyUtils.addMoney(p, 10);
            }
        },600*20, 600*20);



    }

    @Override
    public void onDisable() {
        try {
            TestUtils.writeObjectToFile(Data.data.getServerData(Bukkit.getServer()), new File(LibertyCity.INSTANCE.getDataFolder() + "/server/data/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bukkit.getScheduler().cancelAllTasks();
    }
}
