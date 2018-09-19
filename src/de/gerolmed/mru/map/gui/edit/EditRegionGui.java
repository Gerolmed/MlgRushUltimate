package de.gerolmed.mru.map.gui.edit;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.map.Map;
import de.gerolmed.mru.map.MapRegion;
import de.gerolmed.mru.utils.ItemUtils;
import de.gerolmed.mru.utils.MlgGui;
import de.gerolmed.mru.utils.SoulsLocation;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EditRegionGui extends MlgGui {

    private Inventory inventory;
    private MapRegion region;
    private MlgGui previousGui;

    private String back = "§6Previous", pos1 = "§6Set first position", pos2 = "§6Set second position";

    public EditRegionGui(Player player, MapRegion region, MlgGui previousGui) {
        super(player, "Edit region");
        this.region = region;
        this.previousGui = previousGui;
        inventory = Bukkit.createInventory(null, 9*1, getName());

        fillInventory();

    }

    private void fillInventory() {

        for(int i = 0; i < inventory.getSize(); i++)
        {
            inventory.setItem(i, ItemUtils.spacer(ItemUtils.GlassColor.LIGHT_GRAY));
        }

        inventory.setItem(0, ItemUtils.createSkull(back, "MHF_ArrowLeft",null));
        inventory.setItem(2, ItemUtils.createItem(Material.CLAY_BALL, pos1, "§7(X: "+region.getLocation1().getX() + " Y: "+region.getLocation1().getY() + " Z: "+region.getLocation1().getZ()+")"));
        inventory.setItem(3, ItemUtils.createItem(Material.CLAY_BALL, pos2, "§7(X: "+region.getLocation2().getX() + " Y: "+region.getLocation2().getY() + " Z: "+region.getLocation2().getZ()+")"));
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

        if(itemName.equals(back)) {
            previousGui.openGui();
            return true;
        }

        if(itemName.equals(pos1)) {
            SoulsLocation location = new SoulsLocation(getPlayer().getLocation().getBlock().getLocation());
            getPlayer().sendMessage("§6First position set to X: "+location.getX() + " Y: "+location.getY() + " Z: "+location.getZ());
            region.setLocation1(location);
            Main.getInstance().getMapManager().save();
            fillInventory();
            return true;
        }
        if(itemName.equals(pos2)) {
            SoulsLocation location = new SoulsLocation(getPlayer().getLocation().getBlock().getLocation());
            getPlayer().sendMessage("§6Second position set to X: "+location.getX() + " Y: "+location.getY() + " Z: "+location.getZ());
            region.setLocation2(location);
            Main.getInstance().getMapManager().save();
            fillInventory();
            return true;
        }

        return true;
    }

    @Override
    public void closeInventory() {
        if(getPlayer().getOpenInventory() == null)
            getPlayer().sendMessage("§c§lINFO: §7You can return to the last menu with /mlg last");
    }
}
