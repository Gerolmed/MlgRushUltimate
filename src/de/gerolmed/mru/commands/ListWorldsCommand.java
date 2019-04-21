package de.gerolmed.mru.commands;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.map.Map;
import de.gerolmed.mru.player.MlgPlayer;
import de.gerolmed.mru.player.MlgPlayerManager;
import de.gerolmed.mru.utils.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListWorldsCommand extends SubCommand {

    public ListWorldsCommand(Main main) {
        super(main, "list", "mlg.admin", "/mlg list");
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("You need to be player to do this!");
            return true;
        }

        sender.sendMessage("   ");
        sender.sendMessage("§7======§a[§6MlgRushUltimate§a]§7======");
        sender.sendMessage("  §6Here is a list of all worlds:");
        for(Map map : plugin.getMapManager().getMaps())
            sender.sendMessage(String.format("   - §a%s §f(§7%s§f) §aMode: §b%s", map.getWorldName(), map.getDisplayName(), map.getGameType().toString()));
        sender.sendMessage("§7==========================");
        sender.sendMessage("   ");

        return true;
    }
}
