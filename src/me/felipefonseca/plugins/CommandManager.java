package me.felipefonseca.plugins;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor {

    private final Main plugin;

    public CommandManager(Main plugin) {
        this.plugin = plugin;
    }

    public void init() {
        plugin.getCommand("builduhc").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        if (cmd.getName().equalsIgnoreCase("builduhc")) {
            if (player == null) {
                plugin.getMc().sendMessageToSender(sender, "&cWhat hueá hermano, you ar not a player.");
            } else if (player.hasPermission("BuildUHC.Commmand.Setup")) {
                if (args.length < 1) {
                    plugin.getMc().sendMessageToSender(sender, "&cError: &7Please use &e/builduhc setmin/setmax/setpos/setmap/start");
                } else {
                    switch (args[0].toLowerCase()) {
                        case "setmin":
                            if (args.length < 1) {
                                player.sendMessage(ChatColor.RED + "/builduhc setmin CANT");
                            } else {
                                int min = Integer.valueOf(args[1]);
                                plugin.getMc().sendMessageToSender(sender, "&7Min Players: &e" + min);
                                plugin.getArenaConfiguration().getArenaConfig().set("min", min);
                                plugin.getArenaConfiguration().save();
                            }
                            break;
                        case "setmax":
                            if (args.length < 1) {
                                player.sendMessage(ChatColor.RED + "/builduhc setmax CANT");
                            } else {
                                int max = Integer.valueOf(args[1]);
                                plugin.getMc().sendMessageToSender(sender, "&7Max Players: &e" + max);
                                plugin.getArenaConfiguration().getArenaConfig().set("max", max);
                                plugin.getArenaConfiguration().save();
                            }
                            break;
                        case "setmap":
                            if (args.length < 2) {
                                player.sendMessage(ChatColor.RED + "/builduhc MAPNAME AUTHOR");
                            } else {
                                String mapName = args[1];
                                String authorName = args[2];
                                plugin.getLangConfiguration().getLangConfiguration().set("BuildUHC.Game.arenaName", mapName);
                                plugin.getLangConfiguration().getLangConfiguration().set("BuildUHC.Game.authorName", authorName);
                                plugin.getLangConfiguration().save();
                                plugin.getMc().sendMessageToSender(sender, "&7Map: &e" + mapName + " &7, author: &e" + authorName);
                            }
                            break;
                        case "setpos":
                            plugin.getArenaManager().addSpawn(player);
                            break;
                        case "start":
                            if (plugin.isEditMode() == true) {
                                plugin.getMc().sendMessageToSender(sender, "&7Editmode enabled.");
                            } else {
                                plugin.getGameManager().checkStart();
                            }
                            break;
                    }
                }
            } else {
                plugin.getMc().sendMessageToSender(sender, "&cPlugin made by &aFelipe Fonseca&c.");
            }
        }
        return false;
    }

}
