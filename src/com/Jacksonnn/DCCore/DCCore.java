package com.Jacksonnn.DCCore;

import com.Jacksonnn.DCCore.AutoAnnouncer.AnnouncementCommands;
import com.Jacksonnn.DCCore.AutoAnnouncer.AnnouncementManager;
import com.Jacksonnn.DCCore.AutoAnnouncer.AnnouncerThread;
import com.Jacksonnn.DCCore.BannedWords.BannedWordsCommand;
import com.Jacksonnn.DCCore.BannedWords.BannedWordsListener;
import com.Jacksonnn.DCCore.Broadcast.BroadcastCommand;
import com.Jacksonnn.DCCore.ChatSensor.ChatListener;
import com.Jacksonnn.DCCore.Configuration.ConfigManager;
import com.Jacksonnn.DCCore.Configuration.DiscordWebHook;
import com.Jacksonnn.DCCore.DiamondLuck.DiamondLuck;
import com.Jacksonnn.DCCore.DiamondLuck.ResponseListener;
import com.Jacksonnn.DCCore.Guides.BendingGuideCommand;
import com.Jacksonnn.DCCore.Guides.DCGuideCommand;
import com.Jacksonnn.DCCore.OverrideCommands.*;
import com.Jacksonnn.DCCore.QuickDeposit.QuickDepositListener;
import com.Jacksonnn.DCCore.RandomTP.RandomTP;
import com.Jacksonnn.DCCore.Rankup.*;
import com.Jacksonnn.DCCore.Spawners.SpawnerListener;
import com.Jacksonnn.DCCore.StaffUtils.Notes.NotesCommand;
import com.Jacksonnn.DCCore.StaffUtils.Notes.NotesGeneral;
import com.Jacksonnn.DCCore.StaffUtils.PlayerDisciplineManager;
import com.Jacksonnn.DCCore.StaffUtils.PlayerInfoCommand;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportCommand;
import com.Jacksonnn.DCCore.StaffUtils.Reports.ReportGeneral;
import com.Jacksonnn.DCCore.StaffUtils.StaffChannels.*;
import com.Jacksonnn.DCCore.StaffUtils.StaffCounts.StaffCountCommand;
import com.Jacksonnn.DCCore.StaffUtils.StaffCounts.StaffNotification;
import com.Jacksonnn.DCCore.StaffUtils.StaffSpawnCommand;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.WarningCommand;
import com.Jacksonnn.DCCore.StaffUtils.Warnings.WarningGeneral;
import com.Jacksonnn.DCCore.Storage.DatabaseManager;
import com.Jacksonnn.DCCore.Suggestions.SuggestCommand;
import com.Jacksonnn.DCCore.Suggestions.SuggestionsHandler;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.sql.SQLException;

public class DCCore extends JavaPlugin {
	
	public static DCCore plugin;
	public static Economy economy;
	public static Permission permissions;
	private static SuggestionsHandler suggestionsHandler;

	private final PluginManager pm = Bukkit.getServer().getPluginManager();
	private DatabaseManager databaseManager;

	private PlayerDisciplineManager pdm;
	private NotesGeneral nG;
	private WarningGeneral wG;
	private ReportGeneral rG;

	private static DCPlayerManager dcpm;

	public void onEnable() {
		plugin = this;

		ConfigManager.setupConfigManager();

		this.nG = new NotesGeneral();
		this.wG = new WarningGeneral();
		this.rG = new ReportGeneral();

		dcpm = new DCPlayerManager(plugin);

		suggestionsHandler = new SuggestionsHandler();
		pdm = new PlayerDisciplineManager(plugin);

		registerListeners();
		registerCommands();
		setupEconomy();
		setupPermissions();

		databaseManager = new DatabaseManager(this);
		try {
			databaseManager.init();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			getServer().getPluginManager().disablePlugin(this);
		}

		try {
			pdm.createPunishmentTables();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		dcpm.loadDCPlayers();
		pdm.loadNotes();
		pdm.loadWarnings();
		pdm.loadReports();

		try {
			dcpm.createDCPlayersTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(this, new AnnouncerThread(this), AnnouncementManager.getInterval() * 20L, AnnouncementManager.getInterval() * 20L);
		Bukkit.getServer().getLogger().info("DCCore has successfully been enabled!");
		
		//initialize Discord Webhook
	        final DiscordWebhook web = new DiscordWebhook(this.getConfig().getString("Webhook"));
       		final String message = "Enabled";
        	final DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
        	embed.addField(message, ":)", true);
        	embed.setColor(Color.red);
        	web.addEmbed(embed);
        	try {
        	    web.execute();
        	}
       		 catch (Exception exception) {
         	   this.getLogger().warning("[Discord Vote Logger]Unable to send plugin load message to discord");
       		}
        	System.out.println("Web Vote logger by SaltyGraham loaded");
        	Bukkit.getPluginManager().registerEvents((Listener)new DCPlayerListener(this), (Plugin)this);	
		
		
	}
	
	public void onDisable() {
		Bukkit.getServer().getLogger().info("DCCore has successfully been disabled!");
	}
	
/*
 * REGISTER LISTENERS AND COMMANDS
 */
	private void registerListeners() {
		pm.registerEvents(new SpawnerListener(), this);
		pm.registerEvents(new QuickDepositListener(), this);
		pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new ResponseListener(), this);
        pm.registerEvents(new GuestQuizListener(), this);
        pm.registerEvents(new onChatEvent(), this);
        pm.registerEvents(new StaffNotification(), this);
        pm.registerEvents(new Vanish(), this);
        pm.registerEvents(new BannedWordsListener(), this);
		pm.registerEvents(new DCPlayerListener(), this);
	}
	
	private void registerCommands() {
		this.getCommand("dcguide").setExecutor(new DCGuideCommand());
		this.getCommand("bendingguide").setExecutor(new BendingGuideCommand());
		this.getCommand("broadcast").setExecutor(new BroadcastCommand());
		this.getCommand("bannedwords").setExecutor(new BannedWordsCommand());
		this.getCommand("dccore").setExecutor(new CoreCommands());
		this.getCommand("doguestquiz").setExecutor(new DoGuestQuizCommand());
		this.getCommand("rankup").setExecutor(new Rankup());
		this.getCommand("playtime").setExecutor(new PlayTime());
		this.getCommand("ranks").setExecutor(new Ranks());
		this.getCommand("diamondluck").setExecutor(new DiamondLuck());

		if (ConfigManager.defaultConfig.get().getBoolean("RandomTP.enabled")) {
			this.getCommand("randomtp").setExecutor(new RandomTP());
		}

		this.getCommand("suggest").setExecutor(new SuggestCommand());
//		this.getCommand("b").setExecutor(new PKAlias());
		this.getCommand("vote").setExecutor(new Vote());
		this.getCommand("forums").setExecutor(new Forums());
		this.getCommand("donate").setExecutor(new Donate());
		this.getCommand("website").setExecutor(new Website());
		this.getCommand("bendinghelp").setExecutor(new BendingHelp());
		this.getCommand("avatar").setExecutor(new Avatar());
		this.getCommand("lightspirit").setExecutor(new LightSpirit());
		this.getCommand("darkspirit").setExecutor(new DarkSpirit());
//		this.getCommand("bendingfix").setExecutor(new BendingFix());
		this.getCommand("headofstaff").setExecutor(new HOSCommand());
		this.getCommand("developer").setExecutor(new DeveloperCommand());
		this.getCommand("managers").setExecutor(new ManagersCommand());
		this.getCommand("moderators").setExecutor(new ModeratorsCommand());
		this.getCommand("eventhosts").setExecutor(new EventHostsCommand());
		this.getCommand("staffchat").setExecutor(new StaffChatCommand());
		this.getCommand("artists").setExecutor(new ArtistCommand());
		this.getCommand("staffcount").setExecutor(new StaffCountCommand());
		this.getCommand("announcer").setExecutor(new AnnouncementCommands());
		this.getCommand("staffspawn").setExecutor(new StaffSpawnCommand(plugin));
		this.getCommand("playerinfo").setExecutor(new PlayerInfoCommand());

		//NOTES COMMAND
		NotesCommand notesCommand = new NotesCommand(this);
		this.getCommand("notes").setExecutor(notesCommand);

		//WARN COMMAND
		WarningCommand warningCommand = new WarningCommand(this);
		this.getCommand("warnings").setExecutor(warningCommand);

		//REPORT COMMAND
		ReportCommand reportCommand = new ReportCommand(this);
		this.getCommand("reports").setExecutor(reportCommand);
	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null)
			return false;
		economy = rsp.getProvider();
		return true;
	}

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
		if (rsp == null)
			return false;
		permissions = rsp.getProvider();
		return true;
	}

	public DatabaseManager getDatabaseManager() {
		return databaseManager;
	}

	public NotesGeneral getNotesGeneral() {
		return nG;
	}

	public WarningGeneral getWarningGeneral() {
		return wG;
	}

	public ReportGeneral getReportGeneral() {
		return rG;
	}

	public PlayerDisciplineManager getPDM() {
		return pdm;
	}

	public static DCPlayerManager getDCPM() {
		return dcpm;
	}

	public static SuggestionsHandler getSuggestionsHandler() {
		return suggestionsHandler;
	}
}

