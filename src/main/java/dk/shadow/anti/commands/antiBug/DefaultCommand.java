package dk.shadow.anti.commands.antiBug;

import dk.shadow.anti.commands.ISubCommand;
import dk.shadow.anti.configuration.ConfigManager;
import dk.shadow.anti.utils.ColorUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DefaultCommand extends ISubCommand {

    public DefaultCommand() {
        super("default");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args, String label) {
        if (!(sender instanceof Player)) {
            ConfigManager.send(sender,"only_player_command");
        } else {
            Player player = (Player) sender;
            player.sendMessage(ConfigManager.get("prefix"));
            player.sendMessage(ColorUtils.getColored("\n"));
            if (player.hasPermission("anti.admin")) {
                player.sendMessage(ColorUtils.getColored("&8▌ &7/antiBug reload"));
            }
        }



    }
}