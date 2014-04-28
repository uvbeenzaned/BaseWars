package co.networkery.uvbeenzaned.BaseWars.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import co.networkery.uvbeenzaned.BaseWars.Framework.ITeam;
import co.networkery.uvbeenzaned.BaseWars.Framework.Team;
import co.networkery.uvbeenzaned.BaseWars.IO.Configurations;
import co.networkery.uvbeenzaned.BaseWars.Utils.Chat;

public class TeamManager {

	static List<ITeam> teams = new ArrayList<ITeam>();

	public static void initialize() {
		for (String t : Configurations.getTeamsConfig().getKeys(false)) {
			Team team = new Team(t, ChatColor.valueOf(Configurations.getTeamsConfig().getConfigurationSection(t).getString("color")));
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
			return true;
		}
		return false;
	}

	public static boolean unRegisterTeam(ITeam t) {
		if (teams.contains(t)) {
			teams.remove(t);
			if (Configurations.getTeamsConfig().contains(t.getName())) {
				Configurations.getTeamsConfig().createSection(t.getName());
				Configurations.getTeamsConfig().getConfigurationSection(t.getName()).set("color", t.getColor().toString());
			}
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
			return t.hasPlayer(p);
		}
		return false;
	}

	public static boolean teamsHaveArenaPlayer(Player p) {
		for (ITeam t : teams) {
			return t.hasArenaPlayer(p);
		}
		return false;
	}

	public static ITeam getTeamHasPlayer(Player p) {
		for (ITeam t : teams) {
			if (t.hasArenaPlayer(p)) {
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
