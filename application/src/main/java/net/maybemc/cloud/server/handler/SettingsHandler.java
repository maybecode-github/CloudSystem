package net.maybemc.cloud.server.handler;

import de.maybecode.mbcache.CacheConfig;
import lombok.AllArgsConstructor;
import net.maybemc.cloud.api.cloud.entity.server.CloudServer;
import net.maybemc.cloud.service.provider.IServiceProvider;

@AllArgsConstructor
public class SettingsHandler implements IServiceProvider {

    private final CacheConfig config;

    public String getSpigotTemplate() {
        return config.getString("templates.spigot", "templates/spigot/");
    }

    public String getSpigotJar() {
        return config.getString("templates.spigotJar", "spigot.jar");
    }

    public String getServerPath(CloudServer cloudServer) {
        return config.getString("paths.server." + cloudServer.getCloudGroup().getServerMode().name(),
                "running/" + cloudServer.getCloudGroup().getServerMode().name() + "/") + cloudServer.getServerName() + "/";
    }
}
