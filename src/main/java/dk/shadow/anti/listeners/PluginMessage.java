package dk.shadow.anti.listeners;

import org.bukkit.entity.Player;

import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class PluginMessage implements PluginMessageListener {



    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        player.sendMessage("Your client brand: " + new String(message, StandardCharsets.UTF_8).substring(1));
    }
}
