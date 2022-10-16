package com.xiii.libertycity;

import com.xiii.libertycity.core.Events;
import com.xiii.libertycity.core.commands.chat.*;
import com.xiii.libertycity.core.commands.player.*;
import com.xiii.libertycity.core.commands.punish.*;
import com.xiii.libertycity.core.commands.server.*;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.utils.FileUtils;
import com.xiii.libertycity.roleplay.events.AnkleBreakEvent;
import com.xiii.libertycity.roleplay.events.DeathEvent;
import com.xiii.libertycity.roleplay.events.RegisterEvent;
import com.xiii.libertycity.roleplay.guis.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class LibertyCity extends JavaPlugin {

    public static LibertyCity INSTANCE;

    public static Plugin getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;

        Data.data.registerServer(Bukkit.getServer());
        FileUtils.readServerData();
        FileUtils.readPlayerData();

        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Bukkit.getPluginManager().registerEvents(new ATMGui(), this);
        Bukkit.getPluginManager().registerEvents(new BinGui(), this);
        Bukkit.getPluginManager().registerEvents(new RegisterEvent(), this);
        Bukkit.getPluginManager().registerEvents(new AnkleBreakEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DeathEvent(), this);

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
            Bukkit.getPluginCommand("msg").setExecutor(new MsgCommand());
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
            Bukkit.getConsoleSender().sendMessage("Phase 3 PASSED.");

        }, 30);
        Bukkit.getConsoleSender().sendMessage("Plugin loaded");

    }

    @Override
    public void onDisable() {
        FileUtils.saveServerData(Data.data.getServerData(Bukkit.getServer()));
    }
}
