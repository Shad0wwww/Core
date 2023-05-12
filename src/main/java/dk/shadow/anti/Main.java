package dk.shadow.anti;

import java.io.File;

import dk.shadow.anti.commands.CommandManager;
import dk.shadow.anti.configuration.ConfigManager;
import dk.shadow.anti.listeners.Listeners;
import dk.shadow.anti.task.AutoBroadcastTask;
import dk.shadow.anti.utils.PacketUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;
    private static ConfigManager configManager;

    public void onEnable() {
        instance = this;
        reload();
        Listeners.initialise(this);
        CommandManager.initialise(this);



    }

    public void onDisable() {}

    public static Main getInstance() {
        return instance;
    }

    public void reload() {
        initialiseConfigs();
    }

    private void initialiseConfigs() {
        saveDefaultConfig();
        File config = new File(getDataFolder(), "config.yml");
        if (!config.exists())
            saveResource("config.yml", false);
        configManager = new ConfigManager();
        configManager.initialise();
    }

    public void endCombat(Player player) {
        if (player.hasMetadata("InCombat")) {
            player.removeMetadata("InCombat", this);
        }
    }
}
