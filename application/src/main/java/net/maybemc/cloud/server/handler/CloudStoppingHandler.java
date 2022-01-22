package net.maybemc.cloud.server.handler;

import net.maybemc.cloud.api.cloud.entity.server.CloudServer;
import net.maybemc.cloud.http.client.CloudHttpClient;
import net.maybemc.cloud.server.command.CommandConsole;
import net.maybemc.cloud.server.command.CommandManager;
import net.maybemc.cloud.server.processor.AnnotationProcessor;
import net.maybemc.cloud.server.server.ServerCreator;
import net.maybemc.cloud.server.server.ServerStarter;
import net.maybemc.cloud.service.provider.ServiceProvider;
import net.maybemc.cloud.template.TemplatePullManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

public class CloudStoppingHandler {

    public void addShutdownHook(CloudHook cloudHook) {
        Runtime.getRuntime().addShutdownHook(cloudHook);
        new Thread(new Process()).start();
    }

    public static class Process implements Runnable {

        private final Logger logger = LoggerFactory.getLogger(Process.class);
        private final CloudHttpClient cloudHttpClient = ServiceProvider.getService(CloudHttpClient.class);

        @Override
        public void run() {
            new LibraryHandler().indexLibraries();
            try {
                TemplatePullManager templatePullManager = new TemplatePullManager();
                Objects.requireNonNull(cloudHttpClient.getCloudGroupService().getCloudGroups().execute().body()).forEach(cloudGroup -> {
                    for (int i = 1; i < (cloudGroup.getMinServiceAmount() + 1); i++) {
                        CloudServer cloudServer = new CloudServer();
                        cloudServer.setCloudGroup(cloudGroup);
                        cloudServer.setServerName(cloudGroup.getGroupName() + "-" + i);
                        try {
                            cloudHttpClient.getCloudServerService().createCloudServer(cloudServer).execute();
                            new ServerCreator(cloudServer, templatePullManager).createServer();
                        } catch (IOException e) {
                            logger.warn(String.format("could not create server %s", cloudServer.getServerName()));
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            new AnnotationProcessor(new CommandManager()).processAnnotations();
            new CommandConsole().processConsole();
        }
    }

    public static class CloudHook extends Thread {

        private final Logger logger = LoggerFactory.getLogger(CloudStoppingHandler.class);
        private final CloudHttpClient cloudHttpClient = ServiceProvider.getService(CloudHttpClient.class);

        @Override
        public void run() {
            logger.info("shutting down cloud...");

            try {
                Objects.requireNonNull(cloudHttpClient.getCloudServerService().getAllCloudServers().execute().body()).forEach(cloudServer -> {
                    try {
                        cloudHttpClient.getCloudServerService().deleteCloudServer(cloudServer.getServerName()).execute();
                        new ServerStarter().stopServer(cloudServer);
                    } catch (IOException e) {
                        logger.warn("could not delete server " + cloudServer.getServerName());
                    }
                    logger.info("deleted " + cloudServer.getServerName());
                });
            } catch (IOException e) {
                logger.error("There was an error while stopping cloud servers! Please clear the database!");
            }
        }
    }

}
