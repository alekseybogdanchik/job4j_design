package ru.job4j.collection;

import ru.job4j.generic.SimpleArray;

import java.util.Iterator;


public class SimpleSet<T> {
    private SimpleArray<T> simpleArray;

    public SimpleSet(int size) {
        this.simpleArray = new SimpleArray(size);
    }

    public void add(T model) {
        if (!simpleArray.contains(model)) {
            simpleArray.add(model);
        }
    }

    public Iterator<T> iterator() {
        return simpleArray.iterator();
    }
}
