package me.felipefonseca.plugins.task;

import me.felipefonseca.plugins.Main;
import me.felipefonseca.plugins.utils.Tools;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTask extends BukkitRunnable {

    private final Main plugin;

    public GameTask(Main plugin) {
        this.plugin = plugin;
    }

    private int count = 300;

    @Override
    public void run() {
        plugin.getPlayerManager().getBoard().text(13, plugin.getMessagesLoader().getScoreboardLine2().replace("%time%", "" + Tools.transform(count)));
        if (plugin.getGameManager().isRestarting()) {
            this.cancel();
        }

        if (count == 0) {
            new RestartingTask(plugin).runTaskTimer(plugin, 20l, 20l);
            this.cancel();
        }
        --count;
    }

}
