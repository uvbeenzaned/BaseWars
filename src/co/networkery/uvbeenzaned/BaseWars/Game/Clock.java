package co.networkery.uvbeenzaned.BaseWars.Game;

import co.networkery.uvbeenzaned.BaseWars.Events.ClockTickEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class Clock{

    private static Plugin p = null;
    private static BukkitTask task = null;
    private static ClockTickEvent cte = new ClockTickEvent(0);
    private static boolean running = false;
    private static int elapsedtime = 0;

    public static void initialize() {
        schedule();
    }

    private static void schedule() {
        task = p.getServer().getScheduler().runTaskTimerAsynchronously(p, new Runnable() {
            public void run() {
                if(isRunning()) {
                    elapsedtime += 50;
                    cte = new ClockTickEvent(elapsedtime);
                    Bukkit.getServer().getPluginManager().callEvent(cte);
                } else {
                    task.cancel();
                }
            }
        }, 0L, 1L);
    }

    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean b) {
        running = b;
        if(isRunning()) {
            schedule();
        }
    }
}
