package me.felipefonseca.plugins.manager;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.felipefonseca.plugins.Main;
import me.felipefonseca.plugins.utils.ItemUtils;
import me.felipefonseca.plugins.utils.ScoreboardUtil;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerManager {

    private final Main plugin;

    public PlayerManager(Main plugin) {
        this.plugin = plugin;
    }

    private ScoreboardUtil board;

    public void setScoreboardLobby(Player player) {
        board = new ScoreboardUtil(plugin.getMessagesLoader().getScoreboardTitle());
        new BukkitRunnable() {
            @Override
            public void run() {
                board.text(15, " ");
                board.text(14, plugin.getMessagesLoader().getScoreboardLine1());
                //board.text(13, "Time" //scoreboard2);
                board.text(12, "§3 ");
                board.text(11, plugin.getMessagesLoader().getScoreboardLine3());
                board.text(10, plugin.getMessagesLoader().getScoreboardLine4().replace("%online%", "" + plugin.getGameManager().getPlayers().size()).replace("%max%", "" + plugin.getArenaManager().getMaxPlayers()));
                board.text(9, "§a ");
                board.text(8, plugin.getMessagesLoader().getScoreboardline5());
                board.text(7, plugin.getMessagesLoader().getScoreboardline6().replace("%state%", plugin.getGameManager().getState()));
                board.text(6, "§c ");
                board.text(5, plugin.getMessagesLoader().getScoreboardline7());
                board.text(4, plugin.getMessagesLoader().getScoreboardline8().replace("%author%", plugin.getArenaManager().getAuthorName()));
                board.text(3, plugin.getMessagesLoader().getScoreboardline9().replace("%map%", plugin.getArenaManager().getArenaName()));
                board.text(2, "§d ");
                board.text(1, plugin.getMessagesLoader().getScoreboardline10());
                board.build(player);
            }
        }.runTaskTimer(plugin, 20l, 20l);
    }

    public void setSpectator(Player player) {
        plugin.getArenaManager().addSpectator(player);
        setCleanPlayer(player, GameMode.SPECTATOR);
    }

    public void setPlayer(Player player) {
        plugin.getArenaManager().addPlayer(player);
        setScoreboardLobby(player);
        setCleanPlayer(player, GameMode.ADVENTURE);
        plugin.getArenaManager().teleport(player);
        player.sendMessage(plugin.getMessagesLoader().getPrefix() + plugin.getMessagesLoader().getWelcomeMessage());
        plugin.getMc().sendTitle(player, plugin.getMessagesLoader().getWelcomeTitle(), plugin.getMessagesLoader().getWelcomeSubtitle(), 10, 10, 10);
    }

    public void setKit(Player player) {
        setCleanPlayer(player, GameMode.ADVENTURE);
        ItemUtils.setKit(player);
    }

    public void setCleanPlayer(Player player, GameMode gameMode) {
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);
        player.setExp(0);
        player.setTotalExperience(0);
        player.setLevel(0);
        player.setFireTicks(0);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setGameMode(gameMode);
        player.getActivePotionEffects().stream().forEach((effect) -> {
            player.removePotionEffect(effect.getType());
        });
    }

    public void sendToServer(Player player) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(plugin.getConfig().getString("server"));

        } catch (IOException ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }

    public ScoreboardUtil getBoard() {
        return board;
    }

}
