package de.gerolmed.mru.game;

import de.gerolmed.mru.utils.BlockTracer;
import de.gerolmed.mru.utils.ItemUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public enum GameType {
    ONE_LINE(ItemUtils.createItem(Material.STICK,"§6Oneline", null), false);

    private ItemStack itemStack;
    private boolean canDamage;

    GameType(ItemStack itemStack, boolean canDamage) {

        this.canDamage = canDamage;
        this.itemStack = itemStack;
    }

    public boolean placeBlock(Player player, Location location) {
        return GameManager.getInstance().getCurrentMap().getRegion().isInArea(location.toVector());
    }
    public boolean destroyBlock(Player player, Location location) {
        return BlockTracer.getInstance().hasChanges(location);
    }
    public void dieVoid(GameManager gameManager, Player player) {

    }
    public void diePlayer(GameManager gameManager, Player killer, Player killed) {

    }

    public void start(GameManager gameManager) {
        gameManager.getTeamRed().getMlgPlayer().getPlayer().teleport(gameManager.getCurrentMap().getRedTeam().getTeamSpawn().toLocation());
        gameManager.getTeamBlue().getMlgPlayer().getPlayer().teleport(gameManager.getCurrentMap().getBlueTeam().getTeamSpawn().toLocation());
    }

    public ItemStack toItem() {
        return itemStack;
    }
    public boolean canDamage() {
        return canDamage;
    }
}
