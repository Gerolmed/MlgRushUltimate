package de.gerolmed.mru.utils;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;

public class BlockTracer {
    private static BlockTracer instance;
    private HashMap<Location, Material> blockCache;

    public BlockTracer() {
        instance = this;
        blockCache = new HashMap<>();
    }

    public void addChange(Location location, Material material) {
        blockCache.put(location, material);
    }

    public void revertChange(Location location) {
        Material material = blockCache.get(location);

        if(material == null)
            return;

        location.getBlock().setType(material);
        flushChange(location);
    }

    public void revertAllChange() {
        for(Location entry : blockCache.keySet()) {
            revertChange(entry);
        }
        flushAllChanges();

    }

    public void flushChange(Location location) {
        blockCache.remove(location);
    }

    public void flushAllChanges() {
        for(Location entry : blockCache.keySet()) {
            flushChange(entry);
        }
    }

    public boolean hasChanges(Location location) {
        return blockCache.containsKey(location);
    }

    public static BlockTracer getInstance() {
        return instance;
    }

}
