package com.xiii.libertycity;

import com.keenant.tabbed.Tabbed;
import com.xiii.libertycity.core.Events;
import com.xiii.libertycity.core.commands.chat.*;
import com.xiii.libertycity.core.commands.player.*;
import com.xiii.libertycity.core.commands.punish.*;
import com.xiii.libertycity.core.commands.server.*;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.utils.ChatUtils;
import com.xiii.libertycity.core.utils.FileUtils;
import com.xiii.libertycity.core.utils.LagCalculator;
import com.xiii.libertycity.roleplay.CustomChat;
import com.xiii.libertycity.roleplay.events.AnkleBreakEvent;
import com.xiii.libertycity.roleplay.events.DeathEvent;
import com.xiii.libertycity.roleplay.events.RegisterEvent;
import com.xiii.libertycity.roleplay.guis.*;
import com.xiii.libertycity.roleplay.items.SearchItem;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class LibertyCity extends JavaPlugin {

    public static LibertyCity INSTANCE;
    public static Tabbed tabInstance;
    public static Plugin getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        tabInstance = new Tabbed(this);

        Data.data.registerServer(Bukkit.getServer());
        FileUtils.readServerData();
        FileUtils.readPlayerData();

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new LagCalculator(), 100L, 1L);

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
        Bukkit.getConsoleSender().sendMessage("Plugin loaded");

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            if(ClearLagCommand.getItemsOnTheGround() > 0) Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §FLes éboueurs ramasseront tout les déchets dans §630 seconde§f!");
        },270*20, 270*20);

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            if(ClearLagCommand.getItemsOnTheGround() > 0) Bukkit.broadcastMessage("§2§lLiberty§a§lCity §7» §FLes éboueurs ramasseront tout les déchets dans §610 seconde§f!");
        },300*20, 300*20);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            if(ClearLagCommand.getItemsOnTheGround() > 0) Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "clearlag");
        },310*20, 310*20);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            Bukkit.broadcastMessage("");
            ChatUtils.sendCenteredMessage(null, "§8[§a§lINFORMATION§8]§e", true);
            Bukkit.broadcastMessage("§8» §fUne question, un problème ? Utiliser §6/helpop §f!");
            Bukkit.broadcastMessage("§8» §fUn FreeKill, joueur non RP ? Utilisez §6/report <Joueur> <Raison>");
            Bukkit.broadcastMessage("§8» §fLes recrutement staff sont §a§nouvert§r§f !");
            Bukkit.broadcastMessage("");
        },1800*20, 1800*20);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage("§8§m+---------------------------------------+");
                p.sendMessage("§2§lLiberty§a§lCity §7» §fVous avez reçu §6$10 §7(Aide de l'Etat)");
                p.sendMessage("§8§m+---------------------------------------+");
                PlayerData pData = Data.data.getUserData(p);
                pData.rpBank += 10;

                // AntiAFK
                if((pData.lastX - p.getLocation().getX() <= 5 && pData.lastX - p.getLocation().getX() >= -5) && (pData.lastZ - p.getLocation().getZ() <= 5 && pData.lastZ - p.getLocation().getZ() >= -5)) {
                    p.sendTitle("§4§l§k|||§r §c§lAFK §4§l§k|||§r", "§7Veuillez bouger ou vous serez §cexplusé§7.", 0, 9*20, 2);
                    Bukkit.getScheduler().runTaskLater(LibertyCity.INSTANCE, () -> {
                        if((pData.lastX - p.getLocation().getX() <= 5 && pData.lastX - p.getLocation().getX() >= -5) && (pData.lastZ - p.getLocation().getZ() <= 5 && pData.lastZ - p.getLocation().getZ() >= -5)) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kick " + p.getName() + " AFK-TX (10+ minutes)");
                    }, 10*20);
                }

                if((pData.lastZ - p.getLocation().getZ() <= 5 && pData.lastZ - p.getLocation().getZ() >= -5) && (pData.lastX - p.getLocation().getX() <= 5 && pData.lastX - p.getLocation().getX() >= -5)) {
                    p.sendTitle("§4§l§k|||§r §c§lAFK §4§l§k|||§r", "§7Veuillez bouger ou vous serez §cexplusé§7.", 0, 9*20, 2);
                    Bukkit.getScheduler().runTaskLater(LibertyCity.INSTANCE, () -> {
                        if((pData.lastZ - p.getLocation().getZ() <= 5 && pData.lastZ - p.getLocation().getZ() >= -5) && (pData.lastX - p.getLocation().getX() <= 5 && pData.lastX - p.getLocation().getX() >= -5)) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kick " + p.getName() + " AFK-TZ (10+ minutes)");
                    }, 10*20);
                }

                pData.lastX = p.getLocation().getX();
                pData.lastZ = p.getLocation().getZ();

            }
        },600*20, 600*20);



    }

    @Override
    public void onDisable() {
        FileUtils.saveServerData(Data.data.getServerData(Bukkit.getServer()));
        Bukkit.getScheduler().cancelAllTasks();
    }
}
