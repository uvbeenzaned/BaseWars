package co.networkery.uvbeenzaned.BaseWars.Framework;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Team implements ITeam {

	String name;
	ChatColor color;
	List<String> players = new ArrayList<String>();
	List<String> arenaplayers = new ArrayList<String>();

	public Team(String n, ChatColor c) {
		setName(n);
		setColor(c);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String n) {
		name = n;
	}

	@Override
	public ChatColor getColor() {
		return color;
	}

	@Override
	public void setColor(ChatColor c) {
		color = c;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Player> getPlayers() {
		ArrayList<Player> objplayers = new ArrayList<Player>();
		for(String p : players) {
			Bukkit.getPlayer(p);
		}
		return objplayers;
	}

	@Override
	public void addPlayer(Player p) {
		players.add(p.getName());
	}

	@Override
	public void removePlayer(Player p) {
		players.remove(p.getName());
		arenaplayers.remove(p.getName());
	}
	
	@Override
	public boolean hasPlayer(Player p) {
		return players.contains(p.getName());
	}

	@Override
	public void addArenaPlayer(Player p) {
		arenaplayers.add(p.getName());
	}

	@Override
	public void removeArenaPlayer(Player p) {
		arenaplayers.remove(p.getName());
	}

	@Override
	public boolean hasArenaPlayer(Player p) {
		return arenaplayers.contains(p.getName());
	}

	@Override
	public boolean hasNoPlayers() {
		return players.isEmpty();
	}

}
