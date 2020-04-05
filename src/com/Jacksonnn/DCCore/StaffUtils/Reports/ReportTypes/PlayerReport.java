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

public class PlayerReport {
    private int id;
    private String message;
    private Player player;
    private Player staffMember;
    private boolean resolved;
    private PlayerDisciplineManager pdm;
    private ReportGeneral.REPORT_TYPE type = ReportGeneral.REPORT_TYPE.PLAYER;

    public PlayerReport(String message, Player player, Player staffMember, boolean resolved, PlayerDisciplineManager pdm) {
        Bukkit.getLogger().info("Creating player report...");
        int i = 0;
        for (ToDoReport report : pdm.getReportManager().getTodoReports()) {
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

    public PlayerReport(int id, String message, Player player, Player staffMember, boolean resolved, PlayerDisciplineManager pdm) {
        this.id = id;
        this.player = player;
        this.resolved = resolved;
        this.message = message;
        this.staffMember = staffMember;
        this.pdm = pdm;
    }

    private String sendReportTo = Objects.requireNonNull(ConfigManager.defaultConfig.get().getString("StaffNotification.Reports.PlayerReport.ChannelID"));
    private String tagReportTo = Objects.requireNonNull(ConfigManager.defaultConfig.get().getString("StaffNotification.Reports.PlayerReport.StaffRoleID"));

    public void sendToDiscord() {
        TextChannel todoReportChannel = DiscordUtil.getTextChannelById(sendReportTo);

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor(this.getStaffMember().getName());
        embedBuilder.setTitle("Player Report: " + player.getName());
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
        Bukkit.getLogger().info("Saving player report...");
        pdm.saveReport(this);
        Bukkit.getLogger().info("Player report saved successfully!");
    }
}
