package org.coding.serialization;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class BackupService {
    public static void createBackupOfFiles(Path sourcePath) throws IOException {
        if (!Files.exists(sourcePath)) {
            throw new InvalidSourceDirectoryException("Provided path for backup does not exist.");
        }

        Path backupFolder = sourcePath.toAbsolutePath().getParent()
                .resolve(sourcePath.getFileName() + "_" + "Backup");
        if (Files.notExists(backupFolder)) {
            Files.createDirectories(backupFolder);
        }

        if (Files.isRegularFile(sourcePath)) {
            Path target = backupFolder.resolve(sourcePath.getFileName());
            Files.copy(sourcePath, target, StandardCopyOption.REPLACE_EXISTING);
            return;
        }

        try (Stream<Path> stream = Files.walk(sourcePath)) {
            stream.forEach(path -> {
                try {
                    Path targetPath = backupFolder.resolve(sourcePath.relativize(path));
                    Files.copy(path, targetPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    LogUtils.error("Failed to copy file: " + path, "BackupService");
                }
            });
        }
    }
}
