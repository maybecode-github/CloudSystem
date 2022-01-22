package net.maybemc.cloud.server.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.maybemc.cloud.api.cloud.entity.server.CloudServer;
import net.maybemc.cloud.server.handler.SettingsHandler;
import net.maybemc.cloud.service.provider.ServiceProvider;
import net.maybemc.cloud.template.TemplateManager;
import net.maybemc.cloud.template.TemplatePullManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Getter
@AllArgsConstructor
public class ServerCreator extends ServerStarter {

    private final CloudServer cloudServer;
    private final TemplatePullManager templatePullManager;

    private final Logger logger = LoggerFactory.getLogger(ServerCreator.class);
    private final SettingsHandler settingsHandler = ServiceProvider.getService(SettingsHandler.class);
    private final TemplateManager templateManager = ServiceProvider.getService(TemplateManager.class);

    public void createServer() {
        loadFiles();
        startServer(cloudServer);
    }

    public void loadFiles() {
        try {
            templatePullManager.pullTemplateIntoDirectory(templateManager.getSpigotJarPath(),
                    settingsHandler.getServerPath(cloudServer));
            logger.info(String.format("pulled template for server %s", cloudServer.getServerName()));
        } catch (IOException e) {
            logger.error(String.format("There was an error while fetching server template for server "
                    + cloudServer.getServerName() + ": %s", e.getMessage()));
        }
    }

}
