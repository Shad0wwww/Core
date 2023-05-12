package dk.shadow.anti.task;

import dk.shadow.anti.Main;
import dk.shadow.anti.configuration.ConfigManager;
import dk.shadow.anti.utils.ActionBar;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class CombatSystem {



    public static void combatTag(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (player.hasMetadata("InCombat")) {
                    int index = player.getMetadata("InCombat").get(0).asInt();
                    if (index >= 1) {
                        String message = ConfigManager.get("combatSystem.message", "%time%", String.valueOf(index))[0];
                        System.out.println(message);
                        player.setMetadata("InCombat", new FixedMetadataValue(Main.getInstance(), index - 1));
                        ActionBar.sendActionBar(player, message);
                    } else {
                        player.removeMetadata("InCombat", Main.getInstance());
                        cancel();
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(Main.getInstance(), 0L, 20L);
    }

}
