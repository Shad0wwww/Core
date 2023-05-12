package dk.shadow.anti.listeners.bukkit;

import dk.shadow.anti.configuration.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.Arrays;

import static org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_FULL;

public class PreLogin implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        String[] blacklistNames = ConfigManager.get("blacklist.names");
        if (Arrays.asList(blacklistNames).contains(event.getName()) || event.getName().contains("TheAncientBook")) {
            event.disallow(KICK_FULL, ConfigManager.get("blacklist.kickMessage")[0]);

        }

    }
}
