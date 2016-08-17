package me.felipefonseca.plugins.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import me.felipefonseca.plugins.Main;
import me.felipefonseca.plugins.utils.Tools;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ArenaManager {

    private final Main plugin;

    public ArenaManager(Main plugin) {
        this.plugin = plugin;
    }

    private final ArrayList<Location> spawnList = new ArrayList<>();
    private final HashMap<Location, Block> blocksPlaced = new HashMap<>();

    private final Random rand = new Random();

    private int maxPlayers;
    private int minPlayers;
    
    private String arenaName;
    private String authorName;

    private int spawn;

    public void addPlayer(Player player) {
        if (plugin.getGameManager().getPlayers().contains(player)) {
            plugin.getGameManager().getPlayers().remove(player);
            plugin.getGameManager().getPlayers().add(player);
        } else {
            plugin.getGameManager().getPlayers().add(player);
        }
    }
    
    public void addSpectator(Player player){
        if (plugin.getGameManager().getSpectators().contains(player)) {
            plugin.getGameManager().getSpectators().remove(player);
            plugin.getGameManager().getSpectators().add(player);
        } else {
            plugin.getGameManager().getSpectators().add(player);
        }
    }

    public void init() {
        maxPlayers = plugin.getArenaConfiguration().getArenaConfig().getInt("max");
        minPlayers = plugin.getArenaConfiguration().getArenaConfig().getInt("min");
        arenaName = plugin.getLangConfiguration().getLangConfiguration().getString("BuildUHC.Game.arenaName");
        authorName = plugin.getLangConfiguration().getLangConfiguration().getString("BuildUHC.Game.authorName");
        loadSpawn();
        loadWorld(plugin.getArenaWorld());
    }
    
    
    public void loadWorld(World world){
        world.setTime(6000);
        world.setPVP(true);
        world.setDifficulty(Difficulty.NORMAL);
        world.setStorm(false);
        world.setGameRuleValue("naturalRegeneration", "false");
        world.setGameRuleValue("doDaylightCycle", "false");
    }

    public void teleport(Player player) {
        int rd = rand.nextInt(spawnList.size());
        player.teleport(spawnList.get(rd));
        spawnList.remove(rd);
    }

    public void addSpawn(Player player) {
        String l = Tools.locationToString(player.getLocation());
        spawn = spawn + 1;
        plugin.getArenaConfiguration().getArenaConfig().set("pos." + spawn, l);
        plugin.getArenaConfiguration().save();
        spawnList.clear();
        for (int i = 1; i <= maxPlayers; i++) {
            spawnList.add(Tools.stringToLoc(plugin.getArenaConfiguration().getArenaConfig().getString("pos." + i)));
        }
        plugin.getMc().sendMessage(player, "&7Spawn: &e" + spawn);
    }

    public void loadSpawn() {
        spawnList.clear();
        try {
            for (int i = 1; i <= maxPlayers; i++) {
                spawnList.add(Tools.stringToLoc(plugin.getArenaConfiguration().getArenaConfig().getString("pos." + i)));
            }
        } catch (Exception e) {
        }
    }

    public void removeSpawn(Player player) {
        spawnList.add(player.getLocation());
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public ArrayList<Location> getSpawns() {
        return spawnList;
    }

    public String getArenaName() {
        return arenaName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public HashMap<Location, Block> getBlocksPlaced() {
        return blocksPlaced;
    }

    
    
}
