package de.gerolmed.mru.map;

import de.gerolmed.mru.ConfigHolder;
import de.gerolmed.mru.game.GameType;
import de.gerolmed.mru.utils.SoulsLocation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.util.ArrayList;

public class MapManager {
    private ArrayList<Map> maps;

    public MapManager() {

        try {
            maps = (ArrayList<Map>) ConfigHolder.Configs.MAPS.getConfig().getList("maps");
        } catch (Exception ex) {
            System.err.println("No Maps found/File is invalid");
        }
        if(maps == null)
            maps = new ArrayList<>();

        System.out.println("Maps found: " + maps.size());

        for(Map map : maps)
            System.out.println(" - Name: "+ map.getDisplayName()+ " World: "+ map.getWorldName() + " Type" + map.getGameType().toString());

    }

    public void addMap(Map map) {
        maps.add(map);
        save();
    }

    public void save() {
        System.out.println("Saving data");
        ConfigHolder.Configs.MAPS.getConfig().set("maps", maps);
        ConfigHolder.Configs.MAPS.saveConfig();
    }

    public boolean mapExists(String mapName) {

        for(Map map : maps)
            if(map.getDisplayName().equalsIgnoreCase(mapName))
                return true;

        return false;
    }

    public void createNewMap(GameType gameType, String worldName, String mapName) {
        System.out.println("Map initialization...");
        System.out.println("Creating world");
        World world = Bukkit.getServer().createWorld(new WorldCreator(worldName));
        System.out.println("Finished creating world");


        System.out.println("Setting up default data");
        MapRegion all = new MapRegion(new SoulsLocation(new Location(world, 0,0,0)), new SoulsLocation(new Location(world, 0,0,0)));
        MapRegion blueTeamArea = new MapRegion(new SoulsLocation(new Location(world, 0,0,0)), new SoulsLocation(new Location(world, 0,0,0)));
        MapRegion redTeamArea = new MapRegion(new SoulsLocation(new Location(world, 0,0,0)), new SoulsLocation(new Location(world, 0,0,0)));

        Map map = new Map(world.getName(), gameType, mapName, all, new SoulsLocation(new Location(world, 0,0,0)),
                new MapTeam(new SoulsLocation(new Location(world, 0,0,0)), redTeamArea),
                new MapTeam(new SoulsLocation(new Location(world, 0,0,0)), blueTeamArea));
        System.out.println("Adding to list");
        addMap(map);
    }

    public Map getMap(String mapName) {
        for(Map map : maps)
            if(map.getDisplayName().equalsIgnoreCase(mapName))
                return map;
        return null;
    }

    public ArrayList<Map> getMaps() {
        return maps;
    }
}
