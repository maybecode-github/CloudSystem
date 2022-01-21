package net.maybemc.cloud.server.handler;

import de.maybecode.mbcache.CacheConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.maybemc.cloud.api.cloud.library.ServerType;
import net.maybemc.cloud.api.cloud.library.SpigotLibrary;

import java.io.File;

@Getter
@AllArgsConstructor
public class LibraryHandler {

    private final CacheConfig config;

    public void indexLibraries() {
        SpigotLibrary spigotLibrary = new SpigotLibrary(new File(getSpigotTemplate() + getSpigotJar()),
                ServerType.PAPER_SPIGOT.setVersion("1.8.8"));
        spigotLibrary.downloadPaper();
    }

    private String getSpigotTemplate() {
        return config.getString("templates.spigot", "templates/spigot/");
    }

    private String getSpigotJar() {
        return config.getString("templates.spigotJar", "spigot.jar");
    }

}
