package me.felipefonseca.plugins.task;

import me.felipefonseca.plugins.Main;
import me.felipefonseca.plugins.enums.GameState;
import me.felipefonseca.plugins.utils.Tools;
import org.bukkit.GameMode;
import org.bukkit.Sound;

import org.bukkit.scheduler.BukkitRunnable;

public class LobbyTask extends BukkitRunnable {
    
    private final Main plugin;
    
    public LobbyTask(Main plugin) {
        this.plugin = plugin;
    }
    
    private int count = 20;
    
    @Override
    public void run() {
        plugin.getGameManager().getPlayers().stream().forEach((players) -> {
            players.setLevel(count);
        });
        plugin.getPlayerManager().getBoard().text(13, plugin.getMessagesLoader().getScoreboardLine2().replace("%time%", "" + Tools.transform(count)));
        if (count == 20) {
            plugin.getGameManager().getPlayers().stream().forEach((players) -> {
                plugin.getPlayerManager().setKit(players);
                players.sendMessage(plugin.getMessagesLoader().getPrefix() + plugin.getMessagesLoader().getSaveInventory());
            });
        } else if (count > 0 && count <= 5) {
            plugin.getGameManager().getPlayers().stream().map((players) -> {
                plugin.getMc().sendTitle(players, plugin.getMessagesLoader().getStartingMessage().replace("%count%", "" + count), "", 20, 20, 40);
                return players;
            }).forEach((players) -> {
                players.playSound(players.getLocation(), Sound.CLICK, 1F, 1F);
            });
        } else if (count == 1) {
            plugin.getGameManager().getPlayers().stream().forEach((players) -> {
                players.sendMessage(plugin.getMessagesLoader().getPrefix() + plugin.getMessagesLoader().getGoodluck());
                plugin.getMc().sendTitle(players, plugin.getMessagesLoader().getGoodluck(), "", 20, 20, 20);
            });
        } else if (count == 0) {
            if (plugin.getGameManager().getPlayers().size() >= 2) {
                plugin.getGameManager().getPlayers().stream().forEach((players) -> {
                    players.setGameMode(GameMode.SURVIVAL);
                });
                new GameTask(plugin).runTaskTimer(plugin, 20l, 20l);
                GameState.state = GameState.IN_GAME;
                this.cancel();
            } else {
                if(plugin.getGameManager().isStarted() == true){
                    plugin.getGameManager().setStarted(false);
                }
                GameState.state = GameState.LOBBY;
                this.cancel();
            }
            this.cancel();
        }
        
        --count;
        
    }
    
}
