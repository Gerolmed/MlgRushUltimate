package de.gerolmed.mru.player;

import de.gerolmed.mru.utils.MlgGui;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MlgPlayer {

    private UUID uuid;
    private boolean loaded;
    private MlgGui gui;
    private Team team;

    public MlgPlayer(Player player) {
        this.setUuid(player.getUniqueId());
        load();
    }

    private void load() {
        setLoaded(false);

        new Thread(() -> {
            // LOAD Async Data

            setLoaded(true);
        }).start();
    }

    public boolean hasOpenedGui() {
        return gui != null;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(getUuid());
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public void save() {
        if(team != null)
            team.setMlgPlayer(null);
    }

    public void setOpenGui(MlgGui mlgGui) {
        this.gui = mlgGui;
    }

    public MlgGui getGui() {
        return gui;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }
}
