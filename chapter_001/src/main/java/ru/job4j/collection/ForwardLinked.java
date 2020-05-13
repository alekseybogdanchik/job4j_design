package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> first;
    private Node<T> last;
    private int size = 0;
    private int modCount = 0;

    private Node<T> indexOf(int index) {
        Node<T> rsl = first;
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl;
    }

    public void add(T model) {
        if (size == 0) {
            first = new Node<>(model, null);
            last = first;
        } else {
            Node<T> node = first;
            while (node.next != null) {
                node = node.next;
            }
            last = new Node<>(model, null);
            node.next = last;
        }
        size++;
        modCount++;
    }

    public void revert() {
        Node<T> node = null;
        Node<T> firstRev = null;
        if (size >= 2) {
            for (int i = size - 1; i > 0; i--) {
                node = indexOf(i);
                node.next = indexOf(i - 1);
                if (i == size - 1) {
                    firstRev = node;
                    firstRev.next = node.next;
                }
            }
            first = firstRev;
            first.next = firstRev.next;
            last = indexOf(size - 1);
            last.next = null;
        }
    }

    public T get(int index) {
        int indexChecked = Objects.checkIndex(index, size);
        return indexOf(index).value;
    }

    public T deleteFirst() {
        T rsl = null;
        if (first == null) {
            throw new NoSuchElementException();
        } else {
            rsl = first.value;
            first = first.next;
        }
        return rsl;
    }

    public T deleteLast() {
        T rsl = null;
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        } else if (size == 1) {
            rsl = last.value;
            first = null;
            last = null;
            size--;
        } else {
            rsl = last.value;
            Node<T> beforeLast = indexOf(size - 2);
            beforeLast.next = null;
            last = beforeLast;
            size--;
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
