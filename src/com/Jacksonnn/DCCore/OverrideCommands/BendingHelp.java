package com.Jacksonnn.DCCore.OverrideCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BendingHelp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("1")) {
                sendPage1(sender);
            } else if (args[0].equalsIgnoreCase("2")) {
                sendPage2(sender);
            } else {
                sendPage3(sender);
            }
        } else {
            sendPage1(sender);
        }
        /*
        String configString = ConfigManager.langConfig.get().getString("Language.BendingHelp.Description");

        if (configString == null) {
            sender.sendMessage(GeneralMethods.errorPrefix + "Error! This command is not setup yet. Alert staff!");
            return true;
        }
        String[] strings = configString.split("§");
        if (strings.length == 0) {
            sender.sendMessage(GeneralMethods.errorPrefix + " Error! This command is not setup yet. Alert staff!");
            return true;
        }
        sender.sendMessage(GeneralMethods.prefix + GeneralMethods.translateColorCodes(strings[0]));
        for (int i = 1; i < strings.length; i++) {
            sender.sendMessage(GeneralMethods.translateColorCodes(strings[i]));
        }
        */
//        sender.sendMessage(GeneralMethods.prefix + "Bending Introduction:");
//        sender.sendMessage(ChatColor.of("#E5B100") + "DarkcrestMC uses the " + GeneralMethods.accentColor + ChatColor.BOLD + "ProjectKorra" + ChatColor.of("#E5B100") + " plugin to allow for A:TLA and A:LOK in-game play. As a " + ChatColor.ITALIC + "member" + ChatColor.of("#E5B100") + ", you can choose between the elements (" + ChatColor.GRAY + "air" + ChatColor.of("#E5B100") + ", " + ChatColor.AQUA + "water" + ChatColor.of("#E5B100") + ", " + ChatColor.GREEN + "earth" + ChatColor.of("#E5B100") + ", " + ChatColor.RED + "fire" + ChatColor.of("#E5B100") + ", and " + ChatColor.GOLD + "chi" + ChatColor.of("#E5B100") + ").");
//        sender.sendMessage(ChatColor.of("#E5B100") + "When you become a " + ChatColor.of("#62636e") + "[" + ChatColor.of("#c018d6") + "Merchant" + ChatColor.of("#62636e") + "]" + ChatColor.of("#E5B100") + " rank or higher, you can also start using the " + ChatColor.DARK_AQUA + "Spirit" + ChatColor.of("#E5B100") + " element.");
//        sender.sendMessage(ChatColor.of("#E5B100") + "On DarkcrestMC, you are also able to donate for " + ChatColor.of("#AA00E5") + "Avatar" + ChatColor.of("#E5B100") + "! In order to do so, type " + GeneralMethods.accentColor + ChatColor.BOLD + "/donate" + ChatColor.of("#E5B100") + ". You can also obtain it as a regular player according to " + GeneralMethods.accentColor + ChatColor.BOLD + "/ranks" + ChatColor.of("#E5B100") + ".");
//        sender.sendMessage(ChatColor.of("#E5B100") + "In order to use the " + ChatColor.ITALIC + "SpiritTypes" + ChatColor.of("#E5B100") + ", it works the same as avatar. You can donate for either " + ChatColor.WHITE + "LightSpirit" + ChatColor.of("#E5B100") + " or " + ChatColor.DARK_GRAY + "DarkSpirit" + ChatColor.of("#E5B100") + " through " + GeneralMethods.accentColor + ChatColor.BOLD + "/donate" + ChatColor.of("#E5B100") + ". Otherwise, you could obtain it according to " + GeneralMethods.accentColor + ChatColor.BOLD + "/ranks" + ChatColor.of("#E5B100") + ".");
        return true;
    }

    public void sendPage1(CommandSender sender) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "tellraw " + sender.getName() + " [\"\",{\"text\":\"\\u2635\",\"color\":\"blue\"},{\"text\":\" \\u2637\",\"color\":\"dark_green\"},{\"text\":\" Welcome to Bending on\",\"color\":\"#4DE5E8\"},{\"text\":\" D\",\"bold\":true,\"color\":\"#833995\"},{\"text\":\"a\",\"bold\":true,\"color\":\"#793B98\"},{\"text\":\"r\",\"bold\":true,\"color\":\"#703F9C\"},{\"text\":\"k\",\"bold\":true,\"color\":\"#67429F\"},{\"text\":\"c\",\"bold\":true,\"color\":\"#5D45A3\"},{\"text\":\"r\",\"bold\":true,\"color\":\"#5448A6\"},{\"text\":\"e\",\"bold\":true,\"color\":\"#4C4DA9\"},{\"text\":\"s\",\"bold\":true,\"color\":\"#4F5DAD\"},{\"text\":\"t\",\"bold\":true,\"color\":\"#536EB1\"},{\"text\":\"MC\",\"bold\":true,\"color\":\"#A74482\"},{\"text\":\"!\",\"color\":\"#4DE5E8\"},{\"text\":\" \\u2632\",\"color\":\"red\"},{\"text\":\" \\u2630\",\"color\":\"#A6D5CC\"},{\"text\":\"\\n\\n\"}," +
                        "{\"text\":\"Brand new to bending? Want to get set up quick? Check out the bending guide in the tree at \",\"color\":\"#CDF8FF\"},{\"text\":\"/spawn\",\"bold\":true,\"underlined\":true,\"color\":\"#CDF8FF\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/spawn\"}},{\"text\":\" !" +
                        "\\n\\nDarkcrestMC uses the \",\"color\":\"#CDF8FF\"},{\"text\":\"ProjectKorra\",\"bold\":true,\"color\":\"#4DE5E8\"},{\"text\":\" plugin to add \",\"color\":\"#CDF8FF\"},{\"text\":\"Avatar: the Last Airbender\",\"bold\":true,\"color\":\"#CDF8FF\"},{\"text\":\" and \",\"color\":\"#CDF8FF\"},{\"text\":\"Legend of Korra\",\"bold\":true,\"color\":\"#CDF8FF\"},{\"text\":\" gameplay to Minecraft! On Darkcrest, once you rank up to \",\"color\":\"#CDF8FF\"},{\"text\":\"[\",\"color\":\"#62636E\"},{\"text\":\"Member\",\"color\":\"#18D674\"},{\"text\":\"]\",\"color\":\"#62636E\"},{\"text\":\", you can change your element at any time using \",\"color\":\"#CDF8FF\"},{\"text\":\"/bending choose [element]\",\"bold\":true,\"color\":\"#4DE5E8\"},{\"text\":\" !\",\"color\":\"#CDF8FF\"}" +
                        ",{\"text\":\"\\n\"},{\"text\":\" \\u0020 \\u0020 \\u0020 \\u0020 \",\"color\":\"blue\"},{\"text\":\"\\u2635 water \\u2635\",\"color\":\"blue\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/b d water\"},\"hoverEvent\":{\"action\":\"show_text\",\"contents\":[\"\",{\"text\":\"Click here to see a list of \",\"color\":\"yellow\"},{\"text\":\"waterbending\",\"bold\":\"true\",\"color\":\"blue\"},{\"text\":\" moves!\",\"color\":\"yellow\"}]}}," +
                        "{\"text\":\" \\u0020 \"},{\"text\":\"\\u2637 earth \\u2637\",\"color\":\"dark_green\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/b d earth\"},\"hoverEvent\":{\"action\":\"show_text\",\"contents\":[\"\",{\"text\":\"Click here to see a list of \",\"color\":\"yellow\"},{\"text\":\"earthbending\",\"bold\":\"true\",\"color\":\"dark_green\"},{\"text\":\" moves!\",\"color\":\"yellow\"}]}}," +
                        "{\"text\":\" \\u0020 \"},{\"text\":\"\\u2632 fire \\u2632\",\"color\":\"red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/b d fire\"},\"hoverEvent\":{\"action\":\"show_text\",\"contents\":[\"\",{\"text\":\"Click here to see a list of \",\"color\":\"yellow\"},{\"text\":\"firebending\",\"bold\":\"true\",\"color\":\"red\"},{\"text\":\" moves!\",\"color\":\"yellow\"}]}}," +
                        "{\"text\":\" \\u0020 \"},{\"text\":\"\\u2630 air \\u2630\",\"color\":\"#A6D5CC\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/b d air\"},\"hoverEvent\":{\"action\":\"show_text\",\"contents\":[\"\",{\"text\":\"Click here to see a list of \",\"color\":\"yellow\"},{\"text\":\"airbending\",\"bold\":\"true\",\"color\":\"#A6D5CC\"},{\"text\":\" moves!\",\"color\":\"yellow\"}]}},{\"text\":\" \\u0020 \"},{\"text\":\"\\u2633 chi \\u2633\",\"color\":\"gold\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/b ch chi\"},\"hoverEvent\":{\"action\":\"show_text\",\"contents\":[\"\",{\"text\":\"Click here to become a \",\"color\":\"yellow\"},{\"text\":\"chiblocker\",\"bold\":\"true\",\"color\":\"gold\"},{\"text\":\" and see a list of \",\"color\":\"yellow\"},{\"text\":\"chi\",\"bold\":\"true\",\"color\":\"gold\"},{\"text\":\" moves\",\"bold\":\"true\",\"color\":\"yellow\"},{\"text\":\"!\",\"color\":\"yellow\"}]}},{\"text\":\"\\n\"},{\"text\":\"<< Prev\",\"color\":\"gray\"},{\"text\":\" \\u25cf\",\"color\":\"#4DE5E8\"},{\"text\":\" \"},{\"text\":\"Next >>\",\"underlined\":true,\"color\":\"dark_aqua\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/bendinghelp 2\"}},{\"text\":\" \"}]");
//        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateColorCodes("&9\u2635 &2\u2637 &#4DE5E8Welcome to Bending on DarkcrestMC! &c\u2632 &#A6D5CC\u2630")));
//        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateColorCodes("")));
//        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', GeneralMethods.translateColorCodes("&#Brand new to bending? Want to get set up quick? Type")));
    }

    public void sendPage2(CommandSender sender) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "tellraw " + sender.getName() + " [\"\",{\"text\":\"On our server, you have access to tons of moves right away! Additionally, once you rank up to \",\"color\":\"#CDF8FF\"},{\"text\":\"[\",\"color\":\"#62636E\"},{\"text\":\"Merchant\",\"color\":\"#C018D6\"},{\"text\":\"]\",\"color\":\"#62636E\"},{\"text\":\", you unlock \",\"color\":\"#CDF8FF\"},{\"text\":\"Spirit\",\"bold\":true,\"color\":\"dark_aqua\"},{\"text\":\", \",\"color\":\"#CDF8FF\"},{\"text\":\"Lightning\",\"bold\":true,\"color\":\"aqua\"},{\"text\":\", \",\"color\":\"#CDF8FF\"},{\"text\":\"Metal\",\"bold\":true,\"color\":\"gray\"},{\"text\":\", and \",\"color\":\"#CDF8FF\"},{\"text\":\"Plant\",\"bold\":true,\"color\":\"dark_green\"},{\"text\":\" subelements!\\n\\nDarkcrestMC also offers \",\"color\":\"#CDF8FF\"},{\"text\":\"DarkSpirit\",\"bold\":true,\"color\":\"#5F5377\"},{\"text\":\" and \",\"color\":\"#CDF8FF\"},{\"text\":\"LightSpirit\",\"bold\":true,\"color\":\"#FCFFC8\"},{\"text\":\", extra movesets unlockable through donation or in-game play! And, for the ultimate bending experience, we offer \",\"color\":\"#CDF8FF\"},{\"text\":\"Avatar\",\"bold\":true,\"color\":\"dark_purple\"},{\"text\":\", which grants exclusive moves and the ability to bind moves from multiple elements at once!\\u2014also obtainable through donation or in-game.\",\"color\":\"#CDF8FF\"},{\"text\":\"\\n\"},{\"text\":\"/donate\",\"bold\":true,\"underlined\":true,\"color\":\"#4DE5E8\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/donate\"}},{\"text\":\" \\u2014 gives link to donation page\",\"color\":\"#CDF8FF\"},{\"text\":\"\\n\"},{\"text\":\"/ranks\",\"bold\":true,\"underlined\":true,\"color\":\"#4DE5E8\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/ranks\"}},{\"text\":\" \\u2014 gives in-game rankup info\",\"color\":\"#CDF8FF\"},{\"text\":\"\\n\"},{\"text\":\"<< Prev\",\"underlined\":true,\"color\":\"dark_aqua\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/bendinghelp\"}},{\"text\":\" \\u25cf \",\"color\":\"#4DE5E8\"},{\"text\":\"Next >>\",\"underlined\":true,\"color\":\"dark_aqua\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/bendinghelp 3\"}}]");
    }

    public void sendPage3(CommandSender sender) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "tellraw " + sender.getName() + " [\"\",{\"text\":\"Are you ready to bend? Practice and battle in the Arenas! Type\",\"color\":\"#CDF8FF\"},{\"text\":\" /warp Arenas\",\"bold\":true,\"color\":\"#4DE5E8\"},{\"text\":\" to check them out!\\n\\nJoin us for our regular bending tournaments as well! Win awards and status as you fight other players!\\n\\nDon't forget to check out the PvP leaderboards!\",\"color\":\"#CDF8FF\"},{\"text\":\"\\n\\n\"},{\"text\":\"<< Prev\",\"color\":\"dark_aqua\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/bendinghelp 2\"}},{\"text\":\" \\u25cf \",\"color\":\"#4DE5E8\"},{\"text\":\"Next >>\",\"color\":\"gray\"}]");
    }
}
