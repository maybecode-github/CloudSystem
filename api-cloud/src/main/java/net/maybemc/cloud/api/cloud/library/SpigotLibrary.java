package net.maybemc.cloud.api.cloud.library;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Getter
@AllArgsConstructor
public class SpigotLibrary {

    private File path;
    private ServerType serverType;

    private final Logger logger = LoggerFactory.getLogger(SpigotLibrary.class);

    public void downloadPaper() {
        logger.info("trying to download paper...");
        if (path.exists()) {
            logger.info("Paper was found! Skipping download...");
            return;
        }
        path.getParentFile().mkdirs();
        try {
            InputStream inputStream = new URL(ServerType.getDownloadPath(serverType)).openStream();
            Files.copy(inputStream, path.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
