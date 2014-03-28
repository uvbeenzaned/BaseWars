package co.networkery.uvbeenzaned.BaseWars.IO;

import org.bukkit.plugin.java.JavaPlugin;

public class Configurations {

	private static Configuration config;

	public static void initialize(JavaPlugin pl) {
		setConfig(new Configuration(pl, "config.yml"));
		config.saveDefaultConfig();
	}

	public static void reloadAllConfigurations() {
		config.reloadConfig();
	}

	public static void saveAllConfigurations() {
		config.saveConfig();
	}

	public static void saveConfig() {
		config.saveConfig();
	}

	public static Configuration getConfig() {
		return config;
	}

	public static void setConfig(Configuration config) {
		Configurations.config = config;
	}

}
