package de.gerolmed.mru.listener;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.game.GameManager;
import de.gerolmed.mru.utils.BasicEvent;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class WorldInteractionEvent extends BasicEvent {

    public WorldInteractionEvent(Main main) {
        super(main);
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(player.getGameMode() != GameMode.CREATIVE)
            event.setCancelled(true);

        if(GameManager.getInstance().getGameState() != GameManager.GameState.RUNNING)
            return;

        if(GameManager.getInstance().getGameType().destroyBlock(player, event.getBlock().getLocation()))
            event.setCancelled(false);
    }

    @EventHandler
    public void placeBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if(player.getGameMode() != GameMode.CREATIVE)
            event.setCancelled(true);

        if(GameManager.getInstance().getGameState() != GameManager.GameState.RUNNING)
            return;

        if(GameManager.getInstance().getGameType().placeBlock(player, event.getBlock().getLocation()))
            event.setCancelled(false);
    }
}
