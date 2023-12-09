package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FileSearchTask extends RecursiveTask<List<File>> {
    private final FileSystemNode node;
    private final FilePredicate predicate;

    public FileSearchTask(FileSystemNode node, FilePredicate predicate) {
        this.node = node;
        this.predicate = predicate;
    }

    @Override
    protected List<File> compute() {
        List<File> result = new ArrayList<>();

        if (node.getFile().isDirectory()) {
            List<FileSearchTask> subtasks = createSubtasks();
            for (FileSearchTask subtask : subtasks) {
                subtask.fork();
            }

            for (FileSearchTask subtask : subtasks) {
                result.addAll(subtask.join());
            }
        } else {
            if (predicate.test(node.getFile())) {
                result.add(node.getFile());
            }
        }

        return result;
    }

    private List<FileSearchTask> createSubtasks() {
        List<FileSearchTask> subtasks = new ArrayList<>();
        for (FileSystemNode child : node.getChildren()) {
            subtasks.add(new FileSearchTask(child, predicate));
        }
        return subtasks;
    }
}
