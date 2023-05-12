package dk.shadow.anti.commands;


import dk.shadow.anti.commands.antiBug.AntiBugCommands;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager {
    public static void initialise(JavaPlugin instance) {
        new AntiBugCommands(instance, "SpaceXCore");

    }
}