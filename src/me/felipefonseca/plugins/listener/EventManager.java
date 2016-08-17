package me.felipefonseca.plugins.listener;

import me.felipefonseca.plugins.Main;
import me.felipefonseca.plugins.enums.GameState;
import me.felipefonseca.plugins.enums.GameState.Move;
import me.felipefonseca.plugins.utils.ItemUtils;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EventManager implements Listener {

    private final Main plugin;

    public EventManager(Main plugin) {
        this.plugin = plugin;
    }

    public void init() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onMotdChange(ServerListPingEvent e) {
        switch (GameState.state) {
            case LOBBY:
                e.setMotd(plugin.getMessagesLoader().getLobbyState());
                break;
            case IN_GAME:
                e.setMotd(plugin.getMessagesLoader().getInGameState());
                break;
            case RESTARTING:
                e.setMotd(plugin.getMessagesLoader().getRestartingState());
                break;
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        if (plugin.getGameManager().isInLobby() && plugin.getGameManager().getPlayers().size() < plugin.getArenaManager().getMaxPlayers()) {
            plugin.getPlayerManager().setPlayer(e.getPlayer());
            plugin.getGameManager().checkStart();
        } else {
            plugin.getPlayerManager().setSpectator(e.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        if (plugin.getGameManager().isInLobby()) {
            plugin.getGameManager().getPlayers().remove(e.getPlayer());
            plugin.getArenaManager().removeSpawn(e.getPlayer());
        } else {

        }
        plugin.getGameManager().check();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityShot(EntityDamageByEntityEvent e) {
        Player affected = null;
        if (plugin.getGameManager().isInGame()) {
            if (e.getEntity() instanceof Player) {
                affected = (Player) e.getEntity();
                if (e.getDamager() instanceof Arrow) {
                    Arrow arrow = (Arrow) e.getDamager();
                    if (arrow.getShooter() instanceof Player) {
                        Player player = (Player) arrow.getShooter();
                        double health = affected.getHealth();
                        String vida = String.format("%.01f", health);
                        plugin.getServer().getScheduler().runTask(plugin, () -> {
                            plugin.getMc().sendMessage(player, plugin.getMessagesLoader().getPlayer_shot().replace("%affected%", e.getEntity().getName()).replace("%health%", vida));
                        });
                    }
                }
            }
        }
    }
    
    public String getHealth(Player player){
        double health = player.getHealth();
        String vida = String.format("%.01f", health);
        return vida;
    }

    @EventHandler
    public void onPlayerFood(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (plugin.getGameManager().isInGame()) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamageByBlock(EntityDamageByBlockEvent e) {
        if (plugin.getGameManager().isInGame()) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (plugin.getGameManager().isInGame()) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        e.setDeathMessage(null);
        e.getDrops().clear();
        if (plugin.getGameManager().isInGame()) {
            ((CraftPlayer) e.getEntity()).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
            if (e.getEntity().getKiller() instanceof Player) {
                plugin.getPlayerManager().setSpectator(e.getEntity());
                plugin.getGameManager().getPlayers().remove(e.getEntity());
                plugin.getServer().getScheduler().runTask(plugin, () -> {
                    plugin.getMc().sendBroadcast(plugin.getMessagesLoader().getPlayerDeathByPlayer().replace("%killer%", e.getEntity().getKiller().getDisplayName()).replace("%death%", e.getEntity().getDisplayName()));
                });
            } else {
                plugin.getPlayerManager().setSpectator(e.getEntity());
                plugin.getGameManager().getPlayers().remove(e.getEntity());
                plugin.getServer().getScheduler().runTask(plugin, () -> {
                    plugin.getMc().sendBroadcast(plugin.getMessagesLoader().getPlayerDeath().replace("%death%", e.getEntity().getDisplayName()));
                });
            }
            plugin.getGameManager().checkWin();
            plugin.getGameManager().check();
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (plugin.getGameManager().isInLobby() || GameState.Move.move == Move.NO_MOVE) {
            if (!(e.getFrom().getBlockZ() == e.getTo().getBlockZ()) || !(e.getFrom().getBlockX() == e.getTo().getBlockX())) {
                e.setTo(e.getFrom());
            }
        }
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e) {
        if (plugin.getGameManager().isInLobby() || plugin.getGameManager().isRestarting()) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onPlayerPickUp(PlayerPickupItemEvent e) {
        if (plugin.getGameManager().isInLobby() || plugin.getGameManager().isRestarting()) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void Consume(PlayerItemConsumeEvent event) {
        if (event.getPlayer().getItemInHand() != null) {
            if (event.getPlayer().getItemInHand().equals(ItemUtils.getGOLDEN_HEAD())) {
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1), true);
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (plugin.getGameManager().isInGame()) {
            plugin.getArenaManager().getBlocksPlaced().put(e.getBlock().getLocation(), e.getBlock());
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (plugin.getGameManager().isInGame() && plugin.getArenaManager().getBlocksPlaced().containsKey(e.getBlock().getLocation())) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (plugin.getGameManager().isInGame()) {
            switch (e.getSlotType()) {
                case ARMOR:
                    e.setCancelled(true);
                    break;
            }
        }
    }

    @EventHandler
    public void onPlayerShot(EntityShootBowEvent e) {
        if (plugin.getGameManager().isInLobby() || plugin.getGameManager().isRestarting()) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    public void unregisterAllEvents() {
        HandlerList.unregisterAll(this);
    }

}
