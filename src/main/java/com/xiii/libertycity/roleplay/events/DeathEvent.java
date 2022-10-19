package com.xiii.libertycity.roleplay.events;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.utils.AlertUtil;
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
                if (e.getDeathMessage().contains("killed")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a été tué au corp à corp par " + killerData.rpPrenom + " " + killerData.rpNom + " §8(" + killer.getName() + ")", "LibertyCity.staff.deathmessages", 4);
                if (e.getDeathMessage().contains("shot")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ")s'est fait tirer dessus par "  + killerData.rpPrenom + " " + killerData.rpNom + " §8(" + killer.getName() + ")", "LibertyCity.staff.deathmessages", 4);

            } else {

                // Arrow shot related death
                if(e.getDeathMessage().contains("shot")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7c'est fait tirer dessus.", "LibertyCity.staff.deathmessages", 4);

                // Snowball related death (impossible)
                if(e.getDeathMessage().contains("pummeled")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7est mort d'une façon ETRANGE.", "LibertyCity.staff.deathmessages", 4);

                // Cactus related death
                if(e.getDeathMessage().contains("pricked")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7c'est froté a un cactus...", "LibertyCity.staff.deathmessages", 4);

                // Drowning related death
                if(e.getDeathMessage().contains("drowned")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7c'est noyé.", "LibertyCity.staff.deathmessages", 4);

                // Elytra related death
                if(e.getDeathMessage().contains("energy")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a perdu les manettes!", "LibertyCity.staff.deathmessages", 4);

                // Explosions related death
                if(e.getDeathMessage().contains("blew")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a explosé", "LibertyCity.staff.deathmessages", 4);

                // [Intentional Game Design] death
                if(e.getDeathMessage().contains("intentional")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a perdu la tête!", "LibertyCity.staff.deathmessages", 4);

                // Fall related death
                if(e.getDeathMessage().contains("ground too hard")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a fait une mauvaise récéption!", "LibertyCity.staff.deathmessages", 4);

                // High fall related death
                if(e.getDeathMessage().contains("high place")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7est tombé de trop haut!", "LibertyCity.staff.deathmessages", 4);

                // Ladder fall related death
                if(e.getDeathMessage().contains("off a ladder") || e.getDeathMessage().contains("vines")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a glissé d'une échelle!", "LibertyCity.staff.deathmessages", 4);

                // Smashed by a block related death
                if(e.getDeathMessage().contains("squashed")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7c'est fait ecrasé par une enclume!", "LibertyCity.staff.deathmessages", 4);

                // Fire related death
                if(e.getDeathMessage().contains("in flames")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7est partis en fumée", "LibertyCity.staff.deathmessages", 4);

                // Fire death related death
                if(e.getDeathMessage().contains("burned")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a voulu faire le pyroman", "LibertyCity.staff.deathmessages", 4);

                // Firework related death
                if(e.getDeathMessage().contains("bang")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7est resté trop près des feux d'artifices!", "LibertyCity.staff.deathmessages", 4);

                // Swim in lava related death
                if(e.getDeathMessage().contains("swim in lava")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a esseyé de nager dans de la lave", "LibertyCity.staff.deathmessages", 4);

                // Lightning related death
                if(e.getDeathMessage().contains("lightning")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7c'est fait frapper par la foudre!", "LibertyCity.staff.deathmessages", 4);

                // Magma block related death
                if(e.getDeathMessage().contains("floor was lava")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a macher sur le mauvais block!", "LibertyCity.staff.deathmessages", 4);

                // Magic related death
                if(e.getDeathMessage().contains("magic")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7est mort par la magie (ooOOOOOhhHH)", "LibertyCity.staff.deathmessages", 4);

                // Stravation related death
                if(e.getDeathMessage().contains("starved")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7est mort de faim.", "LibertyCity.staff.deathmessages", 4);

                // Suffocation related death
                if(e.getDeathMessage().contains("suffocated")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7c'est étouffé.", "LibertyCity.staff.deathmessages", 4);

                // Thorns related death
                if(e.getDeathMessage().contains("trying to hurt")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7a trop esseyé!", "LibertyCity.staff.deathmessages", 4);

                // Void death
                if(e.getDeathMessage().contains("world") || e.getDeathMessage().contains("died")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7est mort.", "LibertyCity.staff.deathmessages", 4);

                // Wither related death
                if(e.getDeathMessage().contains("withered")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7c'est fait tuer par le wither.", "LibertyCity.staff.deathmessages", 4);

                // Shot at death by an entity other than a player
                if(e.getDeathMessage().contains("shot")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7s'est fait tirer dessus.", "LibertyCity.staff.deathmessages", 4);

                // Killed by an entity other than a player
                if(e.getDeathMessage().contains("slain")) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7à été tué", "LibertyCity.staff.deathmessages", 4);

            }

            if (victim == killer) AlertUtil.staffAlert("§4§l☠ §7" + victimData.rpPrenom + " " + victimData.rpNom + " §8(" + victim.getName() + ") §7c'est humilié.", "LibertyCity.staff.deathmessages", 4);
            e.setDeathMessage("");
        }
    }

}
