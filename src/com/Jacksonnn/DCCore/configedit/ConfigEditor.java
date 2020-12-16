package com.Jacksonnn.DCCore.configedit;

import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.command.PKCommand;
import com.projectkorra.projectkorra.configuration.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ConfigEditor extends PKCommand {

    public ConfigEditor() {
        super("config", "/bending config <value>", "stuffs", new String[] { "config", "conf", "con" });

    }

    @Override
    public void execute(CommandSender sender, List<String> args) {

        if (!isPlayer(sender) || !correctLength(sender, args.size(), 0, 3)) {

            return;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("bendingessentials.configeditor")) {
                player.sendMessage(ChatColor.RED + "You do not have permission to use: " + ChatColor.GOLD
                        + "Bending Essentials Config Editor");
                return;
            }

            if (args.size() == 0) {

                player.sendMessage(ChatColor.GOLD
                        + "This allows you to live edit PK's config and make changes immediately. Please be aware that this feature does not support addons.");
                player.sendMessage("\n" + ChatColor.RED + "To find out how to properly use this command, type "
                        + ChatColor.GOLD + "/b config help");
                return;

            }

            if (args.size() == 1) {

                if (args.get(0).equalsIgnoreCase("help")) {

                    player.sendMessage(ChatColor.GOLD
                            + "The correct way to use the PK Config Editor is to define a move, the variable you would like to change and then the value. For example:");
                    player.sendMessage(ChatColor.WHITE + "- " + ChatColor.RED + "/b config FireBlast Damage 5000");
                    player.sendMessage(ChatColor.RED + "For a list of variables, type: " + ChatColor.GOLD + "/b config help variables");
                    return;
                } else {
                    player.sendMessage(ChatColor.RED + "Incorrect usage. Type " + ChatColor.GOLD + "/b config help "
                            + ChatColor.RED + "for information on how to properly use this command.");
                    return;
                }
            }

            if (args.size() == 2) {
                if (args.get(0).equalsIgnoreCase("help") && (args.get(1).equals("variables"))) {
                    player.sendMessage(ChatColor.WHITE + "- " + ChatColor.RED + "Enabled");
                    player.sendMessage(ChatColor.WHITE + "- " + ChatColor.RED + "Damage");
                    player.sendMessage(ChatColor.WHITE + "- " + ChatColor.RED + "Knockback");
                    player.sendMessage(ChatColor.WHITE + "- " + ChatColor.RED + "Cooldown");
                    player.sendMessage(ChatColor.WHITE + "- " + ChatColor.RED + "Range");
                    player.sendMessage(ChatColor.WHITE + "- " + ChatColor.RED + "ChargeTime");
                    player.sendMessage(ChatColor.GOLD + "This is not a full list of variables, just the most common ones. Each ability has its own unique variables and all of those can be accessed.");


                    return;
                } else {
                    player.sendMessage(ChatColor.RED + "Incorrect usage. Type " + ChatColor.GOLD + "/b config help "
                            + ChatColor.RED + "for information on how to properly use this command.");
                    return;
                }
            }

            if (args.size() == 3) {

                String move = args.get(0);
                if (CoreAbility.getAbility(move) == null) {
                    player.sendMessage(ChatColor.RED + move + ChatColor.GOLD + " is not a valid ability!");
                    return;
                }

                // Gets variables from arguments
                String movename = CoreAbility.getAbility(move).getName();
                String variable = args.get(1).toUpperCase().charAt(0) + args.get(1).substring(1);
                String value = args.get(2);
                CoreAbility ability = CoreAbility.getAbility(movename);
                String element = ability.getElement().getName();


                //		event.setMessage(("" + msg.charAt(0)).toUpperCase() + msg.substring(1));

                if (!(ConfigManager.defaultConfig.get()
                        .contains("Abilities." + element + "." + movename + "." + variable))) {
                    player.sendMessage(ChatColor.GOLD + "That config path could not be found:");
                    player.sendMessage(ChatColor.RED + "Abilities." + element + "." + movename + "." + variable);
                    return;

                }

                // Checks if the value is an int
                if (isInt(args.get(2))) {

                    int intvalue = Integer.parseInt(args.get(2));

                    ConfigManager.defaultConfig.get().set("Abilities." + element + "." + movename + "." + variable,
                            intvalue);

                    saveConfig();

                    player.sendMessage("- " + ability.getElement().getColor() + move);
                    player.sendMessage(ChatColor.GOLD + variable + ChatColor.WHITE + ":");
                    player.sendMessage(ChatColor.GREEN + value);

                    return;
                }
                // Checks if the value is a double
                else if (isDouble(args.get(2))) {
                    double doublevalue = Double.parseDouble(args.get(2));
                    ConfigManager.defaultConfig.get().set("Abilities." + element + "." + movename + "." + variable,
                            doublevalue);

                    saveConfig();
                    player.sendMessage("- " + ability.getElement().getColor() + move);
                    player.sendMessage(ChatColor.GOLD + variable + ChatColor.WHITE + ":");
                    player.sendMessage(ChatColor.GREEN + value);
                    return;
                }

                // Checks if the value is boolean=true
                else if (value.equalsIgnoreCase("true")) {
                    Boolean valuetrue = true;
                    ConfigManager.defaultConfig.get().set("Abilities." + element + "." + movename + "." + variable,
                            valuetrue);

                    saveConfig();
                    player.sendMessage("- " + ability.getElement().getColor() + move);
                    player.sendMessage(ChatColor.GOLD + variable + ChatColor.WHITE + ":");
                    player.sendMessage(ChatColor.GREEN + value);
                    return;

                }
                // Checks if the value is boolean=false
                else if (value.equalsIgnoreCase("false")) {
                    Boolean valuefalse = false;
                    ConfigManager.defaultConfig.get().set("Abilities." + element + "." + movename + "." + variable,
                            valuefalse);

                    saveConfig();
                    player.sendMessage("- " + ability.getElement().getColor() + move);
                    player.sendMessage(ChatColor.GOLD + variable + ChatColor.WHITE + ":");
                    player.sendMessage(ChatColor.RED + value);
                    return;

                } else {
                    player.sendMessage(ChatColor.RED + "Incorrect usage. Type " + ChatColor.GOLD + "/b config help "
                            + ChatColor.RED + "for information on how to properly use this command.");

                }

            }
        }
    }

    private void saveConfig() {

        ConfigManager.defaultConfig.save();
        ConfigManager.defaultConfig.reload();

    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
