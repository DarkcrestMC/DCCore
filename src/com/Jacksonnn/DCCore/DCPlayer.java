package com.Jacksonnn.DCCore;

import com.Jacksonnn.DCCore.StaffUtils.Notes.Note;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.BugReport;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.PlayerReport;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.StaffReport;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.ToDoReport;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.Warning;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class DCPlayer {
    private String name;
    private ArrayList<Note> notes;
    private ArrayList<Warning> warnings;
    private ArrayList<Object> reports;
    private long playTime;
    private UUID uuid;
    private Player player;
    private boolean quickdeposit;
    private String chatMode;

    public DCPlayer(Player player) {
        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        playTime = 0;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }

    public ArrayList<Warning> getWarnings() {
        return warnings;
    }

    public void addWarning(Warning warning) {
        this.warnings.add(warning);
    }

    public ArrayList<Object> getReports() {
        return reports;
    }

    public void addTodoReport(ToDoReport report) {
        reports.add(report);
    }

    public void addBugReport(BugReport report) {
        reports.add(report);
    }

    public void addStaffReport(StaffReport report) {
        reports.add(report);
    }

    public void addPlayerReport(PlayerReport report) {
        reports.add(report);
    }

    public long getPlayTime() {
        return playTime;
    }

    public void setPlayTime(long playTime) {
        this.playTime = playTime;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isQuickdeposit() {
        return quickdeposit;
    }

    public void setQuickdeposit(boolean quickdeposit) {
        this.quickdeposit = quickdeposit;
    }

    public String getChatMode() {
        return chatMode;
    }

    public void setChatMode(String chatMode) {
        this.chatMode = chatMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
