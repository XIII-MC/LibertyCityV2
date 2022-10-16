package com.xiii.libertycity.roleplay.events;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {

    @EventHandler
    public void forDeathEvent(PlayerDeathEvent e) {

        Player victim = e.getEntity();
        Player killer = e.getEntity().getKiller();

        if (victim != null) {

            PlayerData victimData = Data.data.getUserData(victim);

            if (killer != null) {

                PlayerData killerData = Data.data.getUserData(killer);
                if (e.getDeathMessage().contains("killed")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a été tué au corp à corp par " + killerData.rpPrenom + " " + killerData.rpNom + " §8(" + killer.getName() + ")");
                if (e.getDeathMessage().contains("shot")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ")s'est fait tirer dessus par "  + killerData.rpPrenom + " " + killerData.rpNom + " §8(" + killer.getName() + ")");

            } else {

                // Arrow shot related death
                if(e.getDeathMessage().contains("shot")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7c'est fait tirer dessus.");

                // Snowball related death (impossible)
                if(e.getDeathMessage().contains("pummeled")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7est mort d'une façon ETRANGE.");

                // Cactus related death
                if(e.getDeathMessage().contains("pricked")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7c'est froté a un cactus...");

                // Drowning related death
                if(e.getDeathMessage().contains("drowned")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7c'est noyé.");

                // Elytra related death
                if(e.getDeathMessage().contains("energy")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a perdu les manettes!");

                // Explosions related death
                if(e.getDeathMessage().contains("blew")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a explosé");

                // [Intentional Game Design] death
                if(e.getDeathMessage().contains("intentional")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a perdu la tête!");

                // Fall related death
                if(e.getDeathMessage().contains("ground too hard")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a fait une mauvaise récéption!");

                // High fall related death
                if(e.getDeathMessage().contains("high place")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7est tombé de trop haut!");

                // Ladder fall related death
                if(e.getDeathMessage().contains("off a ladder") || e.getDeathMessage().contains("vines")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a glissé d'une échelle!");

                // Smashed by a block related death
                if(e.getDeathMessage().contains("squashed")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7c'est fait ecrasé par une enclume!");

                // Fire related death
                if(e.getDeathMessage().contains("in flames")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7est partis en fumée");

                // Fire death related death
                if(e.getDeathMessage().contains("burned")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a voulu faire le pyroman");

                // Firework related death
                if(e.getDeathMessage().contains("bang")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7est resté trop près des feux d'artifices!");

                // Swim in lava related death
                if(e.getDeathMessage().contains("swim in lava")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a esseyé de nager dans de la lave");

                // Lightning related death
                if(e.getDeathMessage().contains("lightning")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7c'est fait frapper par la foudre!");

                // Magma block related death
                if(e.getDeathMessage().contains("floor was lava")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a macher sur le mauvais block!");

                // Magic related death
                if(e.getDeathMessage().contains("magic")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7est mort par la magie (ooOOOOOhhHH)");

                // Stravation related death
                if(e.getDeathMessage().contains("starved")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7est mort de faim.");

                // Suffocation related death
                if(e.getDeathMessage().contains("suffocated")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7c'est étouffé.");

                // Thorns related death
                if(e.getDeathMessage().contains("trying to hurt")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a trop esseyé!");

                // Void death
                if(e.getDeathMessage().contains("world") || e.getDeathMessage().contains("died")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7est mort.");

                // Wither related death
                if(e.getDeathMessage().contains("withered")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7c'est fait tuer par le wither.");

                // Shot at death by an entity other than a player
                if(e.getDeathMessage().contains("shot")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7s'est fait tirer dessus.");

                // Killed by an entity other than a player
                if(e.getDeathMessage().contains("slain")) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7à été tué");

            }

            if (victim == killer) e.setDeathMessage("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7c'est humilié.");

        }
    }

}
