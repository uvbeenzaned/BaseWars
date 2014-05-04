package co.networkery.uvbeenzaned.BaseWars.Utils;

import java.util.ArrayList;
import java.util.List;

import co.networkery.uvbeenzaned.BaseWars.IO.Configurations;

public class Help {

	public static List<String> getHelpForCmd(String section) {
		if (Configurations.getHelpConfig().getConfigurationSection(section) != null) {
			List<String> help = new ArrayList<String>();
			String description = Chat.appendStrings("Description: ", Configurations.getHelpConfig().getString(Chat.appendStrings(section, ".description")));
			String usage = "Usage: /";
			String subcommands = "Sub-commands: ";
			for(String c : section.split("\\.")) {
				usage += Chat.appendStrings(c.toLowerCase(), " ");
			}
			if (Configurations.getHelpConfig().contains(Chat.appendStrings(section, ".args"))) {
				for (String arg : Configurations.getHelpConfig().getStringList(Chat.appendStrings(section, ".args"))) {
					usage += Chat.appendStrings("<", arg, "> ");
				}
			}
			if (Configurations.getHelpConfig().getConfigurationSection(section).getKeys(false).size() > 0) {
				for (String subcommand : Configurations.getHelpConfig().getConfigurationSection(section).getKeys(false)) {
					if(!subcommand.equalsIgnoreCase("description") && !subcommand.equalsIgnoreCase("args")) {
						subcommands += Chat.appendStrings(subcommand, ", ");
					}
				}
			}
				help.add(description.trim());
				help.add(usage.trim());
			if (subcommands != "Sub-commands: ")
				help.add(subcommands.trim().substring(0, subcommands.length() - 1));
			return help;
		}
		return new ArrayList<String>();
	}
}