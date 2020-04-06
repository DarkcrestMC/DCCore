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

public class ClearCommand implements ReportSubCommand {
    private DCCore plugin;
    private PlayerDisciplineManager pdm;

    public ClearCommand(DCCore plugin) {
        this.plugin = plugin;
        pdm = plugin.getPDM();
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("deleteall");
        aliases.add("clearall");
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/reports clear <type>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Reports.CommandDescriptions.Clear");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (args.size() >= 1) {
            String requestedType = args.get(0);

            if (requestedType.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.TODO.getShorthand())) {
                ArrayList<ToDoReport> toDoReports = new ArrayList<>();
                toDoReports.addAll(pdm.getReportManager().getTodoReports());

                for (ToDoReport todo : toDoReports) {
                    try {
                        pdm.deleteReport(todo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                sender.sendMessage(pdm.getReportManager().reportsPrefix + "Successfully deleted all todo reports.");
            } else if (requestedType.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.PLAYER.getShorthand())) {
                ArrayList<PlayerReport> playerReports = new ArrayList<>();
                playerReports.addAll(pdm.getReportManager().getPlayerReports());

                for (PlayerReport pReport : playerReports) {
                    try {
                        pdm.deleteReport(pReport);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                sender.sendMessage(pdm.getReportManager().reportsPrefix + "Successfully deleted all player reports.");
            } else if (requestedType.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.STAFF.getShorthand())) {
                ArrayList<StaffReport> staffReports = new ArrayList<>();
                staffReports.addAll(pdm.getReportManager().getStaffReports());

                for (StaffReport sReport : staffReports) {
                    try {
                        pdm.deleteReport(sReport);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                sender.sendMessage(pdm.getReportManager().reportsPrefix + "Successfully deleted all staff reports.");
            } else if (requestedType.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.BUG.getShorthand())) {
                ArrayList<BugReport> bugReports = new ArrayList<>();

                bugReports.addAll(pdm.getReportManager().getBugReports());

                for (BugReport bReport : bugReports) {
                    try {
                        pdm.deleteReport(bReport);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                sender.sendMessage(pdm.getReportManager().reportsPrefix + "Successfully deleted all bug reports.");
            } else {
                pdm.getReportManager().getHelp(sender);
            }
        } else {
            pdm.getReportManager().getHelp(sender);
        }
    }
}
