package de.gerolmed.mru.map;

import de.gerolmed.mru.utils.SoulsLocation;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.util.Vector;

import java.util.LinkedHashMap;
import java.util.Map;

@SerializableAs("MapRegion")
public class MapRegion implements Cloneable, ConfigurationSerializable  {

    private SoulsLocation location1;
    private SoulsLocation location2;

    public MapRegion(SoulsLocation location1, SoulsLocation location2) {
        this.location1 = location1;
        this.location2 = location2;
    }

    public boolean isInArea(Vector pos) {
        Vector tc = getTopCorner();
        Vector bc = getBottomCorner();
        return ((pos.getX() > bc.getX()) && (pos.getX() < tc.getX())) &&
                ((pos.getY() > bc.getY()) && (pos.getY() < tc.getY())) &&
                ((pos.getX() > bc.getX()) && (pos.getX() < tc.getX()));
    }

    public Vector getTopCorner() {
        double x = location1.getX() > location2.getX() ? location1.getX() : location2.getX();
        double y = location1.getY() > location2.getY() ? location1.getY() : location2.getY();
        double z = location1.getZ() > location2.getZ() ? location1.getZ() : location2.getZ();
        return new Vector(x, y, z);
    }

    public Vector getBottomCorner() {
        double x = location1.getX() < location2.getX() ? location1.getX() : location2.getX();
        double y = location1.getY() < location2.getY() ? location1.getY() : location2.getY();
        double z = location1.getZ() < location2.getZ() ? location1.getZ() : location2.getZ();
        return new Vector(x, y, z);
    }

    public void setLocation1(SoulsLocation location1) {
        this.location1 = location1;
    }

    public void setLocation2(SoulsLocation location2) {
        this.location2 = location2;
    }

    public SoulsLocation getLocation1() {
        return location1;
    }

    public SoulsLocation getLocation2() {
        return location2;
    }

    @Override
    public java.util.Map<String, Object> serialize() {
        java.util.Map<String, Object> list = new LinkedHashMap<>();

        list.put("loc1", location1);
        list.put("loc2", location2);

        return list;
    }

    public static MapRegion deserialize(Map<String, Object> args) {
        return new MapRegion(
                (SoulsLocation) args.get("loc1"),
                (SoulsLocation) args.get("loc2"));

    }
}
