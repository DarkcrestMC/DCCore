package com.Jacksonnn.DCCore.Suggestions;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.GeneralMethods;
import github.scarsz.discordsrv.dependencies.jda.api.EmbedBuilder;
import github.scarsz.discordsrv.dependencies.jda.api.entities.Emote;
import github.scarsz.discordsrv.dependencies.jda.api.entities.Message;
import github.scarsz.discordsrv.dependencies.jda.api.entities.MessageEmbed;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;
import github.scarsz.discordsrv.util.DiscordUtil;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Consumer;

public class SuggestionsHandler {
    private ArrayList<Suggestion> suggestions;
    private int numSuggestions;

    public SuggestionsHandler() {
        suggestions = new ArrayList<>();
    }

    public void addSuggestion(Player suggester, String description) {
        new Suggestion(suggester, description);
    }

    public boolean voteOnSuggestion(int id, Player voter, boolean approve) {
        for (Suggestion suggestion : suggestions)
            if (suggestion.id == id) {
                suggestion.voteOnSuggestion(voter, approve);
                return true;
            }
        return false;
    }

    public class Suggestion {
        private final ArrayList<UUID> votersApprove;
        private final ArrayList<UUID> votersDisapprove;
        private final UUID suggesterUUID;
        private final String suggesterName;
        private final String description;
        private final int id;
        private String messageId;

        public Suggestion(Player player, String description) {
            votersApprove = new ArrayList<>();
            votersDisapprove = new ArrayList<>();
            suggesterName = player.getName();
            suggesterUUID = player.getUniqueId();
            this.description = description;
            id = numSuggestions;
            numSuggestions++;

            votersApprove.add(suggesterUUID);
            suggestions.add(this);
            createMinecraftMessage();
            createDiscordMessage();
            Bukkit.getScheduler().scheduleSyncDelayedTask(DCCore.plugin, new Runnable() {
                @Override public void run() {
                    remove();
                }
            }, ConfigManager.defaultConfig.get().getInt("Suggestions.lifespanInTicks"));
        }

        public void createMinecraftMessage() {
            Bukkit.broadcastMessage(ChatColor.UNDERLINE.toString() + ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "!" +
                    ChatColor.DARK_GREEN + "] " + ChatColor.GREEN + suggesterName + " is making a new suggestion!");
            Bukkit.broadcastMessage(ChatColor.GREEN + description);

            TextComponent clickToApprove = new TextComponent(ChatColor.AQUA.toString() + ChatColor.BOLD + "[Click to Approve]");
            clickToApprove.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/suggest APPROVE " + id));
            TextComponent clickToDisapprove = new TextComponent(ChatColor.RED.toString() + ChatColor.BOLD + "[Click to Disapprove]");
            clickToDisapprove.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/suggest DISAPPROVE" + id));

            BaseComponent message = new TextComponent(clickToApprove);
            message.addExtra(" ");
            message.addExtra(clickToDisapprove);

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!player.getUniqueId().equals(suggesterUUID)) {
                    player.spigot().sendMessage(message);
                }
            }
        }
        public void createDiscordMessage() {
            TextChannel suggestionsChannel = DiscordUtil.getTextChannelById(ConfigManager.defaultConfig.get()
                    .getString("Suggestions.ChannelID"));
            suggestionsChannel.sendMessage(getEmbeddedMessage()).queue(new Consumer<Message>() {
                @Override public void accept(Message message) {
                    messageId = message.getId();
                    message.addReaction("🔼").queue(new Consumer<Void>() {
                        @Override
                        public void accept(Void aVoid) {
                            message.addReaction("🔽").queue();
                        }
                    });
                }
            });
        }
        public void updateDiscordMessage() {
            if (messageId == null)
                return;
            TextChannel suggestionsChannel = DiscordUtil.getTextChannelById(ConfigManager.defaultConfig.get()
                .getString("Suggestions.ChannelID"));
            suggestionsChannel.editMessageById(messageId, getEmbeddedMessage()).queue();
        }

        private MessageEmbed getEmbeddedMessage() {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setAuthor(suggesterName);
            builder.setTitle("New Suggestion");
            builder.setDescription(description);
            builder.setColor(new Color(113, 255, 238, 255));
            builder.setFooter("In-game votes: " + votersApprove.size() + (votersApprove.size() == 1 ? " approval, " : " approvals, ") +
                    votersDisapprove.size() + (votersDisapprove.size() == 1 ? " disapproval" : " disapprovals"));

            return builder.build();
        }

        public void voteOnSuggestion(Player voter, boolean approve) {
            UUID uuid = voter.getUniqueId();
            if (uuid.equals(suggesterUUID)) {
                voter.sendMessage(GeneralMethods.errorColor + "You can't vote on your own suggestion!");
            } else if (approve) {
                if (votersApprove.contains(voter.getUniqueId()))
                    voter.sendMessage(GeneralMethods.errorColor + "You already approved this suggestion!");
                else if (votersDisapprove.contains(uuid)) {
                    votersDisapprove.remove(uuid);
                    votersApprove.add(uuid);
                    voter.sendMessage(GeneralMethods.prefix + "You changed your vote to approve!");
                    updateDiscordMessage();
                } else {
                    votersApprove.add(uuid);
                    voter.sendMessage(GeneralMethods.prefix + "You voted to approve!");
                    updateDiscordMessage();
                }
            } else {
                if (votersDisapprove.contains(voter.getUniqueId()))
                    voter.sendMessage(GeneralMethods.errorColor + "You already disapproved this suggestion!");
                else if (votersApprove.contains(uuid)) {
                    votersApprove.remove(uuid);
                    votersDisapprove.add(uuid);
                    voter.sendMessage(GeneralMethods.prefix + "You changed your vote to disapprove!");
                    updateDiscordMessage();
                } else {
                    votersDisapprove.add(uuid);
                    voter.sendMessage(GeneralMethods.prefix + "You voted to disapprove!");
                    updateDiscordMessage();
                }
            }
        }
        public void remove() {
            suggestions.remove(this);
            Bukkit.broadcastMessage(GeneralMethods.prefix + suggesterName + "'s suggestion received " +
                    ChatColor.AQUA + ChatColor.BOLD + votersApprove.size() + ChatColor.RESET + ChatColor.YELLOW + " approvals and " +
                    ChatColor.RED + ChatColor.BOLD + votersDisapprove.size() + ChatColor.RESET + ChatColor.YELLOW + " disapprovals.");
            updateDiscordMessage();
        }

        public String getSuggesterName() {
            return suggesterName;
        }
        public String getDescription() {
            return description;
        }
        public int getId() {
            return id;
        }
    }

}
