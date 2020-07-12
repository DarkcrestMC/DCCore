package com.Jacksonnn.DCCore.StaffUtils;

import com.Jacksonnn.DCCore.DCCore;
import com.Jacksonnn.DCCore.StaffUtils.Notes.Note;
import com.Jacksonnn.DCCore.StaffUtils.Notes.NotesGeneral;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportGeneral;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.BugReport;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.PlayerReport;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.StaffReport;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportTypes.ToDoReport;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.Warning;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.WarningGeneral;
import com.Jacksonnn.DCCore.Storage.*;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerDisciplineManager {

    private DCCore plugin;
    private NotesGeneral notesGeneral;
    private WarningGeneral warningGeneral;
    private ReportGeneral reportGeneral;

    public PlayerDisciplineManager(DCCore main) {
        this.plugin = main;
        this.notesGeneral = main.getNotesGeneral();
        this.warningGeneral = main.getWarningGeneral();
        this.reportGeneral = main.getReportGeneral();
    }

    public NotesGeneral getNoteManager() {
        return notesGeneral;
    }

    public WarningGeneral getWarningManager() {
        return warningGeneral;
    }

    public ReportGeneral getReportManager() {
        return reportGeneral;
    }

    public void loadNotes() {
        String query;
        if (plugin.
                getDatabaseManager()
                .getDatabase()
                instanceof
                Mysql) {
            query = NoteQueries.GET_NOTES.getMysqlQuery();
        } else {
            query = NoteQueries.GET_NOTES.getSqliteQuery();
        }
        try {
            PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase().getConnection().prepareStatement(query);
            ResultSet getNotes = preparedStatement.executeQuery();

            int i = 0;
            while (getNotes.next()) {
                int id = getNotes.getInt("id");
                UUID player = UUID.fromString(getNotes.getString("player"));
                UUID staffMember = UUID.fromString(getNotes.getString("staffMember"));

                String message = getNotes.getString("message");

                getNoteManager().addNote(new Note(id, player, staffMember, message, this));
                i++;
            }

            Bukkit.getLogger().info("[DCCore] Loaded " + i + " notes.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadWarnings() {
        String query;
        if (plugin.getDatabaseManager().getDatabase() instanceof Mysql) {
            query = WarningQueries.GET_WARNINGS.getMysqlQuery();
        } else {
            query = WarningQueries.GET_WARNINGS.getSqliteQuery();
        }
        try {
            PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase().getConnection().prepareStatement(query);
            ResultSet getWarnings = preparedStatement.executeQuery();

            int i = 0;
            while (getWarnings.next()) {
                int id = getWarnings.getInt("id");
                UUID player = UUID.fromString(getWarnings.getString("player"));
                UUID staffMember = UUID.fromString(getWarnings.getString("staffMember"));

                String reason = getWarnings.getString("reason");

                getWarningManager().addWarning(new Warning(id, player, staffMember, reason, this));
                i++;
            }

            Bukkit.getLogger().info("[DCCore] Loaded " + i + " warnings.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadReports() {
        String query;
        if (plugin.getDatabaseManager().getDatabase() instanceof Mysql) {
            query = ReportQueries.GET_REPORTS.getMysqlQuery();
        } else {
            query = ReportQueries.GET_REPORTS.getSqliteQuery();
        }
        try {
            PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase().getConnection().prepareStatement(query);
            ResultSet getReports = preparedStatement.executeQuery();

            int i = 0;
            while (getReports.next()) {
                int id = getReports.getInt("id");
                String type = getReports.getString("type");

                if (type.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.TODO.getShorthand())) {
                    String message = getReports.getString("message");

                    UUID staffMember = UUID.fromString(getReports.getString("staffMember"));

                    getReportManager().addReport(new ToDoReport(id, message, staffMember, this));
                } else if (type.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.PLAYER.getShorthand())) {
                    String message = getReports.getString("message");
                    UUID player = UUID.fromString(getReports.getString("player"));
                    UUID staffMember = UUID.fromString(getReports.getString("staffMember"));

                    boolean resolved = Boolean.parseBoolean(getReports.getString("resolved"));

                    getReportManager().addReport(new PlayerReport(id, message, player, staffMember, resolved, this));

                } else if (type.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.STAFF.getShorthand())) {
                    String message = getReports.getString("message");
                    UUID player = UUID.fromString(getReports.getString("player"));
                    UUID staffMember = UUID.fromString(getReports.getString("staffMember"));

                    boolean resolved = Boolean.parseBoolean(getReports.getString("resolved"));

                    getReportManager().addReport(new StaffReport(id, message, player, staffMember, resolved, this));

                } else if (type.equalsIgnoreCase(ReportGeneral.REPORT_TYPE.BUG.getShorthand())) {
                    String message = getReports.getString("message");
                    String bugType = getReports.getString("bugType");
                    UUID staffMember = UUID.fromString(getReports.getString("staffMember"));

                    boolean tested = Boolean.parseBoolean(getReports.getString("tested"));

                    getReportManager().addReport(new BugReport(id, message, bugType, staffMember, tested ,this));
                }

                i++;
            }

            Bukkit.getLogger().info("[DCCore] Loaded " + i + " reports.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPunishmentTables() throws SQLException {
        if (plugin.getDatabaseManager().getDatabase() instanceof Mysql) {
            plugin.getDatabaseManager().getDatabase().getConnection().createStatement().execute(NoteQueries.CREATE_TABLE_NOTES.getMysqlQuery());
            plugin.getDatabaseManager().getDatabase().getConnection().createStatement().execute(WarningQueries.CREATE_TABLE_WARNINGS.getMysqlQuery());
            plugin.getDatabaseManager().getDatabase().getConnection().createStatement().execute(ReportQueries.CREATE_TABLE_REPORTS.getMysqlQuery());
        } else {
            plugin.getDatabaseManager().getDatabase().getConnection().createStatement().execute(NoteQueries.CREATE_TABLE_NOTES.getSqliteQuery());
            plugin.getDatabaseManager().getDatabase().getConnection().createStatement().execute(WarningQueries.CREATE_TABLE_WARNINGS.getSqliteQuery());
            plugin.getDatabaseManager().getDatabase().getConnection().createStatement().execute(ReportQueries.CREATE_TABLE_REPORTS.getSqliteQuery());
        }
    }

    public void saveNote(Note note) {
        getNoteManager().addNote(note);

        String query;
        if (plugin.getDatabaseManager().getDatabase() instanceof SqlLite) {
            query = NoteQueries.CREATE_NOTE.getSqliteQuery();
        } else {
            query = NoteQueries.CREATE_NOTE.getMysqlQuery();
        }
        try {
            PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase()
                    .getConnection().prepareStatement(query);
            preparedStatement.setString(1, note.getPlayer().toString());
            preparedStatement.setString(2, note.getStaffMember().toString());
            preparedStatement.setString(3, note.getNote());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveWarning(Warning warning) {
        getWarningManager().addWarning(warning);

        String query;
        if (plugin.getDatabaseManager().getDatabase() instanceof SqlLite) {
            query = WarningQueries.CREATE_WARNING.getSqliteQuery();
        } else {
            query = WarningQueries.CREATE_WARNING.getMysqlQuery();
        }
        try {
            PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase()
                    .getConnection().prepareStatement(query);
            preparedStatement.setString(1, warning.getPlayer().toString());
            preparedStatement.setString(2, warning.getStaffMember().toString());
            preparedStatement.setString(3, warning.getReason());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveReport(Object report) throws Exception {
        getReportManager().addReport(report);

        if (report instanceof ToDoReport) {
            String query;
            if (plugin.getDatabaseManager().getDatabase() instanceof SqlLite) {
                query = ReportQueries.CREATE_TODO_REPORT.getSqliteQuery();
            } else {
                query = ReportQueries.CREATE_TODO_REPORT.getMysqlQuery();
            }
            try {
                PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase()
                        .getConnection().prepareStatement(query);
                preparedStatement.setString(1, ((ToDoReport) report).getStaffMember().toString());
                preparedStatement.setString(2, ((ToDoReport) report).getMessage());
                preparedStatement.setString(3, ((ToDoReport) report).getType().getShorthand());
                preparedStatement.execute();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (report instanceof PlayerReport) {
            String query;
            if (plugin.getDatabaseManager().getDatabase() instanceof SqlLite) {
                query = ReportQueries.CREATE_PLAYER_REPORT.getSqliteQuery();
            } else {
                query = ReportQueries.CREATE_PLAYER_REPORT.getMysqlQuery();
            }
            try {
                PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase()
                        .getConnection().prepareStatement(query);
                preparedStatement.setString(1, ((PlayerReport) report).getPlayer().toString());
                preparedStatement.setString(2, ((PlayerReport) report).getStaffMember().toString());
                preparedStatement.setString(3, String.valueOf(((PlayerReport) report).isResolved()));
                preparedStatement.setString(4, ((PlayerReport) report).getMessage());
                preparedStatement.setString(5, ((PlayerReport) report).getType().getShorthand());
                preparedStatement.execute();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (report instanceof StaffReport) {
            String query;
            if (plugin.getDatabaseManager().getDatabase() instanceof SqlLite) {
                query = ReportQueries.CREATE_STAFF_REPORT.getSqliteQuery();
            } else {
                query = ReportQueries.CREATE_STAFF_REPORT.getMysqlQuery();
            }
            try {
                PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase()
                        .getConnection().prepareStatement(query);
                preparedStatement.setString(1, ((StaffReport) report).getPlayer().toString());
                preparedStatement.setString(2, ((StaffReport) report).getStaffMember().toString());
                preparedStatement.setString(3, String.valueOf(((StaffReport) report).isResolved()));
                preparedStatement.setString(4, ((StaffReport) report).getMessage());
                preparedStatement.setString(5, ((StaffReport) report).getType().getShorthand());
                preparedStatement.execute();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (report instanceof BugReport) {
            String query;
            if (plugin.getDatabaseManager().getDatabase() instanceof SqlLite) {
                query = ReportQueries.CREATE_BUG_REPORT.getSqliteQuery();
            } else {
                query = ReportQueries.CREATE_BUG_REPORT.getMysqlQuery();
            }
            try {
                PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase()
                        .getConnection().prepareStatement(query);
                preparedStatement.setString(1, ((BugReport) report).getStaffMember().toString());
                preparedStatement.setString(2, ((BugReport) report).getBugType());
                preparedStatement.setString(3, String.valueOf(((BugReport) report).isTested()));
                preparedStatement.setString(4, ((BugReport) report).getMessage());
                preparedStatement.setString(5, ((BugReport) report).getType().getShorthand());
                preparedStatement.execute();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new Exception();
        }
    }

    public void deleteNote(Note note) {
        getNoteManager().removeNote(note);

        String query;
        if (plugin.getDatabaseManager().getDatabase() instanceof SqlLite) {
            query = NoteQueries.DELETE_NOTE.getSqliteQuery();
        } else {
            query = NoteQueries.DELETE_NOTE.getMysqlQuery();
        }
        try {
            PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase()
                    .getConnection().prepareStatement(query);
            preparedStatement.setInt(1, note.getID());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWarning(Warning warning) {
        getWarningManager().removeWarning(warning);

        String query;
        if (plugin.getDatabaseManager().getDatabase() instanceof SqlLite) {
            query = WarningQueries.DELETE_WARNING.getSqliteQuery();
        } else {
            query = WarningQueries.DELETE_WARNING.getMysqlQuery();
        }
        try {
            PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase()
                    .getConnection().prepareStatement(query);
            preparedStatement.setInt(1, warning.getID());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReport(Object report) throws Exception {
        getReportManager().removeReport(report);

        if (report instanceof ToDoReport) {
            String query;
            if (plugin.getDatabaseManager().getDatabase() instanceof SqlLite) {
                query = ReportQueries.DELETE_REPORT.getSqliteQuery();
            } else {
                query = ReportQueries.DELETE_REPORT.getMysqlQuery();
            }
            try {
                PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase()
                        .getConnection().prepareStatement(query);
                preparedStatement.setInt(1, ((ToDoReport) report).getID());
                preparedStatement.setString(2, ((ToDoReport) report).getType().getShorthand());
                preparedStatement.execute();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (report instanceof  PlayerReport) {
            String query;
            if (plugin.getDatabaseManager().getDatabase() instanceof SqlLite) {
                query = ReportQueries.DELETE_REPORT.getSqliteQuery();
            } else {
                query = ReportQueries.DELETE_REPORT.getMysqlQuery();
            }
            try {
                PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase()
                        .getConnection().prepareStatement(query);
                preparedStatement.setInt(1, ((PlayerReport) report).getID());
                preparedStatement.setString(2, ((PlayerReport) report).getType().getShorthand());
                preparedStatement.execute();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (report instanceof StaffReport) {
            String query;
            if (plugin.getDatabaseManager().getDatabase() instanceof SqlLite) {
                query = ReportQueries.DELETE_REPORT.getSqliteQuery();
            } else {
                query = ReportQueries.DELETE_REPORT.getMysqlQuery();
            }
            try {
                PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase()
                        .getConnection().prepareStatement(query);
                preparedStatement.setInt(1, ((StaffReport) report).getID());
                preparedStatement.setString(2, ((StaffReport) report).getType().getShorthand());
                preparedStatement.execute();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (report instanceof BugReport) {
            String query;
            if (plugin.getDatabaseManager().getDatabase() instanceof SqlLite) {
                query = ReportQueries.DELETE_REPORT.getSqliteQuery();
            } else {
                query = ReportQueries.DELETE_REPORT.getMysqlQuery();
            }
            try {
                PreparedStatement preparedStatement = plugin.getDatabaseManager().getDatabase()
                        .getConnection().prepareStatement(query);
                preparedStatement.setInt(1, ((BugReport) report).getID());
                preparedStatement.setString(2, ((BugReport) report).getType().getShorthand());
                preparedStatement.execute();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new Exception();
        }
    }
}