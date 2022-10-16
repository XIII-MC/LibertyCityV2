package com.xiii.libertycity.roleplay.events;

import com.xiii.libertycity.LibertyCity;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class AnkleBreakEvent implements Listener {

    private int chancePercentage = 0;

    @EventHandler
    public void forAnkleBreak(EntityDamageEvent e) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(LibertyCity.INSTANCE, () -> {

            if(e.getEntity() instanceof Player) {
                if(e.getCause() == EntityDamageEvent.DamageCause.FALL) {

                    int a = new Random().nextInt(10);
                    if(a < 60) {
                        chancePercentage = 60;
                    } else if (a < 9) {
                        chancePercentage = 30;
                    } else {
                        chancePercentage = 10;
                    }

                    if(chancePercentage <= 100) {
                        PotionEffect potionEffect = new PotionEffect(PotionEffectType.SLOW, 30, 2);
                        potionEffect.apply((LivingEntity) e.getEntity());
                        PotionEffect potEffect = new PotionEffect(PotionEffectType.BLINDNESS, 3, 2);
                        potEffect.apply((LivingEntity) e.getEntity());
                        e.getEntity().sendMessage("§4(⚠) §cVous vous êtes foulé une cheville !");
                    }

                }
            }

        });
    }

}
