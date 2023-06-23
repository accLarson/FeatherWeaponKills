package com.zerek.featherweaponkills.listeners;

import com.zerek.featherweaponkills.FeatherWeaponKills;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlayerDeathListener implements Listener {

    private final FeatherWeaponKills plugin;
    private final List<String> WEAPONS = Arrays.asList(
            "BOW", "CROSSBOW", "TRIDENT",
            "WOODEN_SWORD", "STONE_SWORD", "IRON_SWORD", "GOLDEN_SWORD", "DIAMOND_SWORD", "NETHERITE_SWORD",
            "WOODEN_AXE", "STONE_AXE", "IRON_AXE", "GOLDEN_AXE", "DIAMOND_AXE", "NETHERITE_AXE");


    public PlayerDeathListener(FeatherWeaponKills plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath (PlayerDeathEvent event) {
        if (event.getPlayer().getKiller() != null) {
            Player killer = event.getPlayer().getKiller();
            ItemStack itemStack = killer.getInventory().getItemInMainHand();

            if (WEAPONS.contains(itemStack.getType().toString())) {

                if (itemStack.lore() != null) {

                    List<Component> newLore = itemStack.lore();

                    for (int i = 0; i < newLore.size(); i++) {
                        Component component = newLore.get(i);
                        if (MiniMessage.miniMessage().serialize(component).startsWith("Weapon Kills: ")) {
                            String string = MiniMessage.miniMessage().serialize(component).trim();
                            int kills = Integer.parseInt(string.substring(string.lastIndexOf(" ") + 1)) + 1;
                            newLore.set(i, Component.text("Weapon Kills: " + kills));
                            itemStack.lore(newLore);
                            return;
                        }
                    }
                    newLore.add(Component.text("Weapon Kills: 1"));
                    itemStack.lore(newLore);
                }
                else itemStack.lore(Collections.singletonList(Component.text("Weapon Kills: 1")));
            }
        }
    }
}