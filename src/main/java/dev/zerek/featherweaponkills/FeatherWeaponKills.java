package dev.zerek.featherweaponkills;

import dev.zerek.featherweaponkills.listeners.PlayerDeathListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FeatherWeaponKills extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new PlayerDeathListener(this),this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
