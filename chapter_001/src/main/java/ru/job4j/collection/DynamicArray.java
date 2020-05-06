package ru.job4j.collection;

import java.util.*;


public class DynamicArray<T> implements Iterable<T> {

    private Object[] container;
    private int position = 0;
    private int modCount = 0;

    public DynamicArray(int arraySize) {
        this.container = new Object[arraySize];
    }

    public void add(T model) {
        if (position >= container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[position++] = model;
        modCount++;
    }

    public void set(int index, T model) {
        int indexChecked = Objects.checkIndex(index, position);
        container[indexChecked] = model;
    }

    public void remove(int index) {
        int indexChecked = Objects.checkIndex(index, position);
        container[indexChecked] = null;
        int nextCell = index + 1;
        int length = position - nextCell;
        if (length > 0) {
            System.arraycopy(container, nextCell, container, index, length);
        }
        position--;
        modCount++;
    }

    public T get(int index) {
        int indexChecked = Objects.checkIndex(index, position);
        return (T) container[indexChecked];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                    return index < position;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[index++];
            }
        };
    }
}
