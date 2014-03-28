package co.networkery.uvbeenzaned.BaseWars;

import org.bukkit.plugin.java.JavaPlugin;

public class BaseWars extends JavaPlugin {

	@SuppressWarnings("unused")
	private GameListener g;
	
	public void onEnable() {
		Configurations.initialize(this);
		g = new GameListener(this);
	}
	
	public void onDisable() {
		Configurations.saveAllConfigurations();
	}
	
}
