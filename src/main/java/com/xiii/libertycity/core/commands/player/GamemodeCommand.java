package com.xiii.libertycity.core.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("gamemode")) {
            if(args.length == 0) sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Usage: /gamemode <Gamemode> (Joueur)");
            else {
                if(args.length == 1) {
                    if(sender instanceof Player) {
                        Player p = (Player) sender;
                        if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creatif")) {
                            p.setGameMode(GameMode.CREATIVE);
                            p.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais en §6créatif");
                        }

                        if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("survie")) {
                            p.setGameMode(GameMode.SURVIVAL);
                            p.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais en §6survie");
                        }

                        if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("aventure")) {
                            p.setGameMode(GameMode.ADVENTURE);
                            p.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais en §6aventure");
                        }

                        if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("spectateur")) {
                            p.setGameMode(GameMode.SPECTATOR);
                            p.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais en §6spectateur");
                        }
                    } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! Vous n'êtes pas un joueur");
                } else if(args.length == 2) {
                    Player target = Bukkit.getServer().getPlayer(args[1]);
                    if(target.isOnline()) {
                        if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creatif")) {
                            target.setGameMode(GameMode.CREATIVE);
                            target.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais en §6créatif");
                            sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fest désormais en §6créatif");
                        }

                        if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("survie")) {
                            target.setGameMode(GameMode.SURVIVAL);
                            target.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais en §6survie");
                            sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fest désormais en §6survie");
                        }

                        if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("aventure")) {
                            target.setGameMode(GameMode.ADVENTURE);
                            target.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais en §6aventure");
                            sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fest désormais en §6aventure");
                        }

                        if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("spectateur")) {
                            target.setGameMode(GameMode.SPECTATOR);
                            target.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais en §6spectateur");
                            sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fest désormais en §6spectateur");
                        }
                    } else sender.sendMessage("§2§lLiberty§a§lCity §7» §cErreur! " + target.getName() + " n'est pas en ligne!");
                }
            }
        }

        return true;
    }

    public static class GMS implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if (command.getName().equalsIgnoreCase("gms")) {
                if (args.length < 1) {
                    Player player = (Player) sender;
                    player.setGameMode(GameMode.SURVIVAL);
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais en §6survie");
                } else if (args.length == 1) {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target.isOnline()) {
                        target.setGameMode(GameMode.SURVIVAL);
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fest désormais en §6survie");
                        target.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais en §6survie");
                    }
                }
            }
            return true;
        }
    }

    public static class GMC implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if (command.getName().equalsIgnoreCase("gmc")) {
                if (args.length < 1) {
                    Player player = (Player) sender;
                    player.setGameMode(GameMode.CREATIVE);
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais en §6créatif");
                } else if (args.length == 1) {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target.isOnline()) {
                        target.setGameMode(GameMode.CREATIVE);
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fest désormais en §6créatif");
                        target.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais en §6créatif");
                    }
                }
            }
            return true;
        }
    }

    public static class GMA implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if (command.getName().equalsIgnoreCase("gma")) {
                if (args.length < 1) {
                    Player player = (Player) sender;
                    player.setGameMode(GameMode.ADVENTURE);
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais en §6aventure");
                } else if (args.length == 1) {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target.isOnline()) {
                        target.setGameMode(GameMode.ADVENTURE);
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fest désormais en §6aventure");
                        target.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais en §6aventure");
                    }
                }
            }
            return true;
        }
    }

    public static class GMSP implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if (command.getName().equalsIgnoreCase("gmsp")) {
                if (args.length < 1) {
                    Player player = (Player) sender;
                    player.setGameMode(GameMode.SPECTATOR);
                    sender.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais en §6spectateur");
                } else if (args.length == 1) {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target.isOnline()) {
                        target.setGameMode(GameMode.SPECTATOR);
                        sender.sendMessage("§2§lLiberty§a§lCity §7» §e" + target.getName() + " §fest désormais en §6spectateur");
                        target.sendMessage("§2§lLiberty§a§lCity §7» §fVous êtes désormais en §6spectateur");
                    }
                }
            }
            return true;
        }
    }

}
