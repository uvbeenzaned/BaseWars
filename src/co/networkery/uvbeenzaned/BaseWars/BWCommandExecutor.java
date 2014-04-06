package co.networkery.uvbeenzaned.BaseWars;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.networkery.uvbeenzaned.BaseWars.Game.TeamManager;

public class BWCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (cmd.getName().equals("basewars")) {
				if (args[0].equals("join")) {
					TeamManager.joinTeam(p);
					return true;
				}
				if(args[0].equals("leave")) {
					TeamManager.leaveTeam(p);
				}
			}
			return false;
		}
		return true;
	}
}
