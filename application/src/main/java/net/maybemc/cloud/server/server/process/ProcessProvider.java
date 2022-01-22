package net.maybemc.cloud.server.server.process;

import net.maybemc.cloud.service.provider.IServiceProvider;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ProcessProvider implements IServiceProvider {

    private final ConcurrentMap<String, Process> processMap = new ConcurrentHashMap<>();

    public void startProcess(String serverName, Process process) {
        processMap.putIfAbsent(serverName, process);
    }

    public Process getProcess(String serverName) {
        return processMap.get(serverName);
    }

    public void removeProcess(String serverName) {
        processMap.remove(serverName);
    }

}
