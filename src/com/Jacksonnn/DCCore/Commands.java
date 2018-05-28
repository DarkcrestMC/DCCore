package com.Jacksonnn.DCCore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.Jacksonnn.DCCore.ChatSensor.ChatListener;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String player = sender.getName();
        //dccore chat toggle
        //command arg1 arg2

        if (args.length == 0) {
            sender.sendMessage(GeneralMethods.prefix + "DCCore Help:");
            sender.sendMessage(ChatColor.GOLD + "/dccore chat");
            return true;

        } else if (args.length == 3 && args[1].equalsIgnoreCase("chat")) {
            if (args[2].equalsIgnoreCase("toggle")) {

                if (sender.hasPermission("DCCore.chat.toggle")) {
                    ChatListener.chatEnabled = !ChatListener.chatEnabled;
                    Bukkit.broadcastMessage(ChatListener.chatEnabled ? GeneralMethods.serverPrefix + "Chat has been unmuted by " + player + "." : GeneralMethods.serverPrefix + "Chat has been muted by " + player + ".");
                    sender.sendMessage(ChatListener.chatEnabled ? GeneralMethods.prefix + GeneralMethods.successColor + "Unmuted the chat." : GeneralMethods.prefix + GeneralMethods.successColor + "Muted the chat.");
                    return true;
                }
            } else {
                    sender.sendMessage(GeneralMethods.prefix + GeneralMethods.errorColor + "To use this command, /dccore chat toggle.");
                    return false;
            }
        }
        return true;
    }
}

/*
[ERROR] null

org.bukkit.command.CommandException: Unhandled exception executing command 'dc' in plugin DCCore vBETA v1.0

at org.bukkit.command.PluginCommand.execute(PluginCommand.java:46) ~[spigot-1.12.1.jar:git-Spigot-da42974-8f47214]

at org.bukkit.command.SimpleCommandMap.dispatch(SimpleCommandMap.java:141) ~[spigot-1.12.1.jar:git-Spigot-da42974-8f47214]

at org.bukkit.craftbukkit.v1_12_R1.CraftServer.dispatchCommand(CraftServer.java:651) ~[spigot-1.12.1.jar:git-Spigot-da42974-8f47214]

at net.minecraft.server.v1_12_R1.PlayerConnection.handleCommand(PlayerConnection.java:1386) [spigot-1.12.1.jar:git-Spigot-da42974-8f47214]

at net.minecraft.server.v1_12_R1.PlayerConnection.a(PlayerConnection.java:1221) [spigot-1.12.1.jar:git-Spigot-da42974-8f47214]

at net.minecraft.server.v1_12_R1.PacketPlayInChat.a(PacketPlayInChat.java:45) [spigot-1.12.1.jar:git-Spigot-da42974-8f47214]

at net.minecraft.server.v1_12_R1.PacketPlayInChat.a(PacketPlayInChat.java:1) [spigot-1.12.1.jar:git-Spigot-da42974-8f47214]

at net.minecraft.server.v1_12_R1.PlayerConnectionUtils$1.run(SourceFile:13) [spigot-1.12.1.jar:git-Spigot-da42974-8f47214]

at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511) [?:1.8.0_172]

at java.util.concurrent.FutureTask.run(FutureTask.java:266) [?:1.8.0_172]

at net.minecraft.server.v1_12_R1.SystemUtils.a(SourceFile:46) [spigot-1.12.1.jar:git-Spigot-da42974-8f47214]

at net.minecraft.server.v1_12_R1.MinecraftServer.D(MinecraftServer.java:748) [spigot-1.12.1.jar:git-Spigot-da42974-8f47214]

at net.minecraft.server.v1_12_R1.DedicatedServer.D(DedicatedServer.java:406) [spigot-1.12.1.jar:git-Spigot-da42974-8f47214]

at net.minecraft.server.v1_12_R1.MinecraftServer.C(MinecraftServer.java:679) [spigot-1.12.1.jar:git-Spigot-da42974-8f47214]

at net.minecraft.server.v1_12_R1.MinecraftServer.run(MinecraftServer.java:577) [spigot-1.12.1.jar:git-Spigot-da42974-8f47214]

at java.lang.Thread.run(Thread.java:748) [?:1.8.0_172]

Caused by: java.lang.ArrayIndexOutOfBoundsException: 1

at com.Jacksonnn.DCCore.Commands.onCommand(Commands.java:19) ~[?:?]

at org.bukkit.command.PluginCommand.execute(PluginCommand.java:44) ~[spigot-1.12.1.jar:git-Spigot-da42974-8f47214]

... 15 more
 */