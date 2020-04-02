package com.Jacksonnn.DCCore.Events.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.Events.EventGeneral;
import com.Jacksonnn.DCCore.Events.EventSubCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements EventSubCommand {
    private DCCore plugin;

    public HelpCommand(DCCore dccore) {
        plugin = dccore;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public List<String> getAliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("?");
        aliases.add("wtf");
        aliases.add("whatthefuck");
        aliases.add("??");
        aliases.add("???");
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/dcevents help";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Events.CommandDescriptions.HelpCommand");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        EventGeneral.getHelp(sender);
    }
}
