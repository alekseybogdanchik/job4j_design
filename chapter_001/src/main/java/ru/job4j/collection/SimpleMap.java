package ru.job4j.collection;

import java.util.*;


public class SimpleMap<K, V> implements Iterable<SimpleMap.Node> {
    private Node[] container;
    private int size = 0;
    private int modCount = 0;
    static final float LOAD_FACTOR = 0.75f;

    public SimpleMap(int capacity) {
        this.container = new Node[capacity];
    }

    public SimpleMap() {
        this.container = new Node[16];
    }

    private int hash(Object key) {
        int h = 0;
        if (key != null) {
            h = key.hashCode() ^ (h >>> 16);
        }
        return h;
    }

    private Node[] resize(Node[] oldTab) {
        Node[] bigTab = new Node[oldTab.length * 2];
        for (int i = 0; i < oldTab.length; i++) {
            if (oldTab[i] != null) {
                Node element = oldTab[i];
                int index = (bigTab.length - 1) & element.getHash();
                bigTab[index] = element;
            }
        }
        return bigTab;
    }

    public boolean insert(K key, V value) {
        boolean rsl = false;
        int hash = hash(key);
        if (size >= container.length * LOAD_FACTOR) {
            container = resize(container);
        }
        int index = (container.length - 1) & hash;
        if (container[index] == null) {
            container[index] = new Node(key, value, hash);
            rsl = true;
            size++;
            modCount++;
        } else {
            Node element = container[index];
            if (element.getHash() == hash && element.getKey().equals(key)) {
                element.setValue(value);
                rsl = true;
            }
        }
        return rsl;
    }

    public V get(K key) {
        V rsl = null;
        int hash = hash(key);
        int index = (container.length - 1) & hash;
        if (container[index] != null) {
            Node element = container[index];
            if (element.getHash() == hash && element.getKey().equals(key)) {
                rsl = (V) container[index].getValue();
            }
        }
        return rsl;
    }

    public boolean delete(K key) {
        boolean rsl = false;
        int hash = hash(key);
        int index = (container.length - 1) & hash;
        if (container[index] != null) {
            Node element = container[index];
            if (element.getHash() == hash && element.getKey().equals(key)) {
                container[index] = null;
                size--;
                modCount++;
                rsl = true;
            }
        }
        return rsl;
    }

    protected static class Node<K, V> {
        private K key;
        private V value;
        private int hash;

        public Node(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public int getHash() {
            return hash;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public String toString() {
            return key + "=" + value;
        }
    }

    @Override
    public Iterator<Node> iterator() {
        return new Iterator<Node>() {
            int index = 0;
            int count = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public Node next() {
                Node rsl = null;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = index; i < container.length; i++) {
                    if (container[i] != null) {
                        rsl = container[i];
                        index = i + 1;
                        count++;
                        break;
                    }
                }
                return rsl;
            }
        };
    }
}
