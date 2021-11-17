package net.maybemc.cloud.server.command;

import jline.console.ConsoleReader;
import jline.console.completer.FileNameCompleter;
import lombok.SneakyThrows;

import java.util.Arrays;

public class CommandConsole {

    private final CommandManager commandManager = new CommandManager();
    private static ConsoleReader CONSOLE_READER;

    @SneakyThrows
    public CommandConsole() {
        if (CONSOLE_READER == null) {
            CONSOLE_READER = new ConsoleReader();
            CONSOLE_READER.addCompleter(new FileNameCompleter());
        }
    }

    @SneakyThrows
    public void processConsole() {
        CONSOLE_READER.setPrompt("command: ");
        String command = CONSOLE_READER.readLine();
        String[] args = command.split(" ");
        String commandName = args[0];
        try {
            ICloudCommand cloudCommand = commandManager.getICloudCommand(commandName);
            cloudCommand.getClass().newInstance().onExecute(Arrays.copyOfRange(args, 1, args.length));
            processConsole();
        } catch (NullPointerException exception) {
            System.out.println("the command " + commandName + " could not be found.");
            processConsole();
        }
    }

}
