package de.gerolmed.mru.player;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class MlgPlayerManager {

    private static HashMap<UUID, MlgPlayer> players = new HashMap<>();

    public static MlgPlayer addPlayer(Player player) {
        players.put(player.getUniqueId(), new MlgPlayer(player));
        return getPlayer(player.getUniqueId());
    }

    public static MlgPlayer getPlayer(UUID uuid) {
        MlgPlayer pl = players.get(uuid);
        return pl;
    }

    public static void removePlayer(Player player) {
        if (player == null)
            return;
        getPlayer(player.getUniqueId()).save();
        players.remove(player.getUniqueId());
    }

    public static MlgPlayer[] getAll() {
        MlgPlayer[] player = new MlgPlayer[players.size()];
        return players.values().toArray(player);
    }

}

