package dk.shadow.anti.listeners.bukkit;

import dk.shadow.anti.configuration.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class FallenBlock implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFallenBlock(EntityChangeBlockEvent event) {
        FallingBlock fallingBlock = (FallingBlock) event.getEntity();
        Player player = (Player) event.getEntity();

        Bukkit.broadcastMessage(String.valueOf(((Player) event.getEntity()).getPlayer()));
        if (event.getTo() != null && event.getBlock().getType() == Material.ANVIL && event.getEntityType() == EntityType.FALLING_BLOCK) {

            String s = ConfigManager.get("anvilgrief.enabled")[0];
            boolean anvil = Boolean.parseBoolean(s);
            if (!anvil) {return;}
            event.setCancelled(true);
            fallingBlock.setDropItem(false);
            fallingBlock.getLocation().getBlock().setType(Material.AIR);

            World world = event.getBlock().getWorld();
            Location location = event.getBlock().getLocation();


            ItemStack anvilItem = new ItemStack(Material.ANVIL, 1);
            Item itemEntity = world.dropItem(location, anvilItem);
            itemEntity.setVelocity(fallingBlock.getVelocity());

            ConfigManager.send(player, "anvilgrief.message");
            ConfigManager.sendToStaffs(player,"anvilgrief.send_to_staff", "%player%", player.getDisplayName());

        }
    }
}
