package me.felipefonseca.plugins;

import java.io.IOException;
import me.felipefonseca.plugins.listener.EventManager;
import me.felipefonseca.plugins.manager.ArenaManager;
import me.felipefonseca.plugins.manager.GameManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.felipefonseca.plugins.enums.GameState;
import me.felipefonseca.plugins.manager.PlayerManager;
import me.felipefonseca.plugins.manager.config.ArenaConfiguration;
import me.felipefonseca.plugins.manager.config.LangConfiguration;
import me.felipefonseca.plugins.utils.ItemUtils;
import me.felipefonseca.plugins.utils.MessageController;
import me.felipefonseca.plugins.utils.MessagesLoader;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private final ArenaConfiguration ac;
    private final LangConfiguration lc;

    private final EventManager em;
    private final ArenaManager am;
    private final GameManager gm;
    private final PlayerManager pm;

    private final MessageController mc;
    private final MessagesLoader msg;

    private final CommandManager cm;
    
    private World arenaWorld;

    public Main() {
        this.ac = new ArenaConfiguration(this);
        this.lc = new LangConfiguration(this);
        this.am = new ArenaManager(this);
        this.cm = new CommandManager(this);
        this.em = new EventManager(this);
        this.gm = new GameManager(this);
        this.pm = new PlayerManager(this);
        this.msg = new MessagesLoader(this);
        this.mc = new MessageController(this);
    }

    private boolean editMode;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        editMode = getConfig().getBoolean("editMode");
        arenaWorld = getServer().getWorld(getConfig().getString("world"));
        try {
            ac.init();
            lc.init();
        } catch (IOException | InvalidConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        cm.init();
        msg.init();
        if (editMode == true) {

        } else if (editMode == false) {
            getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
            ItemUtils.init();
            am.init();
            em.init();
            arenaWorld.setAutoSave(false);
            GameState.state = GameState.LOBBY;
        }
        getLogger().log(Level.INFO, "BH: Enabled");
    }

    @Override
    public void onDisable() {
        if (editMode == false) {
            em.unregisterAllEvents();
        }
        getLogger().log(Level.INFO, "BH: Disabled");
    }

    public ArenaManager getArenaManager() {
        return am;
    }

    public GameManager getGameManager() {
        return gm;
    }

    public PlayerManager getPlayerManager() {
        return pm;
    }

    public MessagesLoader getMessagesLoader() {
        return msg;
    }

    public ArenaConfiguration getArenaConfiguration() {
        return ac;
    }

    public LangConfiguration getLangConfiguration() {
        return lc;
    }

    public MessageController getMc() {
        return mc;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public World getArenaWorld() {
        return arenaWorld;
    }

    
}
