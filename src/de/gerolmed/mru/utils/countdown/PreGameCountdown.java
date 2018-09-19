package de.gerolmed.mru.utils.countdown;

import de.gerolmed.mru.game.GameManager;
import de.gerolmed.mru.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PreGameCountdown implements Runnable {

    private int countdown, resetCountdown = 20 * 30;
    private int fullSec;

    public int taskId;

    public PreGameCountdown() {
        countdown = resetCountdown;
    }

    @Override
    public void run() {

        if(Bukkit.getOnlinePlayers().size() != 1) {

            countdown = resetCountdown;
            fullSec = (int) (Math.ceil(countdown/20f));

            renderCountdown();
            return;
        }
        countdown--;
        int oldFullSec = fullSec;
        fullSec = (int) (Math.ceil(countdown/20f));

        if(oldFullSec != fullSec)
        {
            //TODO: play sound
            if((fullSec % 10 == 0 || fullSec <=5 ) && fullSec != 0)
                ChatUtils.broadcast("§aGame will begin in §e"+fullSec);
        }

        renderCountdown();

        if(countdown <= 0) {
            Bukkit.getScheduler().cancelTask(taskId);
            GameManager.getInstance().start();
            ChatUtils.broadcast("§a§lGame started!");
        }

    }

    private void renderCountdown() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.setExp(countdown * (1f / resetCountdown));
            player.setLevel(fullSec);
        }
    }
}
