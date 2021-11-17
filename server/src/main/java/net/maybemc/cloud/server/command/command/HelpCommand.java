package net.maybemc.cloud.server.command.command;

import net.maybemc.cloud.server.command.CloudCommand;
import net.maybemc.cloud.server.command.CommandManager;
import net.maybemc.cloud.server.command.ICloudCommand;

import java.util.Arrays;

@CloudCommand(name = "help")
public class HelpCommand implements ICloudCommand {
    @Override
    public void onExecute(String[] args) {
        System.out.println("help command");
        CommandManager.getCommands().keySet().forEach(command ->
                System.out.println(command.name() + " | " + command.description() + " | " + Arrays.toString(command.aliases())));
    }
}
