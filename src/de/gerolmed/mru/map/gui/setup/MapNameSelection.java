package de.gerolmed.mru.map.gui.setup;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.game.GameType;
import de.gerolmed.mru.utils.ChatInputManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MapNameSelection {

    private Player player;
    private GameType gameType;
    private String worldName, mapName;
    private ChatInputManager.ChatInput selectWorld, selectWorldConfirm;

    public MapNameSelection(Player player, GameType gameType, String worldName) {
        this.player = player;
        this.gameType = gameType;
        this.worldName = worldName;
        ChatInputManager chatInputManager = Main.getInstance().getChatInputManager();
        selectWorld = new ChatInputManager.ChatInput("Enter a map name! (No duplicates)") {
                    @Override
                    public boolean isValid(String input) {
                        boolean valid = !Main.getInstance().getMapManager().mapExists(input);
                        if(!valid)
                            getPlayer().sendMessage("§c§lMap name is already used!");
                        return valid;
                    }

                    @Override
                    public void execute(String mapname) {
                        setMapName(mapname);
                        getPlayer().sendMessage("§6§lMapname selected: §a"+ getMapName());
                        chatInputManager.addInput(getPlayer().getUniqueId(), selectWorldConfirm);
                        chatInputManager.sendMessage(getPlayer());
                    }
                };

        selectWorldConfirm = new ChatInputManager.ChatInput("Confirm map name? (yes/no)") {
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
                sendInfo();
                Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                    if( Main.getInstance().getMapManager().mapExists(mapName)) {
                        getPlayer().sendMessage("§cMap exists! Aborting setup...");
                        return;
                    }
                    getPlayer().sendMessage("§aMap is being added...");
                    Main.getInstance().getMapManager().createNewMap(gameType, worldName, mapName);
                    getPlayer().sendMessage("§aMap was added!");
                }, 1);
            }
        };

        sendInfo();
        chatInputManager.addInput(getPlayer().getUniqueId(), selectWorld);
        chatInputManager.sendMessage(getPlayer());
    }

    public Player getPlayer() {
        return player;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public void sendInfo() {
        getPlayer().sendMessage("§c§lINFO:§a" + " §aGame-Type: §e"+ gameType+ " §aWorld: §e"+ worldName+ " §aMapName: §e"+ mapName);
    }
}
