package com.Jacksonnn.DCCore;

import com.Jacksonnn.DCCore.StaffUtils.Notes.Note;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.BugReport;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.PlayerReport;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.StaffReport;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.ToDoReport;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.Warning;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.UUID;

public class DCPlayer {
    private String name;
    private ArrayList<Note> notes;
    private ArrayList<Warning> warnings;
    private ArrayList<Object> reports;
    private long playTime;
    private long firstPlayed;
    private long lastPlayed;
    private UUID uuid;
    private Player player;
    private boolean quickdeposit;
    private GeneralMethods.ChatModes chatMode;

    private Location lastLocation;
    private String[] ranks;
    private int timesJoined;
    private int kills;
    private int deaths;
    private String lastIP;
    //WHEN NEW PLAYER JOINS

    public DCPlayer(Player player) {
        this.player = player;
        name = player.getName();
        uuid = player.getUniqueId();
        playTime = player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 60 * 1000;
        firstPlayed = player.getFirstPlayed();
        ranks = DCCore.permissions.getPlayerGroups(player);
        timesJoined = player.getStatistic(Statistic.LEAVE_GAME) + 1;
        kills = player.getStatistic(Statistic.PLAYER_KILLS);
        deaths = player.getStatistic(Statistic.DEATHS);
        InetSocketAddress address = player.getAddress();
        if (address != null)
            this.lastIP = address.getAddress().toString();
        lastLocation = player.getLocation();
        chatMode = GeneralMethods.ChatModes.GENERAL;

        DCCore.getDCPM().createDCPlayer(this);
    }

    //LOADING FROM DATABASE
    public DCPlayer(String name, UUID uuid, long playTime, long firstPlayed, long lastPlayed, boolean quickdeposit, Location lastLocation, String[] ranks, int timesJoined, int kills, int deaths, String lastIP) {
        this.player = Bukkit.getPlayer(uuid);
        this.name = name;
        this.uuid = uuid;
        this.firstPlayed = firstPlayed;
        this.lastPlayed = lastPlayed;
        this.playTime = playTime;
        this.quickdeposit = quickdeposit;
        this.chatMode = GeneralMethods.ChatModes.GENERAL;
        this.lastLocation = lastLocation;
        this.ranks = ranks;
        this.timesJoined = timesJoined;
        this.kills = kills;
        this.deaths = deaths;
        this.lastIP = lastIP;
    }
    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public String getLastIP() {
        return lastIP;
    }

    public void setLastIP(String lastIP) {
        this.lastIP = lastIP;
    }

    public long getFirstPlayed() {
        return firstPlayed;
    }

    public long getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(long lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }

    public void removeNote(Note note) {
        this.notes.remove(note);
    }

    public ArrayList<Warning> getWarnings() {
        return warnings;
    }

    public void addWarning(Warning warning) {
        this.warnings.add(warning);
    }

    public void removeWarning(Warning warning) {
        this.warnings.remove(warning);
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

    public void removeTodoReport(ToDoReport report) {
        reports.remove(report);
    }

    public void removeBugReport(BugReport report) {
        reports.remove(report);
    }

    public void removeStaffReport(StaffReport report) {
        reports.remove(report);
    }

    public void removePlayerReport(PlayerReport report) {
        reports.remove(report);
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

    public Location getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    public String[] getRanks() {
        return ranks;
    }

    public void setRanks(String[] ranks) {
        this.ranks = ranks;
    }

    public int getTimesJoined() {
        return timesJoined;
    }

    public void setTimesJoined(int timesJoined) {
        this.timesJoined = timesJoined;
    }
}