package com.xiii.libertycity;

import com.xiii.libertycity.core.commands.chat.*;
import com.xiii.libertycity.core.commands.player.*;
import com.xiii.libertycity.core.commands.punish.*;
import com.xiii.libertycity.core.commands.server.ClearLagCommand;
import com.xiii.libertycity.core.commands.server.ListCommand;
import com.xiii.libertycity.core.commands.server.TimeSetCommand;
import com.xiii.libertycity.core.commands.server.WeatherCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class LibertyCity extends JavaPlugin {

    public static LibertyCity instance;

    public static Plugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

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
        Bukkit.getPluginCommand("near").setExecutor(new NearCommand());
        Bukkit.getPluginCommand("ping").setExecutor(new PingCommand());
        Bukkit.getPluginCommand("r").setExecutor(new RCommand());
        Bukkit.getPluginCommand("repair").setExecutor(new RepairCommand());
        Bukkit.getPluginCommand("speed").setExecutor(new SpeedCommand());
        Bukkit.getPluginCommand("sudo").setExecutor(new SudoCommand());
        Bukkit.getPluginCommand("teleport").setExecutor(new TeleportCommand());
        Bukkit.getPluginCommand("togglemsg").setExecutor(new ToggleMsgCommand());
        Bukkit.getPluginCommand("vanish").setExecutor(new VanishCommand());
        Bukkit.getPluginCommand("whois").setExecutor(new WhoisCommand());
        Bukkit.getPluginCommand("ban").setExecutor(new BanCommand());
        Bukkit.getPluginCommand("kickall").setExecutor(new KickallCommand());
        Bukkit.getPluginCommand("kick").setExecutor(new KickCommand());
        Bukkit.getPluginCommand("mute").setExecutor(new MuteCommand());
        Bukkit.getPluginCommand("report").setExecutor(new ReportCommand());
        Bukkit.getPluginCommand("unban").setExecutor(new UnbanCommand());
        Bukkit.getPluginCommand("unmute").setExecutor(new UnmuteCommand());
        Bukkit.getPluginCommand("clearlag").setExecutor(new ClearLagCommand());
        Bukkit.getPluginCommand("list").setExecutor(new ListCommand());
        Bukkit.getPluginCommand("day").setExecutor(new TimeSetCommand());
        Bukkit.getPluginCommand("night").setExecutor(new TimeSetCommand());
        Bukkit.getPluginCommand("sun").setExecutor(new WeatherCommand());
        Bukkit.getPluginCommand("rain").setExecutor(new WeatherCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
