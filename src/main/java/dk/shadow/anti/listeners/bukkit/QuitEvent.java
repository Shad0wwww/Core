package dk.shadow.anti.listeners.bukkit;

import dk.shadow.anti.Main;
import dk.shadow.anti.configuration.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (player.hasMetadata("InCombat")) {
            player.removeMetadata("InCombat", Main.getInstance());
            Bukkit.broadcastMessage(ConfigManager.get("combatSystem.leavede", "%player%", player.getDisplayName())[0]);
            player.setHealth(0);
        }

    }
}
