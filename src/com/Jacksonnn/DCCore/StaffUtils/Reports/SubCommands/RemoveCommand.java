package com.Jacksonnn.DCCore.StaffUtils.Reports.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportGeneral;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportSubCommand;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.BugReport;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.PlayerReport;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.StaffReport;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.ToDoReport;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class RemoveCommand implements ReportSubCommand {

    private DCCore plugin;
    private PlayerDisciplineManager pdm;

    public RemoveCommand(DCCore dccore) {
        plugin = dccore;
        pdm = plugin.getPDM();
    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("delete");
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/reports delete <type> <id>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Reports.CommandDescriptions.Remove");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        String requestedType = args.get(0);
        int id = Integer.valueOf(args.get(1));

        if (requestedType.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.TODO.getShorthand())) {
            ToDoReport report = pdm.getReportManager().getToDoReport(id);
            try {
                pdm.deleteReport(report);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestedType.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.PLAYER.getShorthand())) {
            PlayerReport report = pdm.getReportManager().getPlayerReport(id);
            try {
                pdm.deleteReport(report);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestedType.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.STAFF.getShorthand())) {
            StaffReport report = pdm.getReportManager().getStaffReport(id);
            try {
                pdm.deleteReport(report);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestedType.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.BUG.getShorthand())) {
            BugReport report = pdm.getReportManager().getBugReport(id);
            try {
                pdm.deleteReport(report);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            pdm.getReportManager().getHelp(sender);
        }
    }
}
