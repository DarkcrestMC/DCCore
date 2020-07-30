package com.Jacksonnn.DCCore.Suggestions;

import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SuggestCommand implements CommandExecutor {
    @Override public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to make a suggestion!");
            return true;
        }
        Player player = (Player)sender;
        if (args.length == 0) {
            sender.sendMessage(GeneralMethods.prefix + "Make a suggestion to improve the server! Type" +
                    "\"/suggest <suggestion>\", and the server staff and players will vote to approve it!");
            return true;
        } else {
            if (args.length == 2) {
                switch (args[0]) {
                    case "APPROVE":
                    case "DISAPPROVE":
                        // voting to approve/disapprove
                        boolean approve = args[0].equals("APPROVE");
                        try {
                            int suggestionId = Integer.parseInt(args[1]);
                            if (DCCore.getSuggestionsHandler().voteOnSuggestion(suggestionId, player, approve))
                                return true; // if successful, SuggestionHandler will deal with everything else.
                                             // otherwise, assume player is manually making a new suggestion
                        } catch (Exception ignored) {}
                }
            }
            // creating new suggestion
            StringBuilder suggestion = new StringBuilder();
            for (int i = 0 ; i < args.length; i++) {
                suggestion.append(args[i]);
                if (i != args.length - 1)
                    suggestion.append(" ");
            }
            DCCore.getSuggestionsHandler().addSuggestion(player, suggestion.toString());
        }
        return true;
    }
}
