package co.networkery.uvbeenzaned.BaseWars.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import co.networkery.uvbeenzaned.BaseWars.Framework.ITeam;
import co.networkery.uvbeenzaned.BaseWars.Game.TeamManager;

public class Chat {

	static StringBuilder sb = new StringBuilder();
	static final String format = sb.append(ChatColor.GREEN).append("[").append(ChatColor.GRAY).append("BaseWars").append(ChatColor.GREEN).append("] ").toString();

	public static void sendAllTeamsMsg(String msg, boolean formatted) {
		String finalmsg = msg;
		if (formatted) {
			sb = new StringBuilder();
			finalmsg = sb.append(format).append(processMsg(msg)).toString();
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
		}
		for (Player p : t.getPlayers()) {
			p.sendMessage(finalmsg);
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
}
