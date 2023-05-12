package dk.shadow.anti.listeners.bukkit;

import dk.shadow.anti.Main;
import dk.shadow.anti.configuration.ConfigManager;
import dk.shadow.anti.utils.Authenticate;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.JSONObject;


public class JoinEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void OnPlayerLogin(PlayerJoinEvent event){

        if(!event.getPlayer().hasPlayedBefore()) {
            event.setJoinMessage(ConfigManager.get("joinmessage.firstjoin", "%player%", event.getPlayer().getDisplayName(), "%allPlayers%", String.valueOf(Bukkit.getOnlinePlayers().size()))[0]);
        }
        event.setJoinMessage(ConfigManager.get("joinmessage.join", "%player%", event.getPlayer().getDisplayName())[0]);

        //AUTH

    }
}
