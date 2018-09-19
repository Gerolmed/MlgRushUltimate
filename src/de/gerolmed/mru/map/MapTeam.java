package de.gerolmed.mru.map;

import de.gerolmed.mru.utils.SoulsLocation;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.LinkedHashMap;
import java.util.Map;

@SerializableAs("MapTeam")
public class MapTeam implements Cloneable, ConfigurationSerializable {
    private SoulsLocation teamSpawn;
    private MapRegion mapRegion;

    public MapTeam(SoulsLocation teamSpawn, MapRegion mapRegion) {
        this.teamSpawn = teamSpawn;
        this.mapRegion = mapRegion;
    }

    public SoulsLocation getTeamSpawn() {
        return teamSpawn;
    }

    public void setTeamSpawn(SoulsLocation teamSpawn) {
        this.teamSpawn = teamSpawn;
    }

    public MapRegion getMapRegion() {
        return mapRegion;
    }


    @Override
    public java.util.Map<String, Object> serialize() {
        java.util.Map<String, Object> list = new LinkedHashMap<>();

        list.put("spawn", teamSpawn);
        list.put("region", mapRegion);


        return list;
    }

    public static MapTeam deserialize(Map<String, Object> args) {
        return new MapTeam(
                (SoulsLocation) args.get("spawn"),
                (MapRegion) args.get("region"));

    }
}
