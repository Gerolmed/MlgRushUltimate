package de.gerolmed.mru.commands;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.map.Map;
import de.gerolmed.mru.map.gui.edit.EditMapGui;
import de.gerolmed.mru.player.MlgPlayer;
import de.gerolmed.mru.player.MlgPlayerManager;
import de.gerolmed.mru.utils.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LastCommand extends SubCommand {

    public LastCommand(Main main) {
        super(main, "last", "mlg.admin", "/mlg last");
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("You need to be player to do this!");
            return true;
        }

        Player player = (Player) sender;
        MlgPlayer mlgPlayer = MlgPlayerManager.getPlayer(player.getUniqueId());
        if(mlgPlayer.getGui() == null) {
            sender.sendMessage("§cYou didn't open a mlg gui!");
            return true;
        }
        mlgPlayer.getGui().openGui();
        return true;
    }
}
