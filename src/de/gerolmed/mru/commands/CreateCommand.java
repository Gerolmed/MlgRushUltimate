package de.gerolmed.mru.commands;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.map.gui.setup.SetupGui;
import de.gerolmed.mru.utils.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateCommand extends SubCommand {

    public CreateCommand(Main main) {
        super(main, "create", "mlg.admin", "/mlg create");
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("You need to be player to do this!");
            return true;
        }
        Player player = (Player) sender;
        new SetupGui(player).openGui();
        return true;
    }
}
