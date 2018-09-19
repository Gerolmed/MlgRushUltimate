package de.gerolmed.mru.map;

import de.gerolmed.mru.game.GameType;
import de.gerolmed.mru.utils.SoulsLocation;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.LinkedHashMap;
import java.util.UUID;

@SerializableAs("Map")
public class Map implements Cloneable, ConfigurationSerializable {

    private String worldName;
    private GameType gameType;
    private String displayName;
    private MapRegion region;
    private SoulsLocation spectatorPosition;
    private MapTeam redTeam, blueTeam;

    public Map(String worldName, GameType gameType, String displayName, MapRegion region, SoulsLocation spectatorPosition, MapTeam redTeam, MapTeam blueTeam) {
        this.worldName = worldName;
        this.gameType = gameType;
        this.displayName = displayName;
        this.region = region;
        this.spectatorPosition = spectatorPosition;
        this.redTeam = redTeam;
        this.blueTeam = blueTeam;
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public MapRegion getRegion() {
        return region;
    }

    public void setRegion(MapRegion region) {
        this.region = region;
    }

    public SoulsLocation getSpectatorPosition() {
        return spectatorPosition;
    }

    public void setSpectatorPosition(SoulsLocation spectatorPosition) {
        this.spectatorPosition = spectatorPosition;
    }

    public MapTeam getRedTeam() {
        return redTeam;
    }

    public MapTeam getBlueTeam() {
        return blueTeam;
    }

    @Override
    public java.util.Map<String, Object> serialize() {
        java.util.Map<String, Object> list = new LinkedHashMap<>();

        list.put("world", worldName);
        list.put("name", displayName);
        list.put("game", gameType.toString());
        list.put("region", region);
        list.put("spectatorPosition", spectatorPosition);
        list.put("teamRed", redTeam);
        list.put("teamBlue", blueTeam);

        return list;
    }

    public static Map deserialize(java.util.Map<String, Object> args) {
        return new Map(
                (String) args.get("world"),
                GameType.valueOf((String) args.get("game")),
                (String) args.get("name"),
                (MapRegion) args.get("region"),
                (SoulsLocation) args.get("spectatorPosition"),
                (MapTeam) args.get("teamRed"),
                (MapTeam) args.get("teamBlue"));

    }
}
