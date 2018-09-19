package de.gerolmed.mru.listener;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.game.GameManager;
import de.gerolmed.mru.player.MlgPlayerManager;
import de.gerolmed.mru.utils.BasicEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DamageEvent extends BasicEvent {

    public DamageEvent(Main main) {
        super(main);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(!GameManager.getInstance().getGameType().canDamage())
            event.setDamage(0);
    }
}
