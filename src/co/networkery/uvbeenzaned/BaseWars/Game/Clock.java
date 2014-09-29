/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Networkery
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package co.networkery.uvbeenzaned.BaseWars.Game;

import co.networkery.uvbeenzaned.BaseWars.Events.ClockTickEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class Clock {

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
                if (isRunning()) {
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
        if (isRunning()) {
            schedule();
        }
    }
}
