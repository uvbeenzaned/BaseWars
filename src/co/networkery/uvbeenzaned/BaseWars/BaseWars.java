package co.networkery.uvbeenzaned.BaseWars;

import org.bukkit.plugin.java.JavaPlugin;

import co.networkery.uvbeenzaned.BaseWars.IO.Configurations;
import co.networkery.uvbeenzaned.BaseWars.Listeners.GameListener;

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
