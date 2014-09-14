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
