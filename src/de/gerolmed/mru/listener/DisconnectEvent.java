package de.gerolmed.mru.listener;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.player.MlgPlayerManager;
import de.gerolmed.mru.utils.BasicEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DisconnectEvent extends BasicEvent {

    public DisconnectEvent(Main main) {
        super(main);
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        leave(event.getPlayer());
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        event.setLeaveMessage(null);
        leave(event.getPlayer());
    }

    private void leave(Player player) {
        MlgPlayerManager.removePlayer(player);
    }
}
