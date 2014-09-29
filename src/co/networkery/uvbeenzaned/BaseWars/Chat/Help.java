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

package co.networkery.uvbeenzaned.BaseWars.Chat;

import co.networkery.uvbeenzaned.BaseWars.IO.Configurations;

import java.util.ArrayList;
import java.util.List;

public class Help {

    public static List<String> getHelpForCmd(String section) {
        if (Configurations.getHelpConfig().getConfigurationSection(section) != null) {
            List<String> help = new ArrayList<String>();
            String description = Chat.appendStrings("Description: ", Configurations.getHelpConfig().getString(Chat.appendStrings(section, ".description")));
            String usage = "Usage: /";
            String subcommands = "Sub-commands: ";
            for (String c : section.split("\\.")) {
                usage += Chat.appendStrings(c.toLowerCase(), " ");
            }
            if (Configurations.getHelpConfig().contains(Chat.appendStrings(section, ".args"))) {
                for (String arg : Configurations.getHelpConfig().getStringList(Chat.appendStrings(section, ".args"))) {
                    usage += Chat.appendStrings("<", arg, "> ");
                }
            }
            if (Configurations.getHelpConfig().getConfigurationSection(section).getKeys(false).size() > 0) {
                for (String subcommand : Configurations.getHelpConfig().getConfigurationSection(section).getKeys(false)) {
                    if (!subcommand.equalsIgnoreCase("description") && !subcommand.equalsIgnoreCase("args")) {
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