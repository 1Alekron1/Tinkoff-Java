package edu.hw9.Task3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DepthFirstSearch extends RecursiveTask<Boolean> {

    private final File rootDirectory;
    private final String targetFileName;

    public DepthFirstSearch(File rootDirectory, String targetFileName) {
        this.rootDirectory = rootDirectory;
        this.targetFileName = targetFileName;
    }

    @Override
    protected Boolean compute() {
        return search(rootDirectory, targetFileName);
    }

    private boolean search(File currentFile, String targetFileName) {
        if (currentFile.isDirectory()) {
            List<DepthFirstSearch> subtasks = new ArrayList<>();
            File[] files = currentFile.listFiles();

            if (files != null) {
                for (File file : files) {
                    DepthFirstSearch subtask = new DepthFirstSearch(file, targetFileName);
                    subtasks.add(subtask);
                    subtask.fork();
                }
            }

            for (DepthFirstSearch subtask : subtasks) {
                if (subtask.join()) {
                    return true;
                }
            }
        } else if (currentFile.getName().equals(targetFileName)) {
            return true;
        }
        return false;
    }
}
