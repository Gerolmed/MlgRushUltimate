package de.gerolmed.mru.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ChatUtils {

    public static void broadcast(String message) {
        for(Player player : Bukkit.getOnlinePlayers())
            player.sendMessage(message);
    }

}
