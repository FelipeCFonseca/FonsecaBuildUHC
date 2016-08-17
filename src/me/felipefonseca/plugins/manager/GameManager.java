package me.felipefonseca.plugins.manager;

import java.util.ArrayList;
import me.felipefonseca.plugins.Main;
import me.felipefonseca.plugins.enums.GameState;
import me.felipefonseca.plugins.task.LobbyTask;
import me.felipefonseca.plugins.task.RestartingTask;
import org.bukkit.entity.Player;

public class GameManager {

    private final Main plugin;

    public GameManager(Main plugin) {
        this.plugin = plugin;
    }

    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Player> spectators = new ArrayList<>();
    private boolean started;
    private boolean check;

    public void init() {
        players.clear();
        started = false;
        check = false;
    }

    public void checkStart() {
        if (started == false && plugin.getGameManager().getPlayers().size() >= plugin.getArenaManager().getMinPlayers()) {
            started = true;
            new LobbyTask(plugin).runTaskTimer(plugin, 20l, 20l);
        }
    }

    public void checkWin() {
        if (players.size() < 2) {
            plugin.getGameManager().getPlayers().stream().forEach((winner) -> {
                plugin.getServer().broadcastMessage(plugin.getMessagesLoader().getWinnerMessage().replace("%winner%", winner.getDisplayName()));
            });
            new RestartingTask(plugin).runTaskTimer(plugin, 20l, 20l);
            GameState.state = GameState.RESTARTING;
        }
    }

    public void check() {
        if (check == false && plugin.getServer().getOnlinePlayers().size() == 1 && isInGame() || plugin.getServer().getOnlinePlayers().isEmpty())   {
            check = true;
            plugin.getServer().shutdown();
        }
    }

    public String getState() {
        switch (GameState.state) {
            case LOBBY:
                return plugin.getMessagesLoader().getLobbyState();
            case IN_GAME:
                return plugin.getMessagesLoader().getInGameState();
            case RESTARTING:
                return plugin.getMessagesLoader().getRestartingState();
        }
        return "null";
    }

    public boolean isRestarting() {
        return GameState.state == GameState.RESTARTING;
    }

    public boolean isInGame() {
        return GameState.state == GameState.IN_GAME;
    }

    public boolean isInLobby() {
        return GameState.state == GameState.LOBBY;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Player> getSpectators() {
        return spectators;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
    
    
    

}
