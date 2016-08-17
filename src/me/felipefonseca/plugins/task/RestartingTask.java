package me.felipefonseca.plugins.task;

import me.felipefonseca.plugins.Main;
import me.felipefonseca.plugins.utils.Tools;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.scheduler.BukkitRunnable;

public class RestartingTask extends BukkitRunnable {
    
    private final Main plugin;
    
    public RestartingTask(Main plugin) {
        this.plugin = plugin;
    }
    
    private int count = 15;
    
    @Override
    public void run() {
        plugin.getPlayerManager().getBoard().text(13, plugin.getMessagesLoader().getScoreboardLine2().replace("%time%", "" + Tools.transform(count)));
        plugin.getGameManager().getPlayers().stream().forEach((winner) -> {
            Tools.firework(winner.getLocation(), Color.AQUA, Color.GREEN, Color.RED, FireworkEffect.Type.CREEPER);
        });
        switch (count) {
            case 5:
                plugin.getServer().getOnlinePlayers().stream().forEach((players) -> {
                    plugin.getPlayerManager().sendToServer(players);
                });
            case 0:
                plugin.getServer().shutdown();
                this.cancel();
                break;
        }
        
        --count;
    }
    
}
