package co.networkery.uvbeenzaned.BaseWars;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import co.networkery.uvbeenzaned.BaseWars.Game.TeamManager;
import co.networkery.uvbeenzaned.BaseWars.Game.Listeners.GameListener;
import co.networkery.uvbeenzaned.BaseWars.IO.Configurations;

public class BaseWars extends JavaPlugin {

	@SuppressWarnings("unused")
	private GameListener g;
	
	List<Listener> listeners = new ArrayList<Listener>();
	
	public void onEnable() {
		Configurations.initialize(this);
		TeamManager.initialize();
		listeners.add(new GameListener(this));
	}
	
	public void onDisable() {
		Configurations.saveAllConfigurations();
	}
	
}
