package net.maybemc.cloud.server.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.maybemc.cloud.api.cloud.library.ServerType;
import net.maybemc.cloud.api.cloud.library.SpigotLibrary;
import net.maybemc.cloud.service.provider.ServiceProvider;

import java.io.File;

@Getter
@AllArgsConstructor
public class LibraryHandler {

    private final SettingsHandler settingsHandler = ServiceProvider.getService(SettingsHandler.class);

    public void indexLibraries() {
        SpigotLibrary spigotLibrary = new SpigotLibrary(new File(settingsHandler.getSpigotTemplate() + settingsHandler.getSpigotJar()),
                ServerType.PAPER_SPIGOT.setVersion("1.8.8"));
        spigotLibrary.downloadPaper();
    }

}
