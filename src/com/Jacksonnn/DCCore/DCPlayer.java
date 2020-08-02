package com.Jacksonnn.DCCore;

import com.Jacksonnn.DCCore.StaffUtils.Notes.Note;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.BugReport;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.PlayerReport;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.StaffReport;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.ToDoReport;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.Warning;
import org.bukkit.Bukkit;
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
    private GeneralMethods.ChatModes chatMode;

    //WHEN NEW PLAYER JOINS
    public DCPlayer(Player player) {
        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        playTime = 0;

        DCCore.getDCPM().createDCPlayer(this);
    }

    //LOADING FROM DATABASE
    public DCPlayer(String name, UUID uuid) {
        this.player = Bukkit.getPlayer(uuid);
        this.name = name;
        this.uuid = uuid;
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isQuickdeposit() {
        return quickdeposit;
    }

    public void setQuickdeposit(boolean bool) {
        this.quickdeposit = bool;
    }

    public GeneralMethods.ChatModes getChatMode() {
        return chatMode;
    }

    public void setChatMode(GeneralMethods.ChatModes chatMode) {
        this.chatMode = chatMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnline() {
        return Bukkit.getPlayer(this.uuid) != null;
    }
}