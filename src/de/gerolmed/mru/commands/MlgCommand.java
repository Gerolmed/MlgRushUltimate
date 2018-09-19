package de.gerolmed.mru.commands;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.utils.BasicCommand;
import de.gerolmed.mru.utils.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class MlgCommand extends BasicCommand {

    private ArrayList<SubCommand> subCommands;

    public MlgCommand(Main main) {
        super(main, "mlg");

        subCommands = new ArrayList<>();

        addSubCommand(new InfoCommand(main));
        addSubCommand(new CreateCommand(main));
        addSubCommand(new EditCommand(main));
        addSubCommand(new LastCommand(main));
    }

    private void addSubCommand(SubCommand subCommand) {
        subCommands.add(subCommand);
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if(args.length == 0) {
            sendHelp(sender);
            return true;
        }

        for(SubCommand subCommand : subCommands) {
            if(subCommand.getName().equalsIgnoreCase(args[0]) && sender.hasPermission(subCommand.getPermission())) {
                subCommand.execute(sender, alias, reduce(args));
                return true;
            }
        }

        sendHelp(sender);
        return true;
    }

    private String[] reduce(String[] args) {
        String[] output = new String[args.length-1];

        for(int i = 1; i < args.length; i++)
            output[i-1] = args[i];

        return output;
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage("   ");
        sender.sendMessage("§7======§a[§6MlgRushUltimate§a]§7======");
        for(SubCommand subCommand : subCommands) {
            if(sender.hasPermission(subCommand.getPermission()))
                sender.sendMessage("§7 - "+subCommand.getUsage());
        }
        sender.sendMessage("§7==========================");
        sender.sendMessage("   ");
    }
}
