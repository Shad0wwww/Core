package dk.shadow.anti.commands.antiBug;

import dk.shadow.anti.commands.ICommand;
import dk.shadow.anti.commands.ISubCommand;
import dk.shadow.anti.commands.antiBug.subs.ReloadCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiBugCommands extends ICommand {

    public AntiBugCommands(JavaPlugin plugin, String command) {
        super(plugin, command);
        setDefaultCommand(new DefaultCommand());
        addSubCommands(
                new ReloadCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length <= 0 && getDefaultCommand() != null) {
            execute(sender, getDefaultCommand(), args);
        } else if (args.length > 0) {
            ISubCommand subCommand = findSubCommand(args[0]);
            if (subCommand != null) {
                execute(sender, subCommand, args);
            }
            return true;
        }
        return false;
    }
}
