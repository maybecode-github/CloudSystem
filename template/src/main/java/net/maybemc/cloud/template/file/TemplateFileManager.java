package net.maybemc.cloud.template.file;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TemplateFileManager {

    public List<File> findFilesInDirectory(String directory) {
        return findFilesInDirectory(new File(directory));
    }

    public List<File> findFilesInDirectory(File directory) {
        return Arrays.stream(Objects.requireNonNull(directory.listFiles())).collect(Collectors.toList());
    }

}
