package net.maybemc.cloud.template;

import de.maybecode.mbcache.CacheConfig;
import lombok.AllArgsConstructor;
import net.maybemc.cloud.service.provider.IServiceProvider;

@AllArgsConstructor
public class TemplateManager implements IServiceProvider {

    private CacheConfig config;

    public String getTemplatePath() {
        return config.getString("template.path", "templates/");
    }

    public String getSpigotJarPath() {
        return config.getString("template.spigotJarPath", "templates/spigot/");
    }

    public String getGlobalSpigotPath() {
        return config.getString("template.globalSpigotPath", "templates/spigot/global/");
    }

    public String getGlobalBungeePath() {
        return config.getString("template.globalBungeePath", "templates/bungee/global/");
    }

    public String getStaticGamesPath() {
        return config.getString("template.globalGamesPath", "templates/games/global/");
    }

}
