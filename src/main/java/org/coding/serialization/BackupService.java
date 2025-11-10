package org.coding.serialization;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class BackupService {
    public static void createBackupOfFiles(Path pathToBackupFrom) throws IOException {
        if (!Files.exists(pathToBackupFrom)) {
            System.out.println("Provided path for backup does not exist.");
            return;
        }
        if (!Files.isDirectory(pathToBackupFrom)) {
            System.out.println("Provided path for backup is not directory");
            return;
        }

        Path backupFolder = pathToBackupFrom.toAbsolutePath().getParent()
                .resolve(pathToBackupFrom.getFileName() + "Backup");
        if (Files.notExists(backupFolder)) {
            Files.createDirectories(backupFolder);
        }

        try (Stream<Path> stream = Files.walk(pathToBackupFrom)) {
            stream.forEach(sourcePath -> {
                try {
                    Path targetPath = backupFolder.resolve(pathToBackupFrom.relativize(sourcePath));
                    if (Files.isDirectory(sourcePath)) {
                        if (!Files.exists(targetPath)) {
                            Files.createDirectories(targetPath);
                        }
                    } else {
                        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
