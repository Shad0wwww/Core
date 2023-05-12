package dk.shadow.anti.configuration;

import dk.shadow.anti.Main;
import dk.shadow.anti.task.AutoBroadcastTask;
import dk.shadow.anti.utils.ColorUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.*;

public class ConfigManager {

    static HashMap<String, String[]> messages;
    @SuppressWarnings("FieldMayBeFinal")
    private static YamlConfiguration config;
    @SuppressWarnings("FieldMayBeFinal")
    private List<String> autoBroadcastMessages = new ArrayList<>();

    public void initialise() {
        File file = new File(Main.getInstance().getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(file);
        messages = new HashMap<>();

        for (String key : config.getConfigurationSection("").getKeys(true)) {
            if (config.isConfigurationSection(key))
                continue;
            if (config.getStringList(key) != null && config.isList(key)) {
                List<String> stringList = ColorUtils.getColored(config.getStringList(key));
                messages.put(key, stringList.<String>toArray(new String[0]));
                continue;
            }
            if (config.getString(key) != null) {
                List<String> stringList = Collections.singletonList(ColorUtils.getColored(config.getString(key)));
                messages.put(key, stringList.<String>toArray(new String[0]));
            }
        }
        if (messages.containsKey("prefix"))
            for (Map.Entry<String, String[]> entry : messages.entrySet()) {
                for (int i = 0; i < ((String[]) entry.getValue()).length; i++)
                    ((String[]) entry.getValue())[i] = ((String[]) entry.getValue())[i].replaceAll("%prefix%", ((String[]) messages.get("prefix"))[0]);
            }

        loadBroadcastMessages();
        begin();
    }

    public static String[] get(String path) {
        if(messages.containsKey(path)){
            return messages.get(path);
        }
        return new String[] { "" };
    }

    public static String[] get(String path, String... replacements) {
        String[] messages = get(path);
        for (String message : messages) {
            for (int i = 0; i < replacements.length; i += 2) {
                message = message.replaceAll(replacements[i], replacements[i+1]);
            }
            return new String[]{message};
        }
        return new String[] { "" };
    }
    public static void send(CommandSender player, String path) {
        player.sendMessage(get(path));
    }

    public static void send(CommandSender player, String path, String... replacements) {
        String[] messages = get(path);
        for (String message : messages) {
            for (int i = 0; i < replacements.length; i += 2) {
                message = message.replaceAll(replacements[i], replacements[i+1]);
            }
            player.sendMessage(message);
        }
    }

    public static void sendToStaffs(Player player, String path, String... replacements) {
        String[] messages = get(path);
        for (String message : messages) {
            for (int i = 0; i < replacements.length; i += 2) {
                message = message.replaceAll(replacements[i], replacements[i+1]);
            }
            for(Player staffs : Bukkit.getOnlinePlayers()) {

                if (!staffs.hasPermission(get("permission-to-get-bug-messages")[0])) return;

                TextComponent subComponent = new TextComponent(message);

                subComponent.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "Klik her for at tp til " +  player.getName()).create() ) );
                subComponent.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND,  "/tp " + player.getName()) );

                staffs.spigot().sendMessage( subComponent );


            }


        }
    }


    private void loadBroadcastMessages() {
        for (String key : config.getConfigurationSection("auto-broadcast.broadcast-messages").getKeys(false)) {
            Bukkit.broadcastMessage("Key - " + key);
            String[] list = get("auto-broadcast.broadcast-messages." + key);

            StringBuilder sb = new StringBuilder();
            for (String s : list) {
                sb.append(s).append("\n");
            }
            autoBroadcastMessages.add(sb.toString());
        }
    }

    private void begin() {
        boolean autoBroadcast = Boolean.parseBoolean(ConfigManager.get("auto-broadcast.enabled")[0]);
        int time = Integer.parseInt(ConfigManager.get("auto-broadcast.interval")[0]);
        if (autoBroadcast) {
            AutoBroadcastTask autoBroadcastTask = new AutoBroadcastTask(getAutoBroadcastMessages().toArray(new String[0]));
            autoBroadcastTask.runTaskTimer(Main.getInstance(), 0L, time * 20L);
        }
    }

    public List<String> getAutoBroadcastMessages() {
        return autoBroadcastMessages;
    }


}
