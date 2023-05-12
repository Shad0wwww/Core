package dk.shadow.anti.listeners;

import dk.shadow.anti.Main;
import dk.shadow.anti.listeners.bukkit.*;
import dk.shadow.anti.listeners.custom.FlowerPotGriefing;
import dk.shadow.anti.listeners.custom.SkullGrifing;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Listeners {
    public static void initialise(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(new DamageByEntityEvent(Main.getInstance()), (Plugin)plugin);
        Bukkit.getPluginManager().registerEvents(new InteractEvent(), (Plugin)plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerRespawn(), (Plugin)plugin);
        Bukkit.getPluginManager().registerEvents(new FlowerPotGriefing(), (Plugin)plugin);
        Bukkit.getPluginManager().registerEvents(new SkullGrifing(), (Plugin)plugin);
        Bukkit.getPluginManager().registerEvents(new PreLogin(), (Plugin)plugin);
        //Bukkit.getPluginManager().registerEvents(new FallenBlock(), (Plugin)plugin);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(), (Plugin)plugin);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), (Plugin)plugin);

    }
}
