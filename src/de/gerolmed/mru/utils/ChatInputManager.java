package de.gerolmed.mru.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ChatInputManager {

    public abstract static class ChatInput {

        private String message;

        public ChatInput(String message) {
            this.message = message;
        }

        public abstract boolean isValid(String input);

        public abstract void execute(String input);

        public String getMessage() {
            return message;
        }
    }

    private HashMap<UUID, ChatInput> chatInputs;

    public ChatInputManager() {
        chatInputs = new HashMap<>();
    }

    public void addInput(UUID uuid, ChatInput chatInput) {
        chatInputs.put(uuid, chatInput);
    }

    public ChatInput getInput(UUID uuid) {
        return chatInputs.get(uuid);
    }

    public void removeInput(UUID uuid) {
        chatInputs.remove(uuid);
    }

    public void sendMessage(Player player) {
        ChatInput input = getInput(player.getUniqueId());
        if(input == null)
            return;
        player.sendMessage("   ");
        player.sendMessage("§a"+input.getMessage());
        player.sendMessage("§cType '§lcancel§c' to cancel!");
        player.sendMessage("   ");
    }
}
