package de.gerolmed.mru.commands;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.map.Map;
import de.gerolmed.mru.map.gui.edit.EditMapGui;
import de.gerolmed.mru.map.gui.setup.SetupGui;
import de.gerolmed.mru.utils.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EditCommand extends SubCommand {

    public EditCommand(Main main) {
        super(main, "edit", "mlg.admin", "/mlg edit <mapname>");
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("You need to be player to do this!");
            return true;
        }
        if(args.length == 0) {
            sender.sendMessage("§cUsage: "+ getUsage());
            return true;
        }

        String mapName = args[0];

        Map map = plugin.getMapManager().getMap(mapName);

        if(map == null) {
            sender.sendMessage("§cMap not found!");
            return true;
        }

        Player player = (Player) sender;
        new EditMapGui(player, map).openGui();
        return true;
    }
}
