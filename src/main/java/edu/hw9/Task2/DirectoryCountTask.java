package edu.hw9.Task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DirectoryCountTask extends RecursiveTask<List<File>> {
    public static final int MAX_FILES = 1000;
    private final FileSystemNode node;
    private final static Logger LOGGER = LogManager.getLogger();

    public DirectoryCountTask(FileSystemNode node) {
        this.node = node;
    }

    @Override
    protected List<File> compute() {
        List<File> directoriesWithMoreThan1000Files = new ArrayList<>();

        if (node.getFile().isDirectory()) {
            try {
                long countInCurrentDirectory = Files.list(node.getFile().toPath()).count();

                if (countInCurrentDirectory > MAX_FILES) {
                    directoriesWithMoreThan1000Files.add(node.getFile());
                }

                List<DirectoryCountTask> subtasks = createSubtasks();
                invokeAll(subtasks);

                for (DirectoryCountTask subtask : subtasks) {
                    List<File> subtaskResults = subtask.join();
                    directoriesWithMoreThan1000Files.addAll(subtaskResults);
                }

            } catch (IOException e) {
                LOGGER.info(e.getStackTrace());
            }
        }

        return directoriesWithMoreThan1000Files;
    }

    private List<DirectoryCountTask> createSubtasks() {
        List<DirectoryCountTask> subtasks = new ArrayList<>();
        for (FileSystemNode child : node.getChildren()) {
            subtasks.add(new DirectoryCountTask(child));
        }
        return subtasks;
    }
}
