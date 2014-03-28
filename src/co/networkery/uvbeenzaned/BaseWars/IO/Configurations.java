package co.networkery.uvbeenzaned.BaseWars.IO;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Configurations {

	private static Configuration config;
	private static Configuration teamsConfig;

	public static void initialize(JavaPlugin pl) {
		setConfig(new Configuration(pl, "config.yml"));
		setTeamsConfig(new Configuration(pl, "teams.yml"));
		config.saveDefaultConfig();
		teamsConfig.saveDefaultConfig();
	}

	public static void reloadAllConfigurations() {
		config.reloadConfig();
		teamsConfig.reloadConfig();
	}

	public static void saveAllConfigurations() {
		config.saveConfig();
		teamsConfig.saveConfig();
	}

	public static void saveConfig() {
		config.saveConfig();
	}

	public static FileConfiguration getConfig() {
		return config.getConfig();
	}

	public static void setConfig(Configuration config) {
		Configurations.config = config;
	}
	
	public static void saveTeamsConfig() {
		teamsConfig.saveConfig();
	}

	public static FileConfiguration getTeamsConfig() {
		return teamsConfig.getConfig();
	}

	public static void setTeamsConfig(Configuration teamsConfig) {
		Configurations.teamsConfig = teamsConfig;
	}

}
