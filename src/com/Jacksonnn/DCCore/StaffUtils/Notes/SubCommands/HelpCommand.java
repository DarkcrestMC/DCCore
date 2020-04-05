package com.Jacksonnn.DCCore.StaffUtils.Notes.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.StaffUtils.Notes.NotesSubCommand;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements NotesSubCommand {
    private DCCore plugin;
    private PlayerDisciplineManager pdm;

    public HelpCommand(DCCore dccore) {
        plugin = dccore;
        pdm = plugin.getPDM();
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
        return "/notes help";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Notes.CommandDescriptions.Help");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        pdm.getNoteManager().getHelp(sender);
    }
}
