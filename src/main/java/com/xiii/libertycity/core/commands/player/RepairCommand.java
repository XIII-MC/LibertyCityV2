package com.xiii.libertycity.core.commands.player;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RepairCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("repair")) {
            if(sender instanceof Player) {
                Player snder = (Player) sender;
                if (snder.getItemInHand() != null && snder.getItemInHand() != new ItemStack(Material.AIR)) {
                    snder.getItemInHand().setDurability((short) 0);
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §fVotre item a été réparé");
                } else {
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §cAttention! Item invalide");
                }
            } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");
        }

        return true;
    }
}
