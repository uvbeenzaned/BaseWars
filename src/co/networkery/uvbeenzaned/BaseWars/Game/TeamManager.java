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

import co.networkery.uvbeenzaned.BaseWars.Chat.Chat;
import co.networkery.uvbeenzaned.BaseWars.Framework.ITeam;
import co.networkery.uvbeenzaned.BaseWars.Framework.Team;
import co.networkery.uvbeenzaned.BaseWars.IO.Configurations;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeamManager {

    static List<ITeam> teams = new ArrayList<ITeam>();

    public static void initialize() {
        for (String t : Configurations.getTeamsConfig().getKeys(false)) {
            Team team = new Team(t, ChatColor.getByChar(Configurations.getTeamsConfig().getConfigurationSection(t).getString("color")));
            teams.add(team);
        }
    }

    public static boolean registerTeam(ITeam t) {
        if (!teams.contains(t)) {
            teams.add(t);
            if (!Configurations.getTeamsConfig().contains(t.getName())) {
                Configurations.getTeamsConfig().createSection(t.getName());
                Configurations.getTeamsConfig().getConfigurationSection(t.getName()).set("color", t.getColor().toString());
            }
            Configurations.saveTeamsConfig();
            return true;
        }
        return false;
    }

    public static boolean unRegisterTeam(ITeam t) {
        if (teams.contains(t)) {
            teams.remove(t);
            Configurations.saveTeamsConfig();
            return true;
        }
        return false;
    }

    public static List<ITeam> getTeams() {
        return teams;
    }

    public static ITeam getTeamByName(String n) {
        for (ITeam t : teams) {
            if (t.getName().equalsIgnoreCase(n)) {
                return t;
            }
        }
        return null;
    }

    public static boolean teamsHavePlayer(Player p) {
        for (ITeam t : teams) {
            if (t.hasPlayer(p)) {
                return true;
            }
        }
        return false;
    }

    public static boolean teamsHaveArenaPlayer(Player p) {
        for (ITeam t : teams) {
            if (t.hasArenaPlayer(p)) {
                return true;
            }
        }
        return false;
    }

    public static ITeam getTeamHasPlayer(Player p) {
        for (ITeam t : teams) {
            if (t.hasPlayer(p)) {
                return t;
            }
        }
        return null;
    }

    public static String joinSpecificTeam(Player p, String team) {
        if (!teamsHavePlayer(p)) {
            ITeam t = getTeamByName(team);
            if (t != null) {
                if (!t.hasPlayer(p)) {
                    t.addPlayer(p);
                    return Chat.appendStrings("You have force joined team ", t.getName(), ".");
                }
            }
            return "You have tried to join a team that doesn't exist!";
        }
        return Chat.appendStrings("You are already on team ", getTeamHasPlayer(p).getName(), "!");
    }

    public static String joinTeam(Player p) {
        if (!teamsHavePlayer(p)) {
            Random r = new Random();
            r.nextInt(getTeams().size());
            for (ITeam t : getTeams()) {
                if (t.hasNoPlayers()) {
                    t.addPlayer(p);
                    return Chat.appendStrings("You have joined team ", t.getName(), ".");
                }
            }
            ITeam leastplayers = null;
            int lastamount = 0;
            for (ITeam t : getTeams()) {
                if (lastamount < t.getPlayers().size()) {
                    lastamount = t.getPlayers().size();
                    leastplayers = t;
                }
            }
            leastplayers.addPlayer(p);
        }
        return Chat.appendStrings("You are already on team ", getTeamHasPlayer(p).getName(), "!");
    }

    public static String leaveTeam(Player p) {
        if (teamsHavePlayer(p)) {
            ITeam leaveteam = getTeamHasPlayer(p);
            leaveteam.removePlayer(p);
            return Chat.appendStrings("You have left team ", leaveteam.getName(), ".");
        }
        return "You are not on a team!";
    }
}
