package com.Jacksonnn.DCCore.Guides;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.jetbrains.annotations.NotNull;

public class DCGuideCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player)
            giveGuide((Player)commandSender);
        return true;
    }

    public static void giveGuide(Player player) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + player.getName() + " written_book{display:{Name:'[{\"text\":\"Welcome to \",\"italic\":false,\"color\":\"#a74482\"},{\"text\":\"D\",\"color\":\"#833995\",\"bold\":true},{\"text\":\"a\",\"color\":\"#793b98\",\"bold\":true},{\"text\":\"r\",\"color\":\"#703f9c\",\"bold\":true},{\"text\":\"k\",\"color\":\"#67429f\",\"bold\":true},{\"text\":\"c\",\"color\":\"#5d45a3\",\"bold\":true},{\"text\":\"r\",\"color\":\"#5448a6\",\"bold\":true},{\"text\":\"e\",\"color\":\"#4c4da9\",\"bold\":true},{\"text\":\"s\",\"color\":\"#4f5dad\",\"bold\":true},{\"text\":\"t!\",\"color\":\"#536eb1\",\"bold\":true}]',Lore:['{\"text\":\"Your guide to play!\"}']},title:\"\",author:\"DarkcrestMC\",pages:['[\"\",{\"text\":\" \\\\u0020 \\\\u0020 \\\\u0020 Welcome to \\\\n\"},{\"text\":\" \\\\u0020\\\\u2605 D\",\"bold\":true,\"color\":\"#833995\"},{\"text\":\"a\",\"bold\":true,\"color\":\"#793B98\"},{\"text\":\"r\",\"bold\":true,\"color\":\"#703F9C\"},{\"text\":\"k\",\"bold\":true,\"color\":\"#67429F\"},{\"text\":\"c\",\"bold\":true,\"color\":\"#5D45A3\"},{\"text\":\"r\",\"bold\":true,\"color\":\"#5448A6\"},{\"text\":\"e\",\"bold\":true,\"color\":\"#4C4DA9\"},{\"text\":\"s\",\"bold\":true,\"color\":\"#4F5DAD\"},{\"text\":\"t\",\"bold\":true,\"color\":\"#536EB1\"},{\"text\":\"! \\\\u2605\",\"color\":\"#536EB1\"},{\"text\":\"\\\\n\\\\nWe hope you have fun here! To get you started, this book contains some basic info, rules, and commands, as well as tips on how to further explore the world!\\\\n\\\\nTurn the page to start the fun!\",\"color\":\"reset\"}]','[\"\",{\"text\":\"Table of Contents\",\"bold\":true,\"color\":\"#A74482\"},{\"text\":\"\\\\n\\\\n\",\"color\":\"reset\"},{\"text\":\"* \\\\u0020\",\"clickEvent\":{\"action\":\"change_page\",\"value\":3}},{\"text\":\"Rules\",\"bold\":true,\"clickEvent\":{\"action\":\"change_page\",\"value\":3}},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"* \\\\u0020\",\"clickEvent\":{\"action\":\"change_page\",\"value\":6}},{\"text\":\"Ways to Play!\",\"bold\":true,\"clickEvent\":{\"action\":\"change_page\",\"value\":6}},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"* \\\\u0020\",\"clickEvent\":{\"action\":\"change_page\",\"value\":7}},{\"text\":\"Bending\",\"bold\":true,\"clickEvent\":{\"action\":\"change_page\",\"value\":7}},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"* \\\\u0020\",\"clickEvent\":{\"action\":\"change_page\",\"value\":8}},{\"text\":\"Towny\",\"bold\":true,\"clickEvent\":{\"action\":\"change_page\",\"value\":8}},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"* \\\\u0020\",\"clickEvent\":{\"action\":\"change_page\",\"value\":10}},{\"text\":\"Ranks\",\"bold\":true,\"clickEvent\":{\"action\":\"change_page\",\"value\":10}},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"* \\\\u0020\",\"clickEvent\":{\"action\":\"change_page\",\"value\":11}},{\"text\":\"Economy & /Jobs\",\"bold\":true,\"clickEvent\":{\"action\":\"change_page\",\"value\":11}},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"* \\\\u0020\",\"clickEvent\":{\"action\":\"change_page\",\"value\":13}},{\"text\":\"Useful Commands\",\"bold\":true,\"clickEvent\":{\"action\":\"change_page\",\"value\":13}},{\"text\":\"\\\\n\\\\n(Click the links to jump to that section!)\",\"color\":\"reset\"}]','[\"\",{\"text\":\" \\\\u0020 o\",\"bold\":true,\"obfuscated\":true,\"color\":\"#A74482\"},{\"text\":\" \",\"color\":\"#A74482\",\"bold\":true},{\"text\":\"The Rules\",\"bold\":true,\"underlined\":true,\"color\":\"#A74482\"},{\"text\":\" o\",\"color\":\"#A74482\",\"bold\":true,\"obfuscated\":true},{\"text\":\"\\\\nFirst, let\\'s check out the rules!\\\\n\\\\n \\\\u0020 \\\\u0020 \",\"color\":\"reset\"},{\"text\":\"Server Rules\",\"underlined\":true},{\"text\":\"\\\\n1. Greifing and raiding is not allowed!\\\\n2. No builds of a sexual or racial nature.\\\\n3. Do not AFK near mob or entity farms.\\\\n4. Do not scam other players.\",\"color\":\"reset\"}]','[\"\",{\"text\":\"5. No teleport killing.\\\\n\"},{\"text\":\"6. No player death traps.\",\"color\":\"#BLACK\"},{\"text\":\"\\\\n\",\"color\":\"reset\"},{\"text\":\"7. No large redstone clocks without staff approval.\\\\n \\\\u0020 \\\\u0020 \\\\u0020\",\"color\":\"#BLACK\"},{\"text\":\"Chat Rules\",\"underlined\":true,\"color\":\"#BLACK\"},{\"text\":\"\\\\n1. Do not spam.\\\\n2. Do not excessively swear.\\\\n3. Do not joke about suicide or self-harm.\",\"color\":\"#BLACK\"},{\"text\":\"\\\\n4. Do not use caps excessively.\",\"color\":\"reset\"}]','[\"\",{\"text\":\"5. Do not use slurs of sexual or racial nature\\\\n6. Do not impersonate staff.\\\\n7. Do not ask for staff positions; fill out an application on the \"},{\"text\":\"website!\",\"underlined\":true,\"color\":\"#50CBFA\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://darkcrestmc.net/forums/forums/staff-applications.17/\"}},{\"text\":\"\\\\n\\\\nThank you for following the rules!\\\\n~\",\"color\":\"reset\"},{\"text\":\"The Darkcrest Staff Team \",\"color\":\"#6E4E9E\"},{\"text\":\"<3\",\"bold\":true,\"color\":\"#6E4E9E\"}]','[\"\",{\"text\":\" \\\\u0020 \\\\u0020Ways to Play!\",\"bold\":true,\"color\":\"#A74482\"},{\"text\":\"\\\\n\\\\nWhat does Darkcrest have to offer?\\\\n\\\\n\",\"color\":\"reset\"},{\"text\":\"Darkcrest is the original \",\"color\":\"#BLACK\"},{\"text\":\"Bending\",\"bold\":true,\"color\":\"#6E4E9E\"},{\"text\":\" server! We also have \",\"color\":\"#BLACK\"},{\"text\":\"Towny\",\"bold\":true,\"color\":\"#6E4E9E\"},{\"text\":\", \",\"color\":\"#BLACK\"},{\"text\":\"Slimefun\",\"bold\":true,\"color\":\"#6E4E9E\"},{\"text\":\", a fun and interactive \",\"color\":\"#BLACK\"},{\"text\":\"Economy\",\"bold\":true,\"color\":\"#6E4E9E\"},{\"text\":\"\\\\u2014and more!\",\"color\":\"#BLACK\"}]','[\"\",{\"text\":\" \\\\u0020 \\\\u0020 \\\\u0020 Bending\",\"bold\":true,\"color\":\"#A74482\"},{\"text\":\"\\\\n\\\\nLearn the arts of \",\"color\":\"reset\"},{\"text\":\"water\",\"color\":\"blue\"},{\"text\":\", \",\"color\":\"reset\"},{\"text\":\"earth\",\"color\":\"dark_green\"},{\"text\":\", \",\"color\":\"reset\"},{\"text\":\"fire\",\"color\":\"#FF4B00\"},{\"text\":\", \",\"color\":\"reset\"},{\"text\":\"air\",\"color\":\"#89D5DD\"},{\"text\":\", and \",\"color\":\"reset\"},{\"text\":\"chi\",\"color\":\"gold\"},{\"text\":\"! Choose your abilities and change your element at any time!\\\\nCheck out the \",\"color\":\"reset\"},{\"text\":\"Bending Quickstart Guide\",\"bold\":true},{\"text\":\" Book\",\"bold\":true},{\"text\":\" (\",\"color\":\"reset\"},{\"text\":\"/bendingguide\",\"bold\":true},{\"text\":\") or type \",\"color\":\"reset\"},{\"text\":\"/bendinghelp\",\"bold\":true},{\"text\":\" to get started!\",\"color\":\"reset\"}]','[\"\",{\"text\":\" \\\\u0020 \\\\u0020 \\\\u0020 \\\\u0020Towny\",\"bold\":true,\"color\":\"#A74482\"},{\"text\":\"\\\\n\\\\n\",\"color\":\"reset\"},{\"text\":\"Towny\",\"bold\":true,\"color\":\"#6E4E9E\"},{\"text\":\" allows players to create and join \",\"color\":\"reset\"},{\"text\":\"Towns\",\"italic\":true},{\"text\":\" and \",\"color\":\"reset\"},{\"text\":\"Nations\",\"italic\":true},{\"text\":\" in order to work together, building and making money alongside other players! \\\\n\\\\nCheck out some of the town warps at spawn!\",\"color\":\"reset\"}]','[\"\",{\"text\":\"\\\\nAlso, type \"},{\"text\":\"/t list\",\"bold\":true},{\"text\":\" to see what towns are available! \\\\n\\\\n\",\"color\":\"reset\"},{\"text\":\"Type \",\"color\":\"#BLACK\"},{\"text\":\"/warp TownyInfo\",\"bold\":true,\"color\":\"#BLACK\"},{\"text\":\" for more information!\",\"color\":\"#BLACK\"}]','[\"\",{\"text\":\" \\\\u0020 \\\\u0020 \\\\u0020 \\\\u0020Ranks\",\"bold\":true,\"color\":\"#A74482\"},{\"text\":\"\\\\n\\\\nAs you play and earn money, you can rank up to unlock new benefits!\\\\n\\\\u2013Type \",\"color\":\"reset\"},{\"text\":\"/rankup\",\"bold\":true},{\"text\":\" to rank up or see your progress to the next rank!\\\\n\\\\u2013Type \",\"color\":\"reset\"},{\"text\":\"/warp RankInfo\",\"bold\":true},{\"text\":\" or \",\"color\":\"reset\"},{\"text\":\"/ranks\",\"bold\":true},{\"text\":\" to see what benefits you can get!\",\"color\":\"reset\"}]','[\"\",{\"text\":\"Economy and /Jobs\",\"bold\":true,\"color\":\"#A74482\"},{\"text\":\"\\\\nOn Darkcrest, the #1 way to get money is through jobs!\\\\n\\\\nType \",\"color\":\"reset\"},{\"text\":\"/jobs browse\",\"bold\":true},{\"text\":\" to see what jobs are available, and \",\"color\":\"reset\"},{\"text\":\"/jobs join (JobName)\",\"bold\":true},{\"text\":\" to add it.\\\\n\\\\nYou unlock more job slots as you rank up!\",\"color\":\"reset\"}]','[\"\",{\"text\":\"Darkcrest also has several options for buying and selling items!\\\\n\\\\u2013Type \"},{\"text\":\"/warp PlayerShops\",\"bold\":true},{\"text\":\" or \\\\n\",\"color\":\"reset\"},{\"text\":\"/auctionhouse\",\"bold\":true},{\"text\":\" to see what other players are selling!\\\\n\\\\u2013Check out the Admin Shop\\\\u2014 \",\"color\":\"reset\"},{\"text\":\"/shop\",\"bold\":true}]','[\"\",{\"text\":\" \\\\u0020Useful Commands\",\"bold\":true,\"color\":\"#A74482\"},{\"text\":\"\\\\n\\\\n\",\"color\":\"reset\"},{\"text\":\"/rankup\",\"bold\":true},{\"text\":\" \\\\u2014\\\\nAllows you to rank up.\\\\n\",\"color\":\"reset\"},{\"text\":\"/ranks\",\"bold\":true},{\"text\":\" \\\\u2014\\\\nShow server ranks.\\\\n\",\"color\":\"reset\"},{\"text\":\"/rules\",\"bold\":true},{\"text\":\" \\\\u2014\\\\nShows all rules.\\\\n\",\"color\":\"reset\"},{\"text\":\"/discord\",\"bold\":true},{\"text\":\" \\\\u2014\\\\nGet our discord link!\\\\n\",\"color\":\"reset\"},{\"text\":\"/website\",\"bold\":true},{\"text\":\" \\\\u2014\\\\n\",\"color\":\"reset\"},{\"text\":\"Website link!\",\"underlined\":true,\"color\":\"#50CBFA\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://darkcrestmc.net/\"}}]','[\"\",{\"text\":\"/bal\",\"bold\":true},{\"text\":\" \\\\u2014\\\\nCheck your balance.\\\\n\",\"color\":\"reset\"},{\"text\":\"/jobs\",\"bold\":true},{\"text\":\" \\\\u2014\\\\nSee /jobs commands.\\\\n\",\"color\":\"reset\"},{\"text\":\"/warps\",\"bold\":true},{\"text\":\" \\\\u2014\\\\nSee available warps.\\\\n\",\"color\":\"reset\"},{\"text\":\"/msg\",\"bold\":true},{\"text\":\" \\\\u2014\\\\nSend a direct message.\\\\n\",\"color\":\"reset\"},{\"text\":\"/tpa\",\"bold\":true},{\"text\":\" \\\\u2014\\\\nSend teleport request.\\\\n\",\"color\":\"reset\"},{\"text\":\"/helpop\",\"bold\":true},{\"text\":\" \\\\u2014\\\\nAsk staff for help.\",\"color\":\"reset\"}]','[\"\",{\"text\":\"/donate\",\"bold\":true},{\"text\":\" \\\\u2014\\\\nDonate to Darkcrest!\\\\n\",\"color\":\"reset\"},{\"text\":\"/vote\",\"bold\":true},{\"text\":\" \\\\u2014\\\\nVote for Darkcrest and get rewards!\\\\n\",\"color\":\"reset\"},{\"text\":\"/suggest \",\"bold\":true},{\"text\":\"\\\\u2014\\\\nMake a server suggestion!\",\"color\":\"reset\"}]']} 1");
    }
}
