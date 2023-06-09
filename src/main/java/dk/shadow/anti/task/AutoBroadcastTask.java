package dk.shadow.anti.task;


import dk.shadow.anti.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoBroadcastTask extends BukkitRunnable {

    private final String[] messages;
    private int index = 0;
    private boolean stop = false;

    public AutoBroadcastTask(String[] messages) {
        this.messages = messages;
    }

    @Override
    public void run() {
        if (stop) {
            cancel();
            return;
        }
        String currentMessage = messages[index];
        Bukkit.broadcastMessage(ColorUtils.getColored(currentMessage));
        index++;
        if(index >= messages.length)
            index = 0;
    }

    public void stop() {
        this.stop = true;
    }
}