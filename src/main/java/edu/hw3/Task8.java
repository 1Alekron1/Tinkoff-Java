package edu.hw3;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Task8 {

    private Task8() {

    }

    public static class BackwardIterator<T> implements Iterator<T> {
        private final List<T> elements;
        private int index;

        public BackwardIterator(Collection<T> collection) {
            this.elements = List.copyOf(collection);
            this.index = elements.size() - 1;
        }

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements.get(index--);
        }
    }
}
