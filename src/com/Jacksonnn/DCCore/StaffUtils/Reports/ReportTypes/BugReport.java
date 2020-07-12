package com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportGeneral;
import github.scarsz.discordsrv.dependencies.jda.api.EmbedBuilder;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;
import github.scarsz.discordsrv.util.DiscordUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.awt.*;
import java.util.Objects;
import java.util.UUID;

public class BugReport {
    private int id;
    private String message;
    private String bugType;
    private UUID staffMember;
    private boolean tested;
    private PlayerDisciplineManager pdm;
    private ReportGeneral.REPORT_TYPE type = ReportGeneral.REPORT_TYPE.BUG;

    public BugReport(String message, String bugType, UUID staffMember, boolean tested, PlayerDisciplineManager pdm) {
        Bukkit.getLogger().info("Creating bug report...");
        int i = 0;
        for (BugReport report : pdm.getReportManager().getBugReports()) {
            if (report.getID() >= i) {
                i = report.getID();
                i++;
            }
        }

        if (i <= 0) {
            i = 1;
        }

        this.id = i;
        Bukkit.getLogger().info("Getting id... id=" + this.id);
        this.bugType = bugType;
        this.tested = tested;
        this.message = message;
        this.staffMember = staffMember;
        this.pdm = pdm;

        try {
            saveReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BugReport(int id, String message, String bugType, UUID staffMember, boolean tested, PlayerDisciplineManager pdm) {
        this.id = id;
        this.bugType = bugType;
        this.tested = tested;
        this.message = message;
        this.staffMember = staffMember;
        this.pdm = pdm;
    }

    private String sendReportTo = Objects.requireNonNull(ConfigManager.defaultConfig.get().getString("StaffNotification.Reports.BugReport.ChannelID"));
    private String tagReportTo = Objects.requireNonNull(ConfigManager.defaultConfig.get().getString("StaffNotification.Reports.BugReport.StaffRoleID"));

    public void sendToDiscord() {
        TextChannel todoReportChannel = DiscordUtil.getTextChannelById(sendReportTo);

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor(Bukkit.getPlayer(getStaffMember()).getName());
        embedBuilder.setTitle("Bug Report: " + bugType);
        embedBuilder.setDescription(this.getMessage() + " (tested: " + tested + ") -" + Bukkit.getPlayer(getStaffMember()).getName());
        //RED CHAT COLOR
        embedBuilder.setColor(new Color(170, 0, 0));

        todoReportChannel.sendMessage(embedBuilder.build()).queue();

        String chatprefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.Prefix")));
        String msgColor = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.msgColor")));

        Bukkit.broadcast(chatprefix + ChatColor.DARK_RED + "NEW BUG REPORT: " + msgColor + Bukkit.getPlayer(getStaffMember()).getName() + " just submitted a new bug report about " + bugType, "DCCore.staffchats.Staff");
    }

    public int getID() {
        return this.id;
    }

    public String getMessage() {
        return this.message;
    }

    public UUID getStaffMember() {
        return this.staffMember;
    }

    public String getSendReportTo() {
        return this.sendReportTo;
    }

    public String getBugType() {
        return bugType;
    }

    public boolean isTested() {
        return tested;
    }

    public String getTagReportTo() {
        return this.tagReportTo;
    }

    public ReportGeneral.REPORT_TYPE getType() {
        return type;
    }

    public void saveReport() throws Exception {
        Bukkit.getLogger().info("Saving bug report...");
        pdm.saveReport(this);
        Bukkit.getLogger().info("Player bug saved successfully!");
    }
}
