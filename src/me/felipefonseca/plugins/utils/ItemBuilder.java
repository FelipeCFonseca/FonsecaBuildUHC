
package me.felipefonseca.plugins.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {

    private final ItemStack itemStack;

    public ItemBuilder() {
        itemStack = new ItemStack(Material.AIR);
    }

    public ItemBuilder(Material type) {
        itemStack = new ItemStack(type);
    }

    public ItemBuilder setType(Material type) {
        itemStack.setType(type);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setDisplayName(String displayName) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setLores(List<String> lores) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setLore(lores);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setLores(String... lores) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        List<String> loresList = new ArrayList<>();
        for (String lore : lores) {
            loresList.add(lore);
        }
        itemMeta.setLore(loresList);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag itemFlag) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.addItemFlags(itemFlag);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag... itemFlags) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.addItemFlags(itemFlags);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment ench, int level, boolean ignoreLevelRestriction) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.addEnchant(ench, level, ignoreLevelRestriction);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }
    
    public ItemBuilder setUnbreakable(){
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.spigot().setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        this.itemStack.setItemMeta(meta);
        return this;
    }


    public ItemStack build() {
        return this.itemStack;
    }
}