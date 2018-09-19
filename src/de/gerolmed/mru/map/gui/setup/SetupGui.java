package de.gerolmed.mru.map.gui.setup;

import de.gerolmed.mru.game.GameType;
import de.gerolmed.mru.utils.ItemUtils;
import de.gerolmed.mru.utils.MlgGui;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SetupGui extends MlgGui {

    private Inventory inventory;

    public SetupGui(Player player) {
        super(player, "Choose Game-Type");
        inventory = Bukkit.createInventory(null, 9*1, getName());

        fillInventory();

    }

    private void fillInventory() {

        for(int i = 0; i < inventory.getSize(); i++)
        {
            inventory.setItem(i, ItemUtils.spacer(ItemUtils.GlassColor.LIGHT_GRAY));
        }

        int slot = 0;
        for(GameType gameType : GameType.values()) {
            inventory.setItem(slot, gameType.toItem());
            slot++;
        }

    }

    @Override
    public void showGui() {
        getPlayer().openInventory(inventory);
    }

    @Override
    public boolean handleClick(ItemStack itemStack, int slot, ClickType clickType) {

        if(!(itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName()))
            return true;

        String itemName = itemStack.getItemMeta().getDisplayName();

        GameType selectedGameType = null;

        for(GameType gameType : GameType.values())
            if (gameType.toItem().getItemMeta().getDisplayName().equals(itemName)) {
                selectedGameType = gameType;
                break;
            }
        if(selectedGameType == null)
            return true;

        getPlayer().closeInventory();

        new WorldNameSelection(getPlayer(), selectedGameType);

        return true;
    }

    @Override
    public void closeInventory() {

    }
}
