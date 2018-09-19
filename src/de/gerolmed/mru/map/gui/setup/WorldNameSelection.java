package de.gerolmed.mru.map.gui.setup;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.game.GameType;
import de.gerolmed.mru.utils.ChatInputManager;
import org.bukkit.entity.Player;

public class WorldNameSelection {

    private Player player;
    private GameType gameType;
    private String worldName;
    private ChatInputManager.ChatInput selectWorld, selectWorldConfirm;

    public WorldNameSelection(Player player, GameType gameType) {
        this.player = player;
        this.gameType = gameType;
        ChatInputManager chatInputManager = Main.getInstance().getChatInputManager();
        selectWorld = new ChatInputManager.ChatInput("Enter a world name! (If world doesn't exist we will create one)") {
                    @Override
                    public boolean isValid(String input) {
                        return true;
                    }

                    @Override
                    public void execute(String worldname) {
                        worldname = worldname.replaceAll("\\s+","");
                        setWorldName(worldname);
                        getPlayer().sendMessage("§6§lWorldname selected: §a"+ getWorldName());
                        chatInputManager.addInput(getPlayer().getUniqueId(), selectWorldConfirm);
                        chatInputManager.sendMessage(getPlayer());
                    }
                };

        selectWorldConfirm = new ChatInputManager.ChatInput("Confirm world? (yes/no)") {
            @Override
            public boolean isValid(String input) {
                return input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("no");
            }

            @Override
            public void execute(String input) {
                if(input.equalsIgnoreCase("no")) {
                    chatInputManager.addInput(getPlayer().getUniqueId(), selectWorld);
                    chatInputManager.sendMessage(getPlayer());
                    return;
                }
                new MapNameSelection(getPlayer(), gameType, getWorldName());
            }
        };

        sendInfo();
        chatInputManager.addInput(getPlayer().getUniqueId(), selectWorld);
        chatInputManager.sendMessage(getPlayer());
    }

    public Player getPlayer() {
        return player;
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public void sendInfo() {
        getPlayer().sendMessage("§c§lINFO:§a" + " §aGame-Type: §e"+ gameType+ " §aWorld: §e"+ worldName+ " §aMapName: §e"+ null);
    }
}
