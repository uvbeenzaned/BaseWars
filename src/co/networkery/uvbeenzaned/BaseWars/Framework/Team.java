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

package co.networkery.uvbeenzaned.BaseWars.Framework;

import co.networkery.uvbeenzaned.BaseWars.Chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<String> getInfo() {
        List<String> info = new ArrayList<String>();
        info.add(Chat.appendStrings("Name: ", name));
        info.add(Chat.appendStrings("Color: " + color, color.toString()));
        String infoplayers = "Players: ";
        if (!hasNoPlayers()) {
            for (Player p : getPlayers()) {
                infoplayers += Chat.appendStrings(p.getName(), ", ");
            }
        } else {
            infoplayers += "none";
        }
        info.add(infoplayers);
        return info;
    }

    @Override
    public List<Player> getPlayers() {
        ArrayList<Player> objplayers = new ArrayList<Player>();
        for (String p : players) {
            objplayers.add(Bukkit.getPlayer(p));
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
