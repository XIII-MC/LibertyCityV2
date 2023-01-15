package com.xiii.libertycity.discord.commands;

import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.utils.ChatUtils;
import com.xiii.libertycity.core.utils.ConvertUtils;
import com.xiii.libertycity.core.utils.HTTPUtils;
import com.xiii.libertycity.core.utils.TimeUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.utils.FileUpload;
import net.dv8tion.jda.api.utils.TimeFormat;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.File;
import java.util.UUID;

public class SlashCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        // make sure we handle the right command

        InteractionHook hook = event.getHook(); // This is a special webhook that allows you to send messages without having permissions in the channel and also allows ephemeral messages

        switch (event.getName()) {

            // /ping command
            case "ping":
                long time = System.currentTimeMillis();
                event.reply("Pong!").setEphemeral(true) // reply or acknowledge
                        .flatMap(v ->
                                event.getHook().editOriginalFormat("Réponse: %d ms", (System.currentTimeMillis() - time)) // then edit original
                        ).queue(); // Queue both reply and edit
                break;

            case "lookup":

                String joueur = event.getOption("joueur",
                        OptionMapping::getAsString); // used if getOption("reason") is not null (provided)

                final String uuid = HTTPUtils.readUrl("https://api.mojang.com/users/profiles/minecraft/" + joueur).substring(24).replace("}", "").replace("\"", "");
                final StringBuilder stringBuilder = new StringBuilder(uuid);
                stringBuilder.insert(8, "-").insert(13, "-").insert(18, "-").insert(23, "-");
                //OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(stringBuilder.toString()));
                PlayerData data = Data.data.getUserData(stringBuilder.toString());

                event.reply("Recherche...").setEphemeral(true).flatMap(v ->
                        event.getHook().editOriginalFormat(data != null ? "Joueur trouvé!" : "Joueur non existant dans la base de données.") // then edit original
                ).queue(); // Queue both reply and edit
                if(data == null) return;

                File punishmentLog = new File(LibertyCity.INSTANCE.getDataFolder() + "/server/punishments/" + stringBuilder + ".yml");
                BanList banList = Bukkit.getBanList(BanList.Type.NAME);
                BanList banListIP = Bukkit.getBanList(BanList.Type.IP);
                EmbedBuilder builder = new EmbedBuilder();
                if(data.onlineState) builder.setColor(Color.GREEN);
                else builder.setColor(Color.RED);
                if(banList.isBanned(joueur) || banList.getBanEntries().contains(joueur) || banListIP.isBanned(joueur) || data.isMuted) builder.setColor(Color.ORANGE);
                builder.setAuthor("Lookup de " + joueur);
                builder.setTitle("Informations concernant le lookup de " + joueur);
                builder.setDescription("<:312314752711786497:696794019946299423> Le joueur a déja rejoint et crée un compte." + "\n" + (punishmentLog.exists() ? "<:312314752711786497:696794019946299423> Le joueur a un historique de sanctions." : "<:312314733816709120:696794019895836782> Le joueur n'a pas d'historique de sanctions.") + "\n" + (banList.isBanned(joueur) ? "<:312314752711786497:696794019946299423> Le joueur est actuellement banni." : "<:312314733816709120:696794019895836782> Le joueur n'est actuellement pas banni.") + "\n" + (banListIP.isBanned(joueur) ? "<:312314752711786497:696794019946299423> Le joueur est actuellement banni IP." : "<:312314733816709120:696794019895836782> Le joueur n'est actuellement pas banni IP.") + "\n" + (data.isMuted ? "<:312314752711786497:696794019946299423> Le joueur est actuellement muet." : "<:312314733816709120:696794019895836782> Le joueur n'est pas actuellement muet."));
                builder.setThumbnail("https://mc-heads.net/Avatar/" + joueur + "/128.pnj");
                builder.addField("Prénom RP", String.valueOf(data.rpPrenom), true);
                builder.addField("Nom RP", String.valueOf(data.rpNom), true);
                builder.addField("Âge RP", String.valueOf(data.rpAge), true);
                builder.addField("Banque RP", "$" + ConvertUtils.format(data.rpBank) + " ||(" + data.rpBank + ")||", true);
                builder.addField("Job RP", String.valueOf(ChatUtils.removeColorCodes(data.rpCurrentJob)), true);
                builder.addField("Date de création", String.valueOf(data.joinDate), true);
                builder.setFooter("Demandé par " + event.getUser().getAsTag() + " | " + TimeUtil.getFullDate());

                TextChannel textChannel = (TextChannel) event.getMessageChannel();
                if(punishmentLog.exists()) textChannel.sendMessageEmbeds(builder.build()).addFiles(FileUpload.fromData(punishmentLog)).queue();
                else textChannel.sendMessageEmbeds(builder.build()).queue();

                break;
        }
    }
}
