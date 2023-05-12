package dk.shadow.anti.listeners.bukkit;

import dk.shadow.anti.configuration.ConfigManager;
import dk.shadow.anti.events.FlowerPotGriefingEvent;
import dk.shadow.anti.events.SkullGrifingEvent;
import dk.shadow.anti.utils.PacketUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class InteractEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFlowerPotGrief(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (player.getItemInHand() != null && player.getItemInHand().getType() == Material.ANVIL) {

                Block block = event.getClickedBlock().getRelative(event.getBlockFace());


                PacketUtils.blockAnvilSound(player);


                if (block.getType() == Material.FLOWER_POT) {
                    String s = ConfigManager.get("flowerpot.enabled")[0];
                    boolean grief = Boolean.parseBoolean(s);
                    if (!grief) {return;}

                    FlowerPotGriefingEvent flower = new FlowerPotGriefingEvent(player, false);
                    flower.call();

                    if(!flower.isCancelled()) {
                        event.setCancelled(true);
                        block.getState().update(true, false);
                        ConfigManager.send(player, "flowerpot.message");
                        ConfigManager.sendToStaffs(player, "skullgrief.send_to_staff", "%player%", player.getDisplayName());
                    }

                } else if (block.getType().toString().endsWith("SKULL")) {
                    String s = ConfigManager.get("anvilgrief.enabled")[0];
                    boolean grief = Boolean.parseBoolean(s);
                    if (!grief) {return;}

                    SkullGrifingEvent skullGrifingEvent = new SkullGrifingEvent(player, false);
                    skullGrifingEvent.call();
                    if(!skullGrifingEvent.isCancelled()) {
                        event.setCancelled(true);
                        block.getState().update(true, false);
                        ConfigManager.send(player, "anvilgrief.message");
                        ConfigManager.sendToStaffs(player,"anvilgrief.send_to_staff", "%player%", player.getDisplayName());



                    }
                }
            }
        }
    }
}
