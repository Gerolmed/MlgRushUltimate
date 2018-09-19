package de.gerolmed.mru.map.gui.edit;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.game.GameType;
import de.gerolmed.mru.map.Map;
import de.gerolmed.mru.map.gui.setup.WorldNameSelection;
import de.gerolmed.mru.utils.ItemUtils;
import de.gerolmed.mru.utils.MlgGui;
import de.gerolmed.mru.utils.SoulsLocation;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EditMapGui extends MlgGui {

    private Inventory inventory;
    private Map map;

    private String editRegion = "§6Edit Region",
            editTeamRed= "§6Edit §4Red §6Team",
            editTeamBlue= "§6Edit §1Blue §6Team",
            setSpectatorPosition = "§6Set spectator position";

    public EditMapGui(Player player, Map map) {
        super(player, "Edit map");
        this.map = map;
        inventory = Bukkit.createInventory(null, 9*1, getName());

        fillInventory();

    }

    private void fillInventory() {

        for(int i = 0; i < inventory.getSize(); i++)
        {
            inventory.setItem(i, ItemUtils.spacer(ItemUtils.GlassColor.LIGHT_GRAY));
        }

        inventory.setItem(0, ItemUtils.createItem(Material.FENCE, editRegion, null));
        inventory.setItem(1, ItemUtils.createItem(Material.CLAY_BALL, setSpectatorPosition, "§7(X: "+map.getSpectatorPosition().getX() + " Y: "+map.getSpectatorPosition().getY() + " Z: "+map.getSpectatorPosition().getZ()+")"));

        inventory.setItem(3, ItemUtils.createItem(Material.WOOL, editTeamRed, null, (short)14));
        inventory.setItem(4, ItemUtils.createItem(Material.WOOL, editTeamBlue, null, (short)11));
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

        if(itemName.equals(editRegion)) {
            new EditRegionGui(getPlayer(), map.getRegion(), this).openGui();
            return true;
        }

        if(itemName.equals(setSpectatorPosition)) {
            SoulsLocation location = new SoulsLocation(getPlayer().getLocation().getBlock().getLocation());
            getPlayer().sendMessage("§6Spectator position set to X: "+location.getX() + " Y: "+location.getY() + " Z: "+location.getZ());
            map.setSpectatorPosition(location);
            Main.getInstance().getMapManager().save();
            fillInventory();
            return true;
        }

        if(itemName.equals(editTeamRed)) {
            new EditTeamGui(getPlayer(), map.getRedTeam(), this).openGui();
            return true;
        }

        if(itemName.equals(editTeamBlue)) {
            new EditTeamGui(getPlayer(), map.getBlueTeam(), this).openGui();
            return true;
        }

        return true;
    }

    @Override
    public void closeInventory() {

    }
}
