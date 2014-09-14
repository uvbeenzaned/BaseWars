package co.networkery.uvbeenzaned.BaseWars;

import co.networkery.uvbeenzaned.BaseWars.Chat.Chat;
import co.networkery.uvbeenzaned.BaseWars.Chat.Help;
import co.networkery.uvbeenzaned.BaseWars.Framework.ITeam;
import co.networkery.uvbeenzaned.BaseWars.Framework.Team;
import co.networkery.uvbeenzaned.BaseWars.Game.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BWCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("basewars") || cmd.getName().equalsIgnoreCase("bw")) {
                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("join")) {
                        if (args.length > 1) {
                            if (args[1].equalsIgnoreCase("help")) {
                                Chat.printStringList(p, Help.getHelpForCmd("bw.join"));
                                return true;
                            }
                            Chat.sendPlayerMsg(p, TeamManager.joinSpecificTeam(p, Chat.convertArgsToString(args, 1)), true);
                            return true;
                        } else {
                            Chat.sendPlayerMsg(p, TeamManager.joinTeam(p), true);
                            return true;
                        }
                    }
                    if (args[0].equalsIgnoreCase("leave")) {
                        if (args.length > 1) {
                            if (args[1].equalsIgnoreCase("help")) {
                                Chat.printStringList(p, Help.getHelpForCmd("bw.leave"));
                                return true;
                            }
                        }
                        Chat.sendPlayerMsg(p, TeamManager.leaveTeam(p), true);
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("team")) {
                        if (args.length < 2) {
                            Chat.printStringList(p, Help.getHelpForCmd("bw.team"));
                            return true;
                        } else {
                            if (args[1].equalsIgnoreCase("help")) {
                                Chat.printStringList(p, Help.getHelpForCmd("bw.team"));
                                return true;
                            }
                            if (args[1].equalsIgnoreCase("list")) {
                                if (args.length > 2) {
                                    if (args[2].equalsIgnoreCase("help")) {
                                        Chat.printStringList(p, Help.getHelpForCmd("bw.team.list"));
                                        return true;
                                    }
                                }
                                for (ITeam t : TeamManager.getTeams()) {
                                    Chat.sendPlayerMsg(p, t.getName(), true);
                                }
                                return true;
                            }
                            if (args[1].equalsIgnoreCase("info")) {
                                if (args.length > 2) {
                                    if (args[2].equalsIgnoreCase("help")) {
                                        Chat.printStringList(p, Help.getHelpForCmd("bw.team.info"));
                                        return true;
                                    }
                                    Chat.printStringList(p, TeamManager.getTeamByName(Chat.convertArgsToString(args, 2)).getInfo());
                                    return true;
                                }
                                Chat.printStringList(p, Help.getHelpForCmd("bw.team.info"));
                                return true;
                            }
                            if (args[1].equalsIgnoreCase("create")) {
                                if (args.length > 2) {
                                    if (args[2].equalsIgnoreCase("help")) {
                                        Chat.printStringList(p, Help.getHelpForCmd("bw.team.create"));
                                        return true;
                                    }
                                    if (ChatColor.valueOf(args[2].toUpperCase()) != null) {
                                        if (TeamManager.registerTeam(new Team(Chat.convertArgsToString(args, 3), ChatColor.valueOf(args[2].toUpperCase())))) {
                                            Chat.sendPlayerMsg(p, "Your team has been created successfully!", true);
                                            return true;
                                        }
                                        Chat.sendPlayerMsg(p, "The team you tried to create already exists!", true);
                                        return true;
                                    }
                                    Chat.sendPlayerMsg(p, "Please use a valid Bukkit color name (including underscore for a space)!", true);
                                    return true;
                                }
                                Chat.printStringList(p, Help.getHelpForCmd("bw.team.create"));
                                return true;
                            }
                            if (args[1].equalsIgnoreCase("delete")) {
                                if (args.length > 2) {
                                    if (args[2].equalsIgnoreCase("help")) {
                                        Chat.printStringList(p, Help.getHelpForCmd("bw.team.delete"));
                                        return true;
                                    }
                                    if (TeamManager.unRegisterTeam(TeamManager.getTeamByName(Chat.convertArgsToString(args, 2)))) {
                                        Chat.sendPlayerMsg(p, "The team has been deleted successfully.", true);
                                        return true;
                                    }
                                    Chat.sendPlayerMsg(p, "The team you tried to delete does not exist!", true);
                                    return true;
                                }
                                Chat.printStringList(p, Help.getHelpForCmd("bw.team.delete"));
                                return true;
                            }
                        }
                    }
                }
                // show help
                Chat.printStringList(p, Help.getHelpForCmd("bw"));
                return true;
            }
            return false;
        }
        return true;
    }
}
