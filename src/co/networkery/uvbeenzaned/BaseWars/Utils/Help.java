package co.networkery.uvbeenzaned.BaseWars.Utils;

import java.util.ArrayList;
import java.util.List;

import co.networkery.uvbeenzaned.BaseWars.IO.Configurations;

public class Help {

	public static List<String> getHelpForCmd(String section) {
		if (Configurations.getHelpConfig().getConfigurationSection(section) != null) {
			List<String> help = new ArrayList<String>();
			String description = Chat.appendStrings("Description: ", Configurations.getHelpConfig().getString(Chat.appendStrings(section, ".description")));
			String usage = "Usage: /bw";
			String subcommands = "Sub-commands:";
			if (Configurations.getHelpConfig().contains(Chat.appendStrings(section, ".args")) && Configurations.getHelpConfig().getConfigurationSection(Chat.appendStrings(section, ".args")) != null) {
				for (String arg : Configurations.getHelpConfig().getStringList(Chat.appendStrings(section, ".args"))) {
					Chat.appendStrings(usage, " <", arg, ">");
				}
			}
			if (Configurations.getHelpConfig().getConfigurationSection(section).getKeys(false).size() > 0) {
				for (String subcommand : Configurations.getHelpConfig().getConfigurationSection(section).getKeys(false)) {
					if (subcommand != "description") {
						Chat.appendStrings(" ", subcommand);
					}
				}
			}
			if (description != Chat.appendStrings("Description: ", Configurations.getHelpConfig().getString(section)))
				help.add(description);
			if (usage != "Usage: /bw")
				help.add(usage);
			if (subcommands != "Sub-commands:")
				help.add(subcommands);
			help.add("lol");
			return help;
		}
		return new ArrayList<String>();
	}
}