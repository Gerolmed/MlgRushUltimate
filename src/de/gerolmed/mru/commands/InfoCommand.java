package de.gerolmed.mru.commands;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.utils.SubCommand;
import org.bukkit.command.CommandSender;

public class InfoCommand extends SubCommand {

    public InfoCommand(Main main) {
        super(main, "info", "", "/mlg info");
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        sender.sendMessage("   ");
        sender.sendMessage("§7======§a[§6MlgRushUltimate§a]§7======");
        sender.sendMessage("  §6Version: §a" + plugin.getDescription().getVersion());
        sender.sendMessage("  §6Author: §a" + plugin.getDescription().getAuthors());
        sender.sendMessage("§7==========================");
        sender.sendMessage("   ");

        return true;
    }
}
