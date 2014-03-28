package co.networkery.uvbeenzaned.BaseWars.Listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class GameListener implements Listener {

	public GameListener(JavaPlugin p) {
		p.getServer().getPluginManager().registerEvents(this, p);
	}
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent e) {
		for(Block b : e.blockList()) {
			b.getDrops().clear();
		}
	}
	
}
