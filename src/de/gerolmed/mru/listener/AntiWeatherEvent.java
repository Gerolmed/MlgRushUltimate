package de.gerolmed.mru.listener;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.utils.BasicEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.WeatherChangeEvent;

public class AntiWeatherEvent extends BasicEvent {
    public AntiWeatherEvent(Main main) {
        super(main);
    }

    @EventHandler
    public void onMove(WeatherChangeEvent event) {
        event.setCancelled(true);
    }
}
