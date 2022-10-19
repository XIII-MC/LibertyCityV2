package com.xiii.libertycity.roleplay.events;

import com.xiii.libertycity.LibertyCity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AnkleBreakEvent implements Listener {

    private double fallDistance;

    @EventHandler(priority = EventPriority.LOWEST)
    public void forFallDistanceCalculation(PlayerMoveEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {
            if(e.getPlayer().getFallDistance() > 0) fallDistance = e.getPlayer().getFallDistance();
        });
    }

    @EventHandler
    public void forAnkleBreak(EntityDamageEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(LibertyCity.INSTANCE, () -> {

            if(e.getEntity() instanceof Player) {
                if(e.getCause() == EntityDamageEvent.DamageCause.FALL) {

                    double a = Math.random() * 100;

                    if(a <= 30 || fallDistance >= 4.1f) {
                        Bukkit.getScheduler().runTask(LibertyCity.INSTANCE, () -> {
                            ((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (int) (100 * fallDistance), (int) fallDistance / 2));
                            ((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 2));
                            ((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, (int) (100 * fallDistance), 250));
                        });
                        e.getEntity().sendMessage("§4§l⚠ §cVous vous êtes foulé une cheville !");
                    }

                }
            }

        });
    }

}
