package com.xiii.libertycity.discord.commands;

import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.utils.HTTPUtils;
import com.xiii.libertycity.core.utils.TimeUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.utils.TimeFormat;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.awt.*;
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

                event.reply("Recherche...");

                final String uuid = HTTPUtils.readUrl("https://api.mojang.com/users/profiles/minecraft/" + joueur).substring(24).replace("}", "").replace("\"", "");
                final StringBuilder stringBuilder = new StringBuilder(uuid);
                stringBuilder.insert(8, "-").insert(13, "-").insert(18, "-").insert(23, "-");
                Bukkit.getConsoleSender().sendMessage(stringBuilder + " " + uuid);
                PlayerData data = Data.data.getUserData(stringBuilder.toString());

                if(data == null) event.reply("Joueur non existant.");
                if(data != null) event.reply("Joueur trouvé!");
                EmbedBuilder builder = new EmbedBuilder();
                if(data.rpAge == 0) builder.setColor(Color.WHITE);
                //if(data.player.isOnline()) builder.setColor(Color.GREEN);
                //if(!data.player.isOnline()) builder.setColor(Color.RED);
                //if(data.player.isBanned()) builder.setColor(Color.ORANGE);
                builder.setAuthor("Lookup de " + joueur);
                builder.setImage("https://mc-heads.net/Avatar/" + joueur + "/128.pnj");
                builder.addField("ID", String.valueOf(data.playerID), false);
                builder.addField("Prénom RP", String.valueOf(data.rpPrenom), false);
                builder.addField("Nom RP", String.valueOf(data.rpNom), false);
                builder.addField("Âge RP", String.valueOf(data.rpAge), false);
                builder.addField("Bank RP", String.valueOf(data.rpBank), false);
                builder.addField("Job RP", String.valueOf(data.rpCurrentJob), false);
                builder.setFooter("Demandé par " + event.getUser().getAsTag() + " | " + TimeUtil.getFullDate());

                TextChannel textChannel = (TextChannel) event.getMessageChannel();
                textChannel.sendMessageEmbeds(builder.build()).queue();

                break;
        }
    }
}
