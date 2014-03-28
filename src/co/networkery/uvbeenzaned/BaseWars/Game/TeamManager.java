package co.networkery.uvbeenzaned.BaseWars.Game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import co.networkery.uvbeenzaned.BaseWars.Framework.ITeam;
import co.networkery.uvbeenzaned.BaseWars.Framework.Team;
import co.networkery.uvbeenzaned.BaseWars.IO.Configurations;

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
}
