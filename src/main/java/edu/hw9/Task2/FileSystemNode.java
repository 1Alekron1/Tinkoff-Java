package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSystemNode {
    private File file;
    private List<FileSystemNode> children;

    public FileSystemNode(File file) {
        this.file = file;
        this.children = new ArrayList<>();
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles != null) {
                for (File childFile : childFiles) {
                    children.add(new FileSystemNode(childFile));
                }
            }
        }
    }

    public File getFile() {
        return file;
    }

    public List<FileSystemNode> getChildren() {
        return children;
    }
}
