package com.Jacksonnn.DCCore.StaffUtils;

import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.GeneralMethods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StaffSpawnCommand implements CommandExecutor {

    private DCCore plugin;

    public StaffSpawnCommand(DCCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage(GeneralMethods.prefix + "Staff Spawn Command Usage: /staffspawn <rank> [player]");
        } else {
            String rank = args[0];
            ArrayList<String> messages = getRankDesc(rank);

            if (args.length >= 2) {
                Player player = Bukkit.getPlayer(args[1]);

                int i = 20;

                assert messages != null;
                for (String message : messages) {
                    BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                    scheduler.scheduleSyncDelayedTask(this.plugin, () -> player.sendMessage(message), /*20 ticks = 1 second*/ i);

                    i+=40;
                }

            } else {
                int i = 20;

                assert messages != null;
                for (String message : messages) {
                    BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                    scheduler.scheduleSyncDelayedTask(this.plugin, () -> sender.sendMessage(message), /*20 ticks = 1 second*/ i);

                    i+=40;
                }
            }

        }
        return true;
    }

    private ArrayList<String> getRankDesc(String rank) {
        ArrayList<String> messages = new ArrayList<>();

        switch (rank.toUpperCase()) {
            case "ANCIENT":
                messages.add(ChatColor.translateAlternateColorCodes('&', "&b&l-=(&f&lAncient&b&l)=-\n"));
                messages.add(ChatColor.translateAlternateColorCodes('&', "&b(MSG FROM: &f&osehunpark&b)&f Thank you for your time on DarkcrestMC as a Staff Member. You have helped us well. It is now time to join me and be retired from the staff team, letting the next generation take flight.\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "Also, we may be retired, but we are still here to help support the current staff, and we can still play and interact with players. Remember that even though we aren't technically staff, we still have a fancy rank so we still need to try to set an example :)\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "Thank you for everything, see you on the other side...\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "&o-sehunpark"));
                messages.add(ChatColor.translateAlternateColorCodes('&', "&b&l-=(&f&l+&b&l)=-"));
                return messages;
            case "ARTIST":
                messages.add(ChatColor.translateAlternateColorCodes('&', "&b&l-=(&c&lA&6&lr&e&lt&a&li&9&ls&d&lt&b&l)=-\n"));
                messages.add(ChatColor.translateAlternateColorCodes('&', "&b(MSG FROM: &oTheDoctor234&b)&f I am glad you finally made it as an artist-- I am proud. The artist team is a team that is near and dear to my heart. I was once a Co-Owner on this server and also ran the team for many years.\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "Your sole purpose is to build for the server. Of course, helping out players and such is always important for anyone who represents the staff team of DC; however, do not mix up moderation/administration staff with an artist. Their main job is to work on the server's player experience and run it. Your main job is to follow instructions from the Head-Artist and work together to make the server beautiful.\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "Anyone can build, but not everyone can build well. It is a privilege to be on this team, as I know you already are aware.\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "&o-TheDoctor234"));
                messages.add(ChatColor.translateAlternateColorCodes('&', "&b&l-=(&f&l+&b&l)=-"));
            case "JMOD":
                messages.add(ChatColor.translateAlternateColorCodes('&', "&e&l-=(&9&lJMod&e&l)=-\n"));
                messages.add(ChatColor.translateAlternateColorCodes('&', "&e(MSG FROM: &9&o115125&e)&f Congratulations on becoming a Junior Moderator! It's a great achievement and I, 115125, would personally like to welcome you into the staff team. This team works together very hard and we treat each other like a family. Hopefully, you can find that here too.\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "I never passed my trial phase, but I will explain how the process works. You get approved and then you will join the staff section of the discord. Once there, someone will designate someone to be your mentor, who will help you learn how to use your rank and help you through situations.\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "This is a learning opportunity and you will mess up. That's okay. I hope you pass, good luck on your new adventure on DarkcrestMC!\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "-Andy (115125\n"));
                messages.add(ChatColor.translateAlternateColorCodes('&', "&e&l-=(&9&l+&e&l)=-"));
                return messages;
            case "MODERATOR":
                messages.add(ChatColor.translateAlternateColorCodes('&', "&a&l-=(&2&lModerator&a&l)=-\n"));
                messages.add(ChatColor.translateAlternateColorCodes('&', "&2(MSG FROM &a&oRach_D&2)&f Congrats on becoming a Moderator! Mods are in charge of handling chats and helping with most broken rules. Most of the time, we only have to deal with chat scenarios, but it's important that we can help with everything.\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "Now that you are out of your trial phase, you have a lot more commands, and you aren't constantly being tested. As a moderator, you now have access to all essentials commands, towny admin, bending admin, worldguard, and DCCore, and many more plugins and add-ons.\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "Being a mod sounds like a lot to learn, but it's not that much different than being a JMod overall. You've got this, and mentoring helps keep you refereshed as well!\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "&o-Rach_D\n"));
                messages.add(ChatColor.translateAlternateColorCodes('&', "&a&l-=(&2&l+&a&l)=-"));
                return messages;
            case "MANAGER":
                messages.add(ChatColor.translateAlternateColorCodes('&', "&4&l-=(&c&lManager&4&l)=-\n"));
                messages.add(ChatColor.translateAlternateColorCodes('&', "&4(MSG FROM: &c&obravofreak&4)&f Welcome to management, it's super easy-- almost. Congrats on your new rank, I remember being there many moons ago haha. I was a Head-Admin on Darkcrest for many years, and I have seen many people promoted to the rank you have now.\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "The main thing that admins have is OP and PermissionsEX permissions. This allows for you to solve many things that normal staff members cannot when it involves permissions or rank, especially when handling abuse. This rank also grants you ability to handle some situations between staff members.\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "With the authority that is carried with this rank, it is important that you are the best of the best and up to date. Don't abuse it!\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "&o-bravofreak\n"));
                messages.add(ChatColor.translateAlternateColorCodes('&', "&4&l-=(&c&l+&4&l)=-"));
                return messages;
            case "CO-OWNER":
                messages.add(ChatColor.translateAlternateColorCodes('&', "&7&l-=(&b&lCo&f&l-&d&lOwner&7&l)=-\n"));
                messages.add(ChatColor.translateAlternateColorCodes('&', "&7(MSG FROM: &f&ozelban&7)&f Congratulations on hitting the highest rank that is obtainable as a staff member. This is something that should hopefully be taken with pride because of your hard work and dedication.\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "Darkcrest is always changing. As part of this change, you now have one of the biggest voices. Just because you are very important on the server, means you have to be as important to people who aren't as high a rank. Being in this position, you are able to set the status quo and rework the server from the inside; a privilege which not many get.\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "The main new feature to your rank is the Console. Currently, it can be accessed at darkcrestmc.wisp.gg. If you don't already have one, you will have an account created for you where you can manipulate files and manage the server.\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "Just as you have a voice, so do others, so please make sure the HOS discuss anything you add or change before you do so. Good luck with this new responsibility!\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "&o-zelban\n"));
                messages.add(ChatColor.translateAlternateColorCodes('&', "&7&l-=(&b&l&m &f&l+&d&l&m &7&l)=-"));
                return messages;
            case "OWNER":
                messages.add(ChatColor.translateAlternateColorCodes('&', "&f&l-=(&d&lOwner&f&l)=-\n"));
                messages.add(ChatColor.translateAlternateColorCodes('&', "&7(MSG FROM &d&osehunpark&7)\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "Darkcrest is my pride and joy. Don't fuck it up whores.\n"));
                messages.add("");
                messages.add(ChatColor.translateAlternateColorCodes('&', "&o-sehunpark\n"));
                messages.add(ChatColor.translateAlternateColorCodes('&', "&f&l-=(&d&l+&f&l)=-"));
                return messages;
            default:
                return null;
        }
    }
}
