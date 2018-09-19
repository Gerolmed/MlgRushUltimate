package de.gerolmed.mru.utils;

import org.bukkit.Location;

public enum PreLocs {
    SPAWN("spawn");
    private String locationName;

    PreLocs(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }

    public Location getLocation() {
        return LocationManager.getInstance().getPostion(getLocationName());
    }

    public void setLocation(Location loc) {
        LocationManager.getInstance().setPosition(getLocationName(), loc);
    }
}
