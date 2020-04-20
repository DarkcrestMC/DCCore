package com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes;

import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportGeneral;
import github.scarsz.discordsrv.dependencies.jda.api.EmbedBuilder;
import github.scarsz.discordsrv.dependencies.jda.api.entities.TextChannel;
import github.scarsz.discordsrv.util.DiscordUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.Collection;
import java.util.Objects;

public class ToDoReport {
    private int id;
    private String message;
    private Player staffMember;
    private PlayerDisciplineManager pdm;
    private ReportGeneral.REPORT_TYPE type = ReportGeneral.REPORT_TYPE.TODO;

    public ToDoReport(String message, Player staffMember, PlayerDisciplineManager pdm) {
        Bukkit.getLogger().info("Creating todo report...");
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
        this.message = message;
        this.staffMember = staffMember;
        this.pdm = pdm;
        try {
            saveReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ToDoReport(int id, String message, Player staffMember, PlayerDisciplineManager pdm) {
        this.id = id;
        this.message = message;
        this.staffMember = staffMember;
        this.pdm = pdm;
    }


    private String sendReportTo = Objects.requireNonNull(ConfigManager.defaultConfig.get().getString("StaffNotification.Reports.ToDoReport.ChannelID"));
    private String tagReportTo = Objects.requireNonNull(ConfigManager.defaultConfig.get().getString("StaffNotification.Reports.ToDoReport.StaffRoleID"));

    public void sendToDiscord() {
        TextChannel todoReportChannel = DiscordUtil.getTextChannelById(sendReportTo);

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor(this.getStaffMember().getName());
        embedBuilder.setTitle("TODO REPORT");
        embedBuilder.setDescription(this.getMessage() + " -" + this.getStaffMember().getName());
        //RED CHAT COLOR
        embedBuilder.setColor(new Color(170, 0, 0));

        todoReportChannel.sendMessage(embedBuilder.build()).queue();

        String chatprefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.HoS.Prefix")));
        String msgColor = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigManager.langConfig.get().getString("Language.StaffChats.HoS.msgColor")));
        Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();

        for (Player player : onlinePlayers) {
            if (player.hasPermission("DCCore.staffchats.HoS")) {
                player.sendMessage(chatprefix + ChatColor.DARK_RED + "NEW TODO REPORT: " + msgColor + staffMember.getName() + " just submitted a new todo report.");
            }
        }
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

    public String getTagReportTo() {
        return this.tagReportTo;
    }

    public ReportGeneral.REPORT_TYPE getType() {
        return type;
    }

    public void saveReport() throws Exception {
        Bukkit.getLogger().info("Saving todo report...");
        pdm.saveReport(this);
        Bukkit.getLogger().info("Todo report saved successfully!");
    }
}
