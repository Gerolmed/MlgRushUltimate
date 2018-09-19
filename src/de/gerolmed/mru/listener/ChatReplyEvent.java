package de.gerolmed.mru.listener;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.utils.BasicEvent;
import de.gerolmed.mru.utils.ChatInputManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatReplyEvent extends BasicEvent {

    public ChatReplyEvent(Main main) {
        super(main);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        ChatInputManager.ChatInput input = plugin.getChatInputManager().getInput(player.getUniqueId());

        if(input == null)
            return;

        event.setCancelled(true);

        String message = event.getMessage();

        if(message.equalsIgnoreCase("cancel")) {
            plugin.getChatInputManager().removeInput(player.getUniqueId());
            player.sendMessage("§6§lAction was canceled!");
            return;
        }

        if(!input.isValid(message)) {
            plugin.getChatInputManager().sendMessage(player);
            return;
        }

        plugin.getChatInputManager().removeInput(player.getUniqueId());
        input.execute(message);

    }

}