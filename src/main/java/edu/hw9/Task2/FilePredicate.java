package edu.hw9.Task2;

import java.io.File;

@FunctionalInterface
public interface FilePredicate {
    boolean test(File file);
}
