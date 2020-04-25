package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JaggedArrayIterator<T> implements Iterator<T> {
    private int row = 0;
    private int col = 0;
    private T[][] array;

    public JaggedArrayIterator(T[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        while (row < array.length && array[row].length == 0) {
            row++;
        }
        return row < array.length && col < array[row].length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T rsl = array[row][col++];
        if (col == array[row].length) {
            row++;
            col = 0;
        }
        return rsl;
    }
}
