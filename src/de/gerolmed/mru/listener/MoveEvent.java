package de.gerolmed.mru.listener;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.game.GameManager;
import de.gerolmed.mru.utils.BasicEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class MoveEvent extends BasicEvent {
    public MoveEvent(Main main) {
        super(main);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if(event.getTo().getY() < 0)
            GameManager.getInstance().getGameType().dieVoid(GameManager.getInstance(), event.getPlayer());
    }
}
