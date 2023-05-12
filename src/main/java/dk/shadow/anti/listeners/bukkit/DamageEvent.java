package dk.shadow.anti.listeners.bukkit;

import dk.shadow.anti.configuration.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageEvent implements Listener {



    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onDamageEvent(EntityDamageByEntityEvent event) {

        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player damagedPlayer = (Player) event.getEntity();
            Player attackingPlayer = (Player) event.getDamager();


            double damagedPlayerX = damagedPlayer.getEyeLocation().getX();
            double damagedPlayerY = damagedPlayer.getEyeLocation().getY();
            double damagedPlayerZ = damagedPlayer.getEyeLocation().getZ();

            double attackingPlayerX = attackingPlayer.getEyeLocation().getX();
            double attackingPlayerY = attackingPlayer.getEyeLocation().getY();
            double attackingPlayerZ = attackingPlayer.getEyeLocation().getZ();





            if(attackingPlayer.getGameMode() == GameMode.CREATIVE || damagedPlayer.getGameMode() == GameMode.CREATIVE) {
                return;
            }

            double distance = Math.sqrt(Math.pow(damagedPlayerX - attackingPlayerX, 2) + Math.pow(damagedPlayerY - attackingPlayerY, 2) + Math.pow(damagedPlayerZ - attackingPlayerZ, 2));
            String formattedDistance = String.format("%.2f", distance);
            double above_distance = Double.parseDouble(ConfigManager.get("damage.above_distance")[0]);


            if(distance > above_distance) {
                // The distance between the two players is greater than 3
                // Handle the event accordingly
                for(Player player : Bukkit.getOnlinePlayers()){
                    if (player.hasPermission(ConfigManager.get("damage.permission")[0])) {
                        ConfigManager.send(player,"damage.message", "%damage%", damagedPlayer.getDisplayName(), "%attacker%", attackingPlayer.getDisplayName(), "%distance%", String.valueOf(formattedDistance));
                    }
                }
            }
        }

    }



}
