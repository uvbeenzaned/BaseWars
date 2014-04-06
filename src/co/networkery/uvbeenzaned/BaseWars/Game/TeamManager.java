package co.networkery.uvbeenzaned.BaseWars.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import co.networkery.uvbeenzaned.BaseWars.Framework.ITeam;
import co.networkery.uvbeenzaned.BaseWars.Framework.Team;
import co.networkery.uvbeenzaned.BaseWars.IO.Configurations;

public class TeamManager {

	static StringBuilder sb = new StringBuilder();
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
			if (t.getName().equals(n)) {
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
			if(t.hasArenaPlayer(p)) {
				return t;
			}
		}
		return null;
	}

	public static String joinTeam(Player p) {
		if (!teamsHavePlayer(p)) {
			Random r = new Random();
			r.nextInt(getTeams().size());
			for (ITeam t : getTeams()) {
				if (t.hasNoPlayers()) {
					t.addPlayer(p);
					sb = new StringBuilder().append("You have joined team ").append(t.getColor()).append(t.getName()).append(".");
					return sb.toString();
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
		} else {
			return "You are already on a team!";
		}
		return "Error!";
	}
	
	public static String leaveTeam(Player p) {
		if(teamsHavePlayer(p)) {
			ITeam leaveteam = getTeamHasPlayer(p);
			leaveteam.removePlayer(p);
			sb = new StringBuilder().append("You have left team ").append(leaveteam.getColor()).append(leaveteam.getName()).append(".");
			return sb.toString();
		} else {
			return "You are not on a team!";
		}
	}
}
