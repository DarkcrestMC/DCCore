package com.Jacksonnn.DCCore.StaffUtils.Reports.SubCommands;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.DCPlayer;
import com.Jacksonnn.DCCore.GeneralMethods;
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
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AddCommand implements ReportSubCommand {

    private DCCore plugin;
    private PlayerDisciplineManager pdm;

    public AddCommand(DCCore dccore) {
        plugin = dccore;
        pdm = plugin.getPDM();
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("create");
        aliases.add("new");
        return aliases;
    }

    @Override
    public String getProperUse() {
        return "/reports add <type>";
    }

    @Override
    public String getDescription() {
        return ConfigManager.langConfig.get().getString("Reports.CommandDescriptions.Add");
    }

    /*
        /reports add <type>
        /reports add todo <message>
        /reports add player <player> <true/false> <message>
        /reports add staff <staff> <true/false> <message>
        /reports add bug <bugType> <tested> <message>
     */
    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (args.size() >= 1) {
            if (sender instanceof Player) {
                String reportRequestType = args.get(0);
                args.remove(reportRequestType);

                if (reportRequestType.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.TODO.getShorthand())) {
                    if (args.size() >= 1) {
                        String message = String.join(" ", args);

                        ToDoReport report = new ToDoReport(message, ((Player) sender).getPlayer().getUniqueId(), pdm);
                        report.sendToDiscord();
                        sender.sendMessage(pdm.getReportManager().reportsPrefix + "Successfully created new " + report.getType().getShorthand() + " report (ID: " +
                                report.getID() + ") " + ChatColor.YELLOW +
                                report.getMessage() + ChatColor.DARK_RED + " -" +
                                Bukkit.getPlayer(report.getStaffMember()).getName());
                    } else {
                        sender.sendMessage(pdm.getReportManager().reportsPrefix + "Error! Please do /reports add todo <message>.");
                    }
                } else if (reportRequestType.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.PLAYER.getShorthand())) {
                    if (args.size() >= 3) {
                        DCPlayer player = GeneralMethods.getDCPlayer(args.get(0));
                        Player staffMember = ((Player) sender).getPlayer();

                        if (player == null) {
                            sender.sendMessage(pdm.getReportManager().reportsPrefix + "Error! That user does not exist...");
                        }

                        args.remove(0);

                        boolean isResolved = Boolean.parseBoolean(args.get(0).toLowerCase());
                        args.remove(0);

                        String message = String.join(" ", args);

                        PlayerReport report = new PlayerReport(message, player, staffMember.getUniqueId(), isResolved, pdm);
                        report.sendToDiscord();
                        sender.sendMessage(pdm.getReportManager().reportsPrefix + "Successfully created new " + report.getType().getShorthand() + " report (ID: " +
                                GeneralMethods.getDCPlayer(report.getPlayer()).getName() + "-" +
                                (report.isResolved() ? "R" : "NR") +
                                report.getID() + ") " + ChatColor.YELLOW +
                                report.getMessage() + ChatColor.DARK_RED + " -" +
                                GeneralMethods.getDCPlayer(report.getStaffMember()).getName());
                    } else {
                        sender.sendMessage(pdm.getReportManager().reportsPrefix + "Error! Please do /reports add player <player> <isResolved(true/false)> <message>");
                    }
                } else if (reportRequestType.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.STAFF.getShorthand())) {
                    if (args.size() >= 3) {
                        DCPlayer player = GeneralMethods.getDCPlayer(args.get(0));
                        Player staffMember = ((Player) sender).getPlayer();

                        if (player == null) {
                            sender.sendMessage(pdm.getReportManager().reportsPrefix + "Error! That user does not exist...");
                        }

                        args.remove(0);

                        boolean isResolved = Boolean.parseBoolean(args.get(0).toLowerCase());
                        args.remove(0);

                        String message = String.join(" ", args);

                        StaffReport report = new StaffReport(message, player, staffMember.getUniqueId(), isResolved, pdm);
                        report.sendToDiscord();
                        sender.sendMessage(pdm.getReportManager().reportsPrefix + "Successfully created new " + report.getType().getShorthand() + " report (ID: " +
                                GeneralMethods.getDCPlayer(report.getPlayer()).getName() + "-" +
                                (report.isResolved() ? "R" : "NR") +
                                report.getID() + ") " + ChatColor.YELLOW +
                                report.getMessage() + ChatColor.DARK_RED + " -" +
                                GeneralMethods.getDCPlayer(report.getStaffMember()).getName());
                    } else {
                        sender.sendMessage(pdm.getReportManager().reportsPrefix + "Error! Please do /reports add staff <staffMember> <isResolved(true/false)> <message>");
                    }
                } else if (reportRequestType.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.BUG.getShorthand())) {
                    if (args.size() >= 3) {
                        String bugType = args.get(0);
                        args.remove(0);

                        boolean isTested = Boolean.parseBoolean(args.get(0).toLowerCase());
                        args.remove(0);

                        String message = String.join(" ", args);

                        BugReport report = new BugReport(message, bugType, ((Player) sender).getPlayer().getUniqueId(), isTested, pdm);
                        report.sendToDiscord();
                        sender.sendMessage(pdm.getReportManager().reportsPrefix + "Successfully created new " + report.getType().getShorthand() + " report (ID: " +
                                report.getBugType() + "-" +
                                (report.isTested() ? "T" : "NT") +
                                report.getID() + ") " + ChatColor.YELLOW +
                                report.getMessage() + ChatColor.DARK_RED + " -" +
                                Bukkit.getPlayer(report.getStaffMember()).getName());
                    } else {
                        sender.sendMessage(pdm.getReportManager().reportsPrefix + "Error! Please do /reports add bug <bugType> <tested(true/false)> <message>");
                    }
                } else {
                    pdm.getReportManager().getHelp(sender);
                }
            } else {
                sender.sendMessage(pdm.getReportManager().reportsPrefix + "Error! You must be a player to execute this command!");
            }
        } else {
            pdm.getReportManager().getHelp(sender);
        }
    }
}
