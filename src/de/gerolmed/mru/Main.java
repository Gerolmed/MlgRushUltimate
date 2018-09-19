package de.gerolmed.mru;

import de.gerolmed.mru.commands.MlgCommand;
import de.gerolmed.mru.game.GameManager;
import de.gerolmed.mru.listener.*;
import de.gerolmed.mru.map.Map;
import de.gerolmed.mru.map.MapManager;
import de.gerolmed.mru.map.MapRegion;
import de.gerolmed.mru.map.MapTeam;
import de.gerolmed.mru.utils.BlockTracer;
import de.gerolmed.mru.utils.ChatInputManager;
import de.gerolmed.mru.utils.LocationManager;
import de.gerolmed.mru.utils.SoulsLocation;
import de.gerolmed.mru.utils.countdown.PreGameCountdown;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;
    public static Main getInstance() {
        return instance;
    }

    static {
        ConfigurationSerialization.registerClass(SoulsLocation.class);
        ConfigurationSerialization.registerClass(MapRegion.class);
        ConfigurationSerialization.registerClass(MapTeam.class);
        ConfigurationSerialization.registerClass(Map.class);
    }

    private ConfigHolder configHolder;
    private ChatInputManager chatInputManager;
    private MapManager mapManager;

    @Override
    public void onEnable() {
        instance = this;

        configHolder = new ConfigHolder(this);
        chatInputManager = new ChatInputManager();
        mapManager = new MapManager();

        new LocationManager();


        registerEvents();
        registerCommands();

        new GameManager();
        new BlockTracer();

        //Start countdown for game start
        {
            PreGameCountdown preGameCountdown = new PreGameCountdown();
            preGameCountdown.taskId = Bukkit.getScheduler().runTaskTimer(this, preGameCountdown, 1, 1).getTaskId();
        }
    }

    private void registerCommands() {
        new MlgCommand(this);
    }

    private void registerEvents() {
        new ConnectEvent(this);
        new DisconnectEvent(this);
        new AntiWeatherEvent(this);
        new DropItemEvent(this);
        new InventoryEvent(this);
        new ChatReplyEvent(this);
        new MoveEvent(this);
        new WorldInteractionEvent(this);
        new DamageEvent(this);
    }

    public ConfigHolder getConfigHolder() {
        return configHolder;
    }

    public ChatInputManager getChatInputManager() {
        return chatInputManager;
    }

    public MapManager getMapManager() {
        return mapManager;
    }
}
