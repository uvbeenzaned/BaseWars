package co.networkery.uvbeenzaned.BaseWars.Framework;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public interface ITeam {
	
	String getName();

	void setName(String n);

	ChatColor getColor();

	void setColor(ChatColor c);
	
	List<String> getPlayers();
	
	void addPlayer(Player p);
	
	void removePlayer(Player p);
}
