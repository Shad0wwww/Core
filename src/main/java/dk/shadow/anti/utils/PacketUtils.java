package dk.shadow.anti.utils;

import net.minecraft.server.v1_8_R3.PacketPlayOutNamedSoundEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PacketUtils {

    public static void blockAnvilSound(Player player) {
        try {
            Class<?> craftPlayerClass = Class.forName("org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer");
            Object craftPlayer = craftPlayerClass.cast(player);
            Object entityPlayer = craftPlayerClass.getMethod("getHandle").invoke(craftPlayer);
            Field connectionField = entityPlayer.getClass().getField("playerConnection");
            Object playerConnection = connectionField.get(entityPlayer);
            Class<?> packetClass = Class.forName("net.minecraft.server.v1_8_R3.PacketPlayOutNamedSoundEffect");
            Class<?> soundCategoryClass = Class.forName("net.minecraft.server.v1_8_R3.SoundCategory");
            Field soundField = soundCategoryClass.getField("BLOCKS");
            Object soundCategory = soundField.get(null);
            Object packet = packetClass.getConstructor(String.class, Double.class, Double.class, Double.class, Float.class, Float.class, Float.class).newInstance("block.anvil.use", player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), 1f, 1f, 1f);
            playerConnection.getClass().getMethod("sendPacket", Class.forName("net.minecraft.server.v1_8_R3.Packet")).invoke(playerConnection, packet);
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 0f, 0f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
