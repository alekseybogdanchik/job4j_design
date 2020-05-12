package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class LinkedForward<T> implements Iterable<T> {
    private Node<T> first;
    private int size = 0;
    private int modCount = 0;

    public void add(T model) {
        if (size == 0) {
            first = new Node<>(model, null);
        } else {
            Node<T> last = first;
            while (last.next != null) {
                last = last.next;
            }
            Node<T> node = new Node<>(model, null);
            last.next = node;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        int indexChecked = Objects.checkIndex(index, size);
        T rsl = null;
        int rslIndex = 1;
        if (index == 0) {
            rsl = first.value;
        } else {
            Node<T> node = first.next;
            while (rslIndex != index) {
                node = node.next;
                rslIndex++;
            }
            rsl = node.value;
        }
        return rsl;
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = first;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }
}

