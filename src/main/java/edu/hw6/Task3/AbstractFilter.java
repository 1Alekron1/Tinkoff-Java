package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    boolean accept(Path path) throws IOException;

    default AbstractFilter and(AbstractFilter other) {
        return path -> this.accept(path) && other.accept(path);
    }

    default AbstractFilter or(AbstractFilter other) {
        return path -> this.accept(path) || other.accept(path);
    }

    static AbstractFilter regularFile() {
        return Files::isRegularFile;
    }

    static AbstractFilter readable() {
        return Files::isReadable;
    }

    static AbstractFilter writable() {
        return Files::isWritable;
    }

    static AbstractFilter executable() {
        return Files::isExecutable;
    }

    static AbstractFilter sizeGreaterThan(long size) {
        return path -> {
            try {
                return Files.size(path) > size;
            } catch (IOException e) {
                return false;
            }
        };
    }

    static AbstractFilter sizeLessThan(long size) {
        return path -> {
            try {
                return Files.size(path) < size;
            } catch (IOException e) {
                return false;
            }
        };
    }

    static AbstractFilter hasExtension(String extension) {
        return path -> path.toString().toLowerCase().endsWith("." + extension.toLowerCase());
    }

    static AbstractFilter regexMatches(String regex) {
        return path -> path.getFileName().toString().matches(regex);
    }

    static AbstractFilter magicNumber(byte... magicBytes) {
        return path -> {
            try {
                byte[] fileBytes = Files.readAllBytes(path);
                if (fileBytes.length >= magicBytes.length) {
                    for (int i = 0; i < magicBytes.length; i++) {
                        if (fileBytes[i] != magicBytes[i]) {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (IOException e) {
            }
            return false;
        };
    }

    static AbstractFilter globMatches(String glob) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
        return path -> pathMatcher.matches(path.getFileName());
    }
}
