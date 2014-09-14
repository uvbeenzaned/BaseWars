package co.networkery.uvbeenzaned.BaseWars;

import co.networkery.uvbeenzaned.BaseWars.Game.Clock;
import co.networkery.uvbeenzaned.BaseWars.Game.TeamManager;
import co.networkery.uvbeenzaned.BaseWars.IO.Configurations;
import co.networkery.uvbeenzaned.BaseWars.Listeners.GameListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class BaseWars extends JavaPlugin {

    List<Listener> listeners = new ArrayList<Listener>();
    @SuppressWarnings("unused")
    private GameListener g;

    public void onEnable() {
        getCommand("basewars").setExecutor(new BWCommandExecutor());
        Clock.initialize();
        Configurations.initialize(this);
        TeamManager.initialize();
        listeners.add(new GameListener(this));
    }

    public void onDisable() {
        Configurations.saveAllConfigurations();
    }

}
