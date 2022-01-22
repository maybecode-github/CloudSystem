package net.maybemc.cloud.server.server.process;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.maybemc.cloud.api.cloud.entity.server.CloudServer;
import net.maybemc.cloud.api.cloud.library.ServerType;
import net.maybemc.cloud.server.handler.SettingsHandler;
import net.maybemc.cloud.service.provider.ServiceProvider;

import java.io.IOException;

@Getter
@RequiredArgsConstructor
public class ServerProcess {

    private Process process;
    private final CloudServer cloudServer;

    private final ProcessProvider processProvider = ServiceProvider.getService(ProcessProvider.class);
    private final SettingsHandler settingsHandler = ServiceProvider.getService(SettingsHandler.class);

    public void startProcess() throws IOException {
        String serverJar = settingsHandler.getServerPath(cloudServer) + (cloudServer.getCloudGroup().getServerType().equals(ServerType.PAPER_SPIGOT) ? "spigot.jar" : "bungee.jar");
        process = Runtime.getRuntime().exec("java -jar -Xmx" + cloudServer.getCloudGroup().getMaxRam() + "M " + serverJar);
        processProvider.startProcess(cloudServer.getServerName(), process);
    }

    public void stopProcess() {
        processProvider.getProcess(cloudServer.getServerName()).destroy();
        processProvider.removeProcess(cloudServer.getServerName());
    }

}
