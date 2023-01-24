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
import com.xiii.libertycity.core.displays.BossBarDisplay;
import com.xiii.libertycity.core.utils.FileUtils;
import com.xiii.libertycity.core.utils.LagCalculator;
import com.xiii.libertycity.core.utils.MoneyUtils;
import com.xiii.libertycity.discord.commands.SlashCommand;
import com.xiii.libertycity.roleplay.CustomChat;
import com.xiii.libertycity.roleplay.events.AnkleBreakEvent;
import com.xiii.libertycity.roleplay.events.DeathEvent;
import com.xiii.libertycity.roleplay.events.RegisterEvent;
import com.xiii.libertycity.roleplay.guis.ATMGui;
import com.xiii.libertycity.roleplay.guis.BinGui;
import com.xiii.libertycity.roleplay.items.IDCard;
import com.xiii.libertycity.roleplay.items.SearchItem;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
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

    // DEV: MTA2NTMxNDQ1NDk4NTc4NTQxNg.GJBEwB.YfdVOSjaLbwFQ3FN2Q3_B07xKaR52K_98JRftM
    // OFI: MTA2Mjc5Mjg2ODk2NTY2MjcyMA.G8-6hq.7UHoeLfkMn6K04KuqDX7IXFKsUe28gPCGOeSBI

    public static LibertyCity INSTANCE;
    public static Tabbed tabInstance;
    public static Plugin getInstance() {
        return INSTANCE;
    }
    public static BossBar bossBar;
    private final String BOT_TOKEN = "MTA2NTMxNDQ1NDk4NTc4NTQxNg.GJBEwB.YfdVOSjaLbwFQ3FN2Q3_B07xKaR52K_98JRftM";
    private static JDA jda = null;

    @Override
    public void onEnable() {
        INSTANCE = this;
        tabInstance = new Tabbed(this);
        bossBar = Bukkit.createBossBar("§4§LERROR_43", BarColor.RED, BarStyle.SEGMENTED_6);
        BossBarDisplay.init();

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new LagCalculator(), 100L, 1L);

        FileUtils.readPlayerData();
        Data.data.registerServer(Bukkit.getServer());
        FileUtils.readServerData();

        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Bukkit.getPluginManager().registerEvents(new ATMGui(), this);
        Bukkit.getPluginManager().registerEvents(new BinGui(), this);
        Bukkit.getPluginManager().registerEvents(new RegisterEvent(), this);
        Bukkit.getPluginManager().registerEvents(new AnkleBreakEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DeathEvent(), this);
        Bukkit.getPluginManager().registerEvents(new CustomChat(), this);
        Bukkit.getPluginManager().registerEvents(new SearchItem(), this);
        Bukkit.getPluginManager().registerEvents(new IDCard(), this);

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
        }, 2);


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
        }, 4);

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
            Bukkit.getPluginCommand("gms").setExecutor(new GamemodeCommand.GMS());
            Bukkit.getPluginCommand("gmc").setExecutor(new GamemodeCommand.GMC());
            Bukkit.getPluginCommand("gma").setExecutor(new GamemodeCommand.GMA());
            Bukkit.getPluginCommand("gmsp").setExecutor(new GamemodeCommand.GMSP());
            Bukkit.getPluginCommand("warn").setExecutor(new WarnCommand());
            Bukkit.getPluginCommand("warns").setExecutor(new WarnsCommand());
            Bukkit.getPluginCommand("tpall").setExecutor(new TeleportCommand.TPAll());
            Bukkit.getPluginCommand("tphere").setExecutor(new TeleportCommand.TPHere());
        }, 6);

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> FileUtils.saveServerData(Data.data.getServerData(Bukkit.getServer())), 20, 20);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> jda.getPresence().setActivity(Activity.watching(Bukkit.getOnlinePlayers().size() - Data.data.getServerData(Bukkit.getServer()).vanishedPlayers.size() + " joueurs")), 20, 20);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage("§8§m+---------------------------------------+");
                p.sendMessage("§2§lLiberty§a§lCity §7» §fVous avez reçu §6$10 §7(Aide de l'Etat)");
                p.sendMessage("§8§m+---------------------------------------+");
                MoneyUtils.addMoney(p, 10);
            }
        },600*20, 600*20);

        // Discord
        jda = JDABuilder.createDefault(BOT_TOKEN)
                .addEventListeners(new SlashCommand())
                .setActivity(Activity.watching("??? joueurs"))
                .build();

        CommandListUpdateAction commands = jda.updateCommands();

        commands.addCommands(
                Commands.slash("ping", "Calcule la latence entre le bot et discord."));

        commands.addCommands(
                Commands.slash("lookup", "Lookup les informations RP/HRP d'un joueur.")
                        .addOptions(new OptionData(OptionType.STRING, "joueur", "Pseudo du joueur a lookup")
                                .setRequired(true))
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.VIEW_AUDIT_LOGS))
        );

        commands.queue();

    }

    @Override
    public void onDisable() {
        FileUtils.saveServerData(Data.data.getServerData(Bukkit.getServer()));
        for(Player p : Bukkit.getOnlinePlayers()) FileUtils.savePlayerData(Data.data.getUserData(p));
        Bukkit.getScheduler().cancelAllTasks();
    }
}
