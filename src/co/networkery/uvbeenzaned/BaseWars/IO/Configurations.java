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

package co.networkery.uvbeenzaned.BaseWars.IO;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Configurations {

    private static Configuration config;
    private static Configuration teamsConfig;
    private static Configuration helpConfig;

    public static void initialize(JavaPlugin pl) {
        setConfig(new Configuration(pl, "config.yml"));
        setTeamsConfig(new Configuration(pl, "teams.yml"));
        setHelpConfig(new Configuration(pl, "help.yml"));
        config.saveDefaultConfig();
        teamsConfig.saveDefaultConfig();
        helpConfig.saveDefaultConfig();
    }

    public static void reloadAllConfigurations() {
        config.reloadConfig();
        teamsConfig.reloadConfig();
        helpConfig.reloadConfig();
    }

    public static void saveAllConfigurations() {
        config.saveConfig();
        teamsConfig.saveConfig();
    }

    public static void saveConfig() {
        config.saveConfig();
    }

    public static FileConfiguration getConfig() {
        return config.getConfig();
    }

    public static void setConfig(Configuration config) {
        Configurations.config = config;
    }

    public static void saveTeamsConfig() {
        teamsConfig.saveConfig();
    }

    public static FileConfiguration getTeamsConfig() {
        return teamsConfig.getConfig();
    }

    public static void setTeamsConfig(Configuration teamsConfig) {
        Configurations.teamsConfig = teamsConfig;
    }

    public static void saveHelpConfig() {
        helpConfig.saveConfig();
    }

    public static FileConfiguration getHelpConfig() {
        return helpConfig.getConfig();
    }

    public static void setHelpConfig(Configuration helpConfig) {
        Configurations.helpConfig = helpConfig;
    }

}
