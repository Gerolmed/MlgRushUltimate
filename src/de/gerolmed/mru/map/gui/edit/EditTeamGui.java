package de.gerolmed.mru.map.gui.edit;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.map.MapRegion;
import de.gerolmed.mru.map.MapTeam;
import de.gerolmed.mru.utils.ItemUtils;
import de.gerolmed.mru.utils.MlgGui;
import de.gerolmed.mru.utils.SoulsLocation;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EditTeamGui extends MlgGui {

    private Inventory inventory;
    private MapTeam team;
    private MlgGui previousGui;

    private String back = "§6Previous", editRegion = "§6Edit team region", setSpawn = "§6Set spawn position";

    public EditTeamGui(Player player, MapTeam team, MlgGui previousGui) {
        super(player, "Edit team");
        this.team = team;
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
        inventory.setItem(2, ItemUtils.createItem(Material.CLAY_BALL, setSpawn, "§7(X: "+team.getTeamSpawn().getX() + " Y: "+team.getTeamSpawn().getY() + " Z: "+team.getTeamSpawn().getZ()+")"));
        inventory.setItem(3, ItemUtils.createItem(Material.FENCE, editRegion, null));
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

        if(itemName.equals(setSpawn)) {
            SoulsLocation location = new SoulsLocation(getPlayer().getLocation().getBlock().getLocation());
            getPlayer().sendMessage("§6Spawn position set to X: "+location.getX() + " Y: "+location.getY() + " Z: "+location.getZ());
            team.setTeamSpawn(location);
            Main.getInstance().getMapManager().save();
            fillInventory();
            return true;
        }
        if(itemName.equals(editRegion)) {
            new EditRegionGui(getPlayer(), team.getMapRegion(), this).openGui();
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
