package co.networkery.uvbeenzaned.BaseWars.Events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ClockTickEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private int centralclockelapsedtime;

    public ClockTickEvent(int elapsed) {
        centralclockelapsedtime = elapsed;
    }

    public int getCentralClockElapsedTime() {
        return centralclockelapsedtime;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
