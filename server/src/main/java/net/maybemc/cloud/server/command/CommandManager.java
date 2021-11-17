package net.maybemc.cloud.server.command;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CommandManager {

    private final static Map<CloudCommand, ICloudCommand> COMMANDS = new ConcurrentHashMap<>();

    @SneakyThrows
    public void registerCommand(Class<? extends ICloudCommand> clazz) {
        COMMANDS.putIfAbsent(clazz.getAnnotation(CloudCommand.class), clazz.newInstance());
    }

    public CloudCommand getCommand(String name) {
        if (COMMANDS.keySet().stream().anyMatch(match -> match.name().equalsIgnoreCase(name))
                || (COMMANDS.keySet().stream().anyMatch(match ->
                Arrays.stream(match.aliases()).anyMatch(match1 -> match1.equalsIgnoreCase(name))))) {
            return findByName(name);
        }
        return findByName("help");
    }

    public ICloudCommand getICloudCommand(String name) {
        return COMMANDS.get(getCommand(name));
    }

    private CloudCommand findByName(String name) {
        if (COMMANDS.keySet().stream().anyMatch(match -> match.name().equalsIgnoreCase(name))) {
            return COMMANDS.keySet().stream().filter(filter -> filter.name().equalsIgnoreCase(name)).findFirst().orElse(null);
        }
        return COMMANDS.keySet().stream().filter(filter ->
                Arrays.stream(filter.aliases()).anyMatch(match -> match.equalsIgnoreCase(name))).findFirst().orElse(null);
    }

    public static Map<CloudCommand, ICloudCommand> getCommands() {
        return COMMANDS;
    }
}
