package com.Jacksonnn.DCCore.Events.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.Events.Event;
import com.Jacksonnn.DCCore.Events.EventGeneral;
import com.Jacksonnn.DCCore.Events.EventSubCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class EndEventCommand implements EventSubCommand {
    private DCCore plugin;

    public EndEventCommand(DCCore dccore) {
        plugin = dccore;
    }

    @Override
    public String getName() {
        return "endEvent";
    }

    @Override
    public List<String> getAliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("end");
        aliases.add("terminate");
        aliases.add("finish");
        aliases.add("kill");
        aliases.add("stop");
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/dcevents endEvent <event>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Events.CommandDescriptions.EndEventCommand");
    }

    //  /dcevents endEvent <event>
    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (sender.hasPermission("DCCore.EventHost.terminate")) {
            if (args.size() == 1) {
                Event event = EventGeneral.getEvent(args.get(0));

                if (event == null) {
                    sender.sendMessage(EventGeneral.eventPrefix + "There is no event with that name!");
                    return;
                }

                String eventName = event.getEventName();
                EventGeneral.events.remove(event);
                sender.sendMessage(EventGeneral.eventPrefix + "Successfully terminated the event, " + eventName);

            } else {
                sender.sendMessage(EventGeneral.eventPrefix + "/dcevents endEvent <event>");
            }
        }
    }
}
