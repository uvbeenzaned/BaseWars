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

package co.networkery.uvbeenzaned.BaseWars.Chat;

import co.networkery.uvbeenzaned.BaseWars.Framework.ITeam;
import co.networkery.uvbeenzaned.BaseWars.Game.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class Chat {

    static StringBuilder sb = new StringBuilder();
    static final String format = sb.append(ChatColor.GREEN).append("[").append(ChatColor.GRAY).append("BaseWars").append(ChatColor.GREEN).append("] ").append(ChatColor.DARK_PURPLE).toString();

    public static void sendPlayerMsg(Player p, String msg, boolean formatted) {
        String finalmsg = msg;
        if (formatted) {
            sb = new StringBuilder();
            finalmsg = sb.append(format).append(msg).toString();
        } else {
            finalmsg = processMsg(msg);
        }
        p.sendMessage(finalmsg);
    }

    public static void sendAllTeamsMsg(String msg, boolean formatted) {
        String finalmsg = msg;
        if (formatted) {
            sb = new StringBuilder();
            finalmsg = sb.append(format).append(processMsg(msg)).toString();
        } else {
            finalmsg = processMsg(msg);
        }
        for (ITeam t : TeamManager.getTeams()) {
            for (Player p : t.getPlayers()) {
                p.sendMessage(finalmsg);
            }
        }
    }

    public static void sendTeamMsg(ITeam t, String msg, boolean formatted) {
        String finalmsg = msg;
        if (formatted) {
            sb = new StringBuilder();
            finalmsg = sb.append(format).append(processMsg(msg)).toString();
        } else {
            finalmsg = processMsg(msg);
        }
        for (Player p : t.getPlayers()) {
            p.sendMessage(finalmsg);
        }
    }

    public static void printStringList(Player p, List<String> h) {
        for (String help : h) {
            if (help != "" && help != null)
                sendPlayerMsg(p, help, true);
        }
    }

    private static String processMsg(String msg) {
        boolean endswithperiod = msg.endsWith(".");
        if (endswithperiod)
            msg = msg.substring(0, (msg.length() - 3));
        String[] msgarr = msg.split(" ");
        sb = new StringBuilder();
        for (ITeam t : TeamManager.getTeams()) {
            for (String s : msgarr) {
                if (s.equalsIgnoreCase(t.getName())) {
                    sb.append(t.getColor()).append(t.getName()).append(ChatColor.RESET).append(" ");
                }
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    if (s.equalsIgnoreCase(p.getName())) {
                        sb.append(t.getColor()).append(p.getName()).append(ChatColor.RESET).append(" ");
                    }
                }
            }
        }
        String trimmed = sb.toString().trim();
        if (endswithperiod) {
            sb = new StringBuilder();
            sb.append(trimmed).append(".");
        }
        return sb.toString();
    }

    public static String appendStrings(String... strings) {
        sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static String convertArgsToString(String[] args, int startpoint) {
        String aname = "";
        for (int i = startpoint; i < args.length; i++) {
            aname = appendStrings(aname, args[i], " ");
        }
        return aname.trim();
    }
}
