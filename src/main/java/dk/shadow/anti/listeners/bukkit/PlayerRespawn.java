package dk.shadow.anti.listeners.bukkit;

import dk.shadow.anti.Main;
import dk.shadow.anti.configuration.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerRespawn implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerRespawn(org.bukkit.event.player.PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        player.spigot().respawn();
        Main.getInstance().endCombat(player);
        ConfigManager.send(player, "combatSystem.respawnMessage");
    }


}