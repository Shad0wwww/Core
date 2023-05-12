package dk.shadow.anti.listeners.bukkit;

import com.avaje.ebean.validation.NotNull;
import dk.shadow.anti.Main;
import dk.shadow.anti.configuration.ConfigManager;
import dk.shadow.anti.task.CombatSystem;
import dk.shadow.anti.utils.ColorUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class DamageByEntityEvent implements Listener {
    Main plugin;
    public DamageByEntityEvent(Main plugin) {
        this.plugin = plugin;
    }

    String s = ConfigManager.get("combatSystem.combat-tid")[0];
    int tid = Integer.parseInt(s);
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player victim = (Player) event.getEntity();
            Player attacker = (Player) event.getDamager();

            if (Boolean.parseBoolean(ConfigManager.get("damage-exploit-disabler.enabled")[0])) {
                final ItemStack item = attacker.getInventory().getItemInHand();
                if (!checkDamage(item, "sword", "axe", "hoe", "shovel", "bow", "helmet", "chestplate", "leggings", "boots"))
                    return;
                final double damage = event.getDamage();
                if (damage > 2.8) {
                    event.setCancelled(true);
                    ConfigManager.send(attacker, "damage-exploit-disabler.message");
                    ConfigManager.send(attacker, "damage-exploit-disabler.fix-message");
                }
            }

            if (!Boolean.parseBoolean(ConfigManager.get("combatSystem.enabled")[0])) return;
            seeCombat(victim.getPlayer());
            seeCombat(attacker.getPlayer());

        }



    }

    public void seeCombat(Player player) {
        System.out.println("player - " + player.getDisplayName());
        if (player.hasMetadata("InCombat")) {
            System.out.println("player 43 - " + player.getDisplayName());
            player.setMetadata("InCombat", new FixedMetadataValue(Main.getInstance(), tid));
        } else {
            System.out.println("player 46 - " + player.getDisplayName());
            player.setMetadata("InCombat", new FixedMetadataValue(Main.getInstance(), tid));
            CombatSystem.combatTag(player);
        }
    }

    /**
     * Checks if the item is allowed to exceed the damage limit.
     *
     * @param heldItem the held {@link ItemStack}
     * @param dontCheck the items that are allowed to exceed the damage limit.
     *
     * @return if the item is allowed to exceed the damage limit.
     */

    private boolean checkDamage(ItemStack heldItem, String... dontCheck) {
        for (String item : dontCheck) {
            if (heldItem.getType().name()
                    .toLowerCase().contains(item.toLowerCase())) {
                return false;
            }
        }
        return true;
    }



}
