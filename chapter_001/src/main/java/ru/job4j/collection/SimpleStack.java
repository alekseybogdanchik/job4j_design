package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        T rsl = linked.deleteLast();
        return rsl;
    }

    public void push(T value) {
        linked.add(value);
    }
}
