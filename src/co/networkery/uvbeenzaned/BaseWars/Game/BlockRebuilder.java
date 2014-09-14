package co.networkery.uvbeenzaned.BaseWars.Game;

import co.networkery.uvbeenzaned.BaseWars.Events.ClockTickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class BlockRebuilder implements Listener{

    public BlockRebuilder(Plugin p) {
        p.getServer().getPluginManager().registerEvents(this, p);
    }

    @EventHandler
    public void rebuildBlocks(ClockTickEvent e) {

    }
}
