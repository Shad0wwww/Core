package dk.shadow.anti.commands.antiBug.subs;

import dk.shadow.anti.Main;
import dk.shadow.anti.commands.ISubCommand;
import dk.shadow.anti.configuration.ConfigManager;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends ISubCommand {
    public ReloadCommand() {
        super("reload");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args, String paramString) {
        //Checks, if player have permission.
        if (!sender.hasPermission("antibug.reload")) {
            ConfigManager.send(sender, "no_permission");
            return;
        }
        //Send permission that you started to reload.
        ConfigManager.send(sender, "reload.started");
        try {
            Main.getInstance().reload();
            ConfigManager.send(sender, "reload.success");
        } catch (Exception e) {
            ConfigManager.send(sender, "reload.error");
            e.printStackTrace();
        }

    }
}
