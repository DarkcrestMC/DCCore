package com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.DCPlayer;
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

public class PlayerReport {
    private int id;
    private String message;
    private UUID player;
    private UUID staffMember;
    private boolean resolved;
    private PlayerDisciplineManager pdm;
    private ReportGeneral.REPORT_TYPE type = ReportGeneral.REPORT_TYPE.PLAYER;

    public PlayerReport(String message, DCPlayer player, UUID staffMember, boolean resolved, PlayerDisciplineManager pdm) {
        Bukkit.getLogger().info("Creating player report...");
        int i = 0;
        for (PlayerReport report : pdm.getReportManager().getPlayerReports()) {
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
        this.player = player.getUuid();
        this.resolved = resolved;
        this.message = message;
        this.staffMember = staffMember;
        this.pdm = pdm;

        player.addPlayerReport(this);

        try {
            saveReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PlayerReport(int id, String message, UUID player, UUID staffMember, boolean resolved, PlayerDisciplineManager pdm) {
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
        embedBuilder.setAuthor(Bukkit.getPlayer(getStaffMember()).getName());
        embedBuilder.setTitle("Player Report: " + Bukkit.getPlayer(getPlayer()).getName());
        embedBuilder.setDescription(this.getMessage() + " (resolved: " + resolved + ") -" + Bukkit.getPlayer(getStaffMember()).getName());
        //RED CHAT COLOR
        embedBuilder.setColor(new Color(170, 0, 0));

        todoReportChannel.sendMessage(embedBuilder.build()).queue();

        String chatprefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.Prefix")));
        String msgColor = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.StaffChat.msgColor")));

        Bukkit.broadcast(chatprefix + ChatColor.DARK_RED + "NEW PLAYER REPORT: " + msgColor + Bukkit.getPlayer(getStaffMember()).getName() + " just submitted a new player report against " + Bukkit.getPlayer(getStaffMember()).getName() + ".", "DCCore.staffchats.Staff");
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

    public UUID getPlayer() {
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
