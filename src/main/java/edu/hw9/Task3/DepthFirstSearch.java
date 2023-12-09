package edu.hw9.Task3;

import java.io.File;
import java.util.concurrent.Callable;

public class DepthFirstSearch implements Callable<Boolean> {

    private final File rootDirectory;
    private final String targetFileName;

    public DepthFirstSearch(File rootDirectory, String targetFileName) {
        this.rootDirectory = rootDirectory;
        this.targetFileName = targetFileName;
    }

    @Override
    public Boolean call() {
        return search(rootDirectory, targetFileName);
    }

    public boolean search(File currentFile, String targetFileName) {
        if (currentFile.isDirectory()) {
            File[] files = currentFile.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (search(file, targetFileName)) {
                        return true;
                    }
                }
            }
        } else if (currentFile.getName().equals(targetFileName)) {
            return true;
        }
        return false;
    }
}
