package de.gerolmed.mru.utils;

import de.gerolmed.mru.ConfigHolder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LocationManager {
    private static LocationManager instance;

    private HashMap<String, SoulsLocation> locations;


    public LocationManager() {
        setup();
        instance = this;
    }

    private void setup() {
        locations = new HashMap();

        ConfigurationSection sec = ConfigHolder.Configs.POSITION.getConfig().getConfigurationSection("locs");
        if (sec == null) {
            ConfigHolder.Configs.POSITION.getConfig().createSection("locs");
            ConfigHolder.Configs.POSITION.saveConfig();
            sec = ConfigHolder.Configs.POSITION.getConfig().getConfigurationSection("locs");
        }
        for (String name : sec.getKeys(false)) {
            locations.put(name, (SoulsLocation) sec.get(name));
        }
    }

    private void toConfig() {
        for (Map.Entry<String, SoulsLocation> set : locations.entrySet()) {
            setPos(set.getKey(), set.getValue());
        }
    }

    public Location getPostion(String position) {
        Location locTo = null;

        if (locations.containsKey(position)) {
            locTo = locations.get(position).toLocation();
        }

        if (locTo == null) {
            locTo = new Location(Bukkit.getWorlds().get(0), 0, 5, 0);
        }

        return locTo;

    }

    public ArrayList<Location> getAllPostions(String position) {

        ArrayList<Location> ret = new ArrayList<>();

        for (String key : locations.keySet()) {
            if (key.startsWith(position))
                ret.add(getPostion(key));
        }

        return ret;

    }

    public HashMap<String, SoulsLocation> getAllSoulsLocations() {

        return locations;

    }

    public void setPosition(String name, Location l) {
        setup();

        if (locations.containsKey(name)) {
            locations.remove(name);
        }

        locations.put(name, new SoulsLocation(l));

        toConfig();
    }

    private void setPos(String name, SoulsLocation l) {
        ConfigHolder.Configs.POSITION.getConfig().set("locs." + name, l);
        ConfigHolder.Configs.POSITION.saveConfig();
    }

    public ArrayList<String> getNearPositions(String position, Location loc, float f) {
        ArrayList<String> ret = new ArrayList<>();

        for (Map.Entry<String, SoulsLocation> key : locations.entrySet()) {
            if (key.getValue().toLocation().distance(loc) <= f && key.getKey().startsWith(position))
                ret.add(key.getKey());
        }

        return ret;

    }

    public void removeLocation(String key) {
        locations.remove(key);

        toConfig();
    }

    public static LocationManager getInstance() {
        return instance;
    }
}
