package net.maybemc.cloud.server.server;

import net.maybemc.cloud.api.cloud.entity.server.CloudServer;
import net.maybemc.cloud.server.server.process.ProcessProvider;
import net.maybemc.cloud.server.server.process.ServerProcess;
import net.maybemc.cloud.service.provider.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ServerStarter {

    private final Logger logger = LoggerFactory.getLogger(ServerStarter.class);
    private final ProcessProvider processProvider = ServiceProvider.getService(ProcessProvider.class);

    public void startServer(CloudServer cloudServer) {
        ServerProcess serverProcess = new ServerProcess(cloudServer);
        logger.info(String.format("trying to start server %s", cloudServer.getServerName()));
        try {
            serverProcess.startProcess();
            logger.info(String.format("server %s started successfully!", cloudServer.getServerName()));
        } catch (IOException e) {
            logger.info(String.format("There was an error while starting the server! %s", e.getMessage()));
        }
    }

    public void stopServer(CloudServer cloudServer) {
        processProvider.getProcess(cloudServer.getServerName()).destroy();
        processProvider.removeProcess(cloudServer.getServerName());
        logger.info(String.format("Server %s was stopped successfully!", cloudServer.getServerName()));
    }

}
