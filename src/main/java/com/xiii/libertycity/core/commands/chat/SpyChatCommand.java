package com.xiii.libertycity.core.commands.chat;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpyChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("spy")) {
            if(sender instanceof Player) {
                PlayerData data = Data.data.getUserData((Player) sender);
                if (args.length == 0) {
                    if (!data.spyChatGlobal) {
                        data.spyChatGlobal = true;
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §fSpy chat §6Global §aactivé");
                    } else {
                        data.spyChatGlobal = false;
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §fSpy chat §6Global §cdésactivé");
                    }
                } else {
                    if (args[0].equalsIgnoreCase("rp") || args[0].equalsIgnoreCase("hrp") || args[0].equalsIgnoreCase("police") || args[0].equalsIgnoreCase("gang") || args[0].equalsIgnoreCase("msg")) {
                        if (args[0].equalsIgnoreCase("rp")) {
                            if (!data.spyChatRP) {
                                data.spyChatRP = true;
                                sender.sendMessage("§2§lLiberty§a§lCity §7» §fSpy chat §6RP §aactivé");
                            } else {
                                data.spyChatRP = false;
                                sender.sendMessage("§2§lLiberty§a§lCity §7» §fSpy chat §6RP §cdésactivé");
                            }
                        }

                        if (args[0].equalsIgnoreCase("hrp")) {
                            if (!data.spyChatHRP) {
                                data.spyChatHRP = true;
                                sender.sendMessage("§2§lLiberty§a§lCity §7» §fSpy chat §6HRP §aactivé");
                            } else {
                                data.spyChatHRP = false;
                                sender.sendMessage("§2§lLiberty§a§lCity §7» §fSpy chat §6HRP §cdésactivé");
                            }
                        }

                        if (args[0].equalsIgnoreCase("police")) {
                            if (!data.spyChatPolice) {
                                data.spyChatPolice = true;
                                sender.sendMessage("§2§lLiberty§a§lCity §7» §fSpy chat §6Police §aactivé");
                            } else {
                                data.spyChatPolice = false;
                                sender.sendMessage("§2§lLiberty§a§lCity §7» §fSpy chat §6Police §cdésactivé");
                            }
                        }

                        if (args[0].equalsIgnoreCase("gang")) {
                            if (!data.spyChatGang) {
                                data.spyChatGang = true;
                                sender.sendMessage("§2§lLiberty§a§lCity §7» §fSpy chat §6Gang §aactivé");
                            } else {
                                data.spyChatGang = false;
                                sender.sendMessage("§2§lLiberty§a§lCity §7» §fSpy chat §6Gang §cdésactivé");
                            }
                        }

                        if (args[0].equalsIgnoreCase("msg")) {
                            if (!data.spyMsg) {
                                data.spyMsg = true;
                                sender.sendMessage("§2§lLiberty§a§lCity §7» §fSpy chat §6Msg §aactivé");
                            } else {
                                data.spyMsg = false;
                                sender.sendMessage("§2§lLiberty§a§lCity §7» §fSpy chat §6Msg §cdésactivé");
                            }
                        }
                    }
                }
            } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous recevez déja tous les messages");
        }

        return true;
    }
}
