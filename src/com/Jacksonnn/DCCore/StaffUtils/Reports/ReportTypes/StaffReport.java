package com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportGeneral;
import github.scarsz.discordsrv.dependencies.jda.api.EmbedBuilder;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;
import github.scarsz.discordsrv.util.DiscordUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.Objects;

public class StaffReport {
    private int id;
    private String message;
    private Player player;
    private Player staffMember;
    private boolean resolved;
    private PlayerDisciplineManager pdm;
    private ReportGeneral.REPORT_TYPE type = ReportGeneral.REPORT_TYPE.STAFF;

    public StaffReport(String message, Player player, Player staffMember, boolean resolved, PlayerDisciplineManager pdm) {
        Bukkit.getLogger().info("Creating staff report...");
        int i = 0;
        for (StaffReport report : pdm.getReportManager().getStaffReports()) {
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
        this.player = player;
        this.resolved = resolved;
        this.message = message;
        this.staffMember = staffMember;
        this.pdm = pdm;

        try {
            saveReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StaffReport(int id, String message, Player player, Player staffMember, boolean resolved, PlayerDisciplineManager pdm) {
        this.id = id;
        this.player = player;
        this.resolved = resolved;
        this.message = message;
        this.staffMember = staffMember;
        this.pdm = pdm;
    }

    private String sendReportTo = Objects.requireNonNull(ConfigManager.defaultConfig.get().getString("StaffNotification.Reports.StaffReport.ChannelID"));
    private String tagReportTo = Objects.requireNonNull(ConfigManager.defaultConfig.get().getString("StaffNotification.Reports.StaffReport.StaffRoleID"));

    public void sendToDiscord() {
        TextChannel todoReportChannel = DiscordUtil.getTextChannelById(sendReportTo);

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor(this.getStaffMember().getName());
        embedBuilder.setTitle("Staff Report: " + player.getName());
        embedBuilder.setDescription(this.getMessage() + " (resolved: " + resolved + ") -" + this.getStaffMember().getName());
        //RED CHAT COLOR
        embedBuilder.setColor(new Color(170, 0, 0));

        todoReportChannel.sendMessage(embedBuilder.build()).queue();
    }

    public int getID() {
        return this.id;
    }

    public String getMessage() {
        return this.message;
    }

    public Player getStaffMember() {
        return this.staffMember;
    }

    public String getSendReportTo() {
        return this.sendReportTo;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isResolved() {
        return resolved;
    }

    public String getTagReportTo() {
        return this.tagReportTo;
    }

    public ReportGeneral.REPORT_TYPE getType() {
        return type;
    }

    public void saveReport() throws Exception {
        Bukkit.getLogger().info("Saving staff report...");
        pdm.saveReport(this);
        Bukkit.getLogger().info("Staff report saved successfully!");
    }
}
