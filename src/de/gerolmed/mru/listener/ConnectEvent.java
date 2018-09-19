package de.gerolmed.mru.listener;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.game.GameManager;
import de.gerolmed.mru.player.MlgPlayer;
import de.gerolmed.mru.player.MlgPlayerManager;
import de.gerolmed.mru.utils.BasicEvent;
import de.gerolmed.mru.utils.PreLocs;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public class ConnectEvent extends BasicEvent {

    public ConnectEvent(Main main) {
        super(main);
    }

    @EventHandler
    public void onFirstJoin(PlayerSpawnLocationEvent event) {
        event.setSpawnLocation(PreLocs.SPAWN.getLocation());
    }

    @EventHandler
    public void onConnect(PlayerJoinEvent event) {

        event.setJoinMessage(null);

        Player player = event.getPlayer();

        Location spawn = PreLocs.SPAWN.getLocation();
        player.teleport(spawn);
        MlgPlayer mlgPlayer = MlgPlayerManager.addPlayer(player);

        //TODO: InventoryType.IN_GAME.apply(player);

        GameManager gameManager = GameManager.getInstance();

        if(gameManager.getTeamBlue().getMlgPlayer() == null) {
            gameManager.getTeamBlue().setMlgPlayer(mlgPlayer);
        } else if(gameManager.getTeamRed()== null) {
            gameManager.getTeamRed().setMlgPlayer(mlgPlayer);
        }

    }
}