package net.maybemc.cloud.template;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class TemplatePullManager {

    private final Logger logger = LoggerFactory.getLogger(TemplatePullManager.class);

    public void pullTemplateIntoDirectory(Path templatePath, Path targetDirectory) throws IOException {
        createDirectories(templatePath);
        logger.info("cloning server " + templatePath.toString() + " to " + targetDirectory.toString());
        FileUtils.copyDirectory(templatePath.toFile(), targetDirectory.toFile());
        logger.info("finished cloning server!");
    }

    public void pullTemplateIntoDirectory(String templatePath, String targetDirectory) throws IOException {
        pullTemplateIntoDirectory(new File(templatePath).toPath(), new File(targetDirectory).toPath());
    }

    private void createDirectories(Path... paths) {
        Arrays.stream(paths).forEach(path -> {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
