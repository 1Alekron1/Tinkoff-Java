package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Task2 {
    private Task2() {
    }

    public static void cloneFile(Path originalFilePath) throws IOException {
        Path directory = originalFilePath.getParent();
        String fileName = originalFilePath.getFileName().toString();
        String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
        String extension = fileName.substring(fileName.lastIndexOf('.'));

        long copyIndex = getCopyIndex(directory, baseName, extension) + 1;

        String copySuffix = (copyIndex == 1) ? " — копия" : " — копия (" + copyIndex + ")";
        String newFileName = baseName + copySuffix + extension;
        Path newFilePath = Paths.get(directory.toString(), newFileName);

        Files.copy(originalFilePath, newFilePath);
    }

    private static long getCopyIndex(Path directory, String baseName, String extension) throws IOException {
        try (Stream<Path> files = Files.list(directory)) {
            return files.filter(file -> file.getFileName().toString()
                    .matches(baseName + " — копия( \\(\\d+\\))?" + extension))
                .count();
        }
    }
}
