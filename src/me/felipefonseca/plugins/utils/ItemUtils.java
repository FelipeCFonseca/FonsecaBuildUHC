package me.felipefonseca.plugins.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemUtils {

    private static ItemStack HELMET;
    private static ItemStack CHESTPLATE;
    private static ItemStack LEGGINGS;
    private static ItemStack BOOTS;

    private static ItemStack DIAMOND_SWORD;
    private static ItemStack BOW;
    private static ItemStack ROD;

    private static ItemStack LAVA;
    private static ItemStack WATER;

    private static ItemStack PICKAXE;
    private static ItemStack AXE;
    private static ItemStack BLOCKS1;
    private static ItemStack BLOCKS2;
    private static ItemStack ARROWS;

    private static ItemStack GOLDEN_APPLE;
    private static ItemStack GOLDEN_HEAD;

    public static void init() {
        HELMET = new ItemBuilder(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true).build();
        CHESTPLATE = new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true).build();
        LEGGINGS = new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true).build();
        BOOTS = new ItemBuilder(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, true).build();

        DIAMOND_SWORD = new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 3, true).build();
        BOW = new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 3, true).build();
        ROD = new ItemBuilder(Material.FISHING_ROD).build();

        LAVA = new ItemBuilder(Material.LAVA_BUCKET).build();
        WATER = new ItemBuilder(Material.WATER_BUCKET).build();

        PICKAXE = new ItemStack(Material.DIAMOND_PICKAXE);
        AXE = new ItemStack(Material.DIAMOND_AXE);
        BLOCKS1 = new ItemStack(Material.WOOD, 64);
        BLOCKS2 = new ItemStack(Material.COBBLESTONE, 64);
        ARROWS = new ItemStack(Material.ARROW, 64);

        GOLDEN_APPLE = new ItemBuilder(Material.GOLDEN_APPLE).setAmount(6).build();
        GOLDEN_HEAD = new ItemBuilder(Material.GOLDEN_APPLE).setAmount(3).setDisplayName(MessagesLoader.getGoldenHead()).build();
    }

    public static void setArmor(Player player) {
        player.getInventory().setHelmet(HELMET);
        player.getInventory().setChestplate(CHESTPLATE);
        player.getInventory().setLeggings(LEGGINGS);
        player.getInventory().setBoots(BOOTS);
    }

    public static void setKit(Player player) {
        player.getInventory().setHelmet(HELMET);
        player.getInventory().setChestplate(CHESTPLATE);
        player.getInventory().setLeggings(LEGGINGS);
        player.getInventory().setBoots(BOOTS);
        player.getInventory().addItem(DIAMOND_SWORD);
        player.getInventory().addItem(BOW);
        player.getInventory().addItem(ROD);
        player.getInventory().addItem(LAVA);
        player.getInventory().addItem(WATER);
        player.getInventory().addItem(GOLDEN_APPLE);
        player.getInventory().addItem(GOLDEN_HEAD);
        player.getInventory().addItem(PICKAXE);
        player.getInventory().addItem(AXE);
        player.getInventory().addItem(BLOCKS1);
        player.getInventory().addItem(BLOCKS2);
        player.getInventory().addItem(ARROWS);
        player.getInventory().addItem(LAVA);
        player.getInventory().addItem(WATER);
    }

    public static ItemStack getGOLDEN_HEAD() {
        return GOLDEN_HEAD;
    }
    
    

}
