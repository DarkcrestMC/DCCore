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
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class ListCommand implements ReportSubCommand {

    private DCCore plugin;
    private PlayerDisciplineManager pdm;

    public ListCommand(DCCore dccore) {
        plugin = dccore;
        pdm = plugin.getPDM();
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<>();
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/reports list <type>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Reports.CommandDescriptions.List");
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (args.size() >= 1) {
            String requestedType = args.get(0);

            if (requestedType.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.TODO.getShorthand())) {
                ArrayList<ToDoReport> toDoReports = pdm.getReportManager().getTodoReports();

                if (toDoReports.size() == 0) {
                    sender.sendMessage(pdm.getReportManager().reportsPrefix + "No todo reports.");
                    return;
                }

                sender.sendMessage(pdm.getReportManager().reportsPrefix + ReportGeneral.REPORT_TYPE.TODO.getGeneralUsage() + "s:");

                for (ToDoReport todo : toDoReports) {
                    sender.sendMessage("(ID: " +
                            todo.getID() + ") " + ChatColor.YELLOW +
                            todo.getMessage() + ChatColor.DARK_RED + " -" +
                            Bukkit.getPlayer(todo.getStaffMember()).getName());
                }

            } else if (requestedType.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.PLAYER.getShorthand())) {
                ArrayList<PlayerReport> playerReports = pdm.getReportManager().getPlayerReports();

                if (playerReports.size() == 0) {
                    sender.sendMessage(pdm.getReportManager().reportsPrefix + "No player reports.");
                    return;
                }

                sender.sendMessage(pdm.getReportManager().reportsPrefix + ReportGeneral.REPORT_TYPE.PLAYER.getGeneralUsage() + "s:");

                for (PlayerReport pReport : playerReports) {
                    sender.sendMessage("(ID: " +
                            Bukkit.getPlayer(pReport.getPlayer()).getName() + "-" +
                            (pReport.isResolved() ? "R" : "NR") +
                            pReport.getID() + ") " + ChatColor.YELLOW +
                            pReport.getMessage() + ChatColor.DARK_RED + " -" +
                            Bukkit.getPlayer(pReport.getStaffMember()).getName());
                }

                sender.sendMessage(ChatColor.GRAY + "R=Resolved; NR=Not Resolved");
            } else if (requestedType.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.STAFF.getShorthand())) {
                ArrayList<StaffReport> staffReports = pdm.getReportManager().getStaffReports();

                if (staffReports.size() == 0) {
                    sender.sendMessage(pdm.getReportManager().reportsPrefix + "No staff reports.");
                    return;
                }

                sender.sendMessage(pdm.getReportManager().reportsPrefix + ReportGeneral.REPORT_TYPE.STAFF.getGeneralUsage() + "s:");

                for (StaffReport sReport : staffReports) {
                    sender.sendMessage("(ID: " +
                            Bukkit.getPlayer(sReport.getPlayer()).getName() + "-" +
                            (sReport.isResolved() ? "R" : "NR") +
                            sReport.getID() + ") " + ChatColor.YELLOW +
                            sReport.getMessage() + ChatColor.DARK_RED + " -" +
                            Bukkit.getPlayer(sReport.getStaffMember()).getName());
                }

                sender.sendMessage(ChatColor.GRAY + "R=Resolved; NR=Not Resolved");
            } else if (requestedType.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.BUG.getShorthand())) {
                ArrayList<BugReport> bugReports = pdm.getReportManager().getBugReports();

                if (bugReports.size() == 0) {
                    sender.sendMessage(pdm.getReportManager().reportsPrefix + "No bug reports.");
                    return;
                }

                sender.sendMessage(pdm.getReportManager().reportsPrefix + ReportGeneral.REPORT_TYPE.BUG.getGeneralUsage() + "s:");

                for (BugReport bReport : bugReports) {
                    sender.sendMessage("(ID: " +
                            bReport.getBugType() + "-" +
                            (bReport.isTested() ? "T" : "NT") +
                            bReport.getID() + ") " + ChatColor.YELLOW +
                            bReport.getMessage() + ChatColor.DARK_RED + " -" +
                            Bukkit.getPlayer(bReport.getStaffMember()).getName());
                }

                sender.sendMessage(ChatColor.GRAY + "T=Tested; NT=Not Tested");
            } else {
                pdm.getReportManager().getHelp(sender);
            }
        } else {
            pdm.getReportManager().getHelp(sender);
        }
    }
}
