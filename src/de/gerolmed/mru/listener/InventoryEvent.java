package de.gerolmed.mru.listener;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.player.MlgPlayer;
import de.gerolmed.mru.player.MlgPlayerManager;
import de.gerolmed.mru.utils.BasicEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryEvent extends BasicEvent {

    public InventoryEvent(Main main) {
        super(main);
    }


    @EventHandler(priority = EventPriority.HIGH)
    public void onGuiClick(InventoryClickEvent event) {
        if(event.isCancelled())
            return;
        Player player = (Player) event.getWhoClicked();

        MlgPlayer mlgPlayer = MlgPlayerManager.getPlayer(player.getUniqueId());

        String invName = null;
        ItemStack item = event.getCurrentItem();


        if((item == null || item.getType() == Material.AIR) && event.getCursor() != null)
            item = event.getCursor();

        if(event.getClickedInventory() != null)
            invName = event.getClickedInventory().getTitle();

        if(mlgPlayer != null && invName != null)
        {
            int slot = event.getSlot();

            if(mlgPlayer.hasOpenedGui() && mlgPlayer.getGui().getName().equals(invName) && mlgPlayer.getGui().handleClick(item, slot, event.getClick()))
                event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        MlgPlayer mlgPlayer = MlgPlayerManager.getPlayer(player.getUniqueId());

        if(mlgPlayer != null && mlgPlayer.hasOpenedGui() ) {
            //TODO: test for correct inventory
            mlgPlayer.getGui().closeInventory();
        }

    }

}