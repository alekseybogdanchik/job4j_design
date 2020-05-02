package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int position = 0;

    public SimpleArray(int arraySize) {
        this.array = new Object[arraySize];
    }

    public void add(T model) {
        if (position < array.length) {
            array[position++] = model;
        } else {
            throw new IndexOutOfBoundsException("Array is full");
        }
    }

    public void set(int index, T model) {
        int indexChecked = Objects.checkIndex(index, position);
        array[indexChecked] = model;
    }

    public void remove(int index) {
        int indexChecked = Objects.checkIndex(index, position);
        array[indexChecked] = null;
        int nextCell = index + 1;
        int length = position - nextCell;
        if (length > 0) {
            System.arraycopy(array, nextCell, array, index, length);
        }
        position--;
    }

    public T get(int index) {
        int indexChecked = Objects.checkIndex(index, position);
        return (T) array[indexChecked];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < position;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[index++];
            }
        };
    }
}
