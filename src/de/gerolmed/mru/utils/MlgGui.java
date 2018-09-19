package de.gerolmed.mru.utils;

import de.gerolmed.mru.player.MlgPlayer;
import de.gerolmed.mru.player.MlgPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public abstract class MlgGui {

    private Player player;
    private MlgPlayer mlgPlayer;
    private String name;

    public MlgGui(Player player, String name) {
        this.player = player;
        this.mlgPlayer = MlgPlayerManager.getPlayer(player.getUniqueId());
        this.name = name;
    }

    public String getName() {
        return name;
    }
    protected Player getPlayer() {
        return player;
    }
    protected MlgPlayer getMlgPlayer() {
        return mlgPlayer;
    }

    public void openGui() {
        getMlgPlayer().setOpenGui(this);
        showGui();
    }

    public abstract void showGui();

    /**
     * Handle a click in the gui
     * @param itemStack
     * @param clickType
     * @return - true if action should be canceled
     */
    public abstract boolean handleClick(ItemStack itemStack, int slot, ClickType clickType);

    /**
     * Is called when inventory is closed
     */
    public abstract void closeInventory();

}
