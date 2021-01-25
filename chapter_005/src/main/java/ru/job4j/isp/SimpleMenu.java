package ru.job4j.isp;

import java.util.*;

public class SimpleMenu<K extends Comparable<K>> implements Menu<K> {

    private Node<K> root;

    public SimpleMenu(K key, Action action) {
        this.root = new Node<>(key, action);
    }

    @Override
    public boolean add(K parent, K node, Action action) {
        Optional<Node<K>> parentNode = find(parent);
        Optional<Node<K>> childrenNode = find(node);
        if (parentNode.isEmpty() || childrenNode.isPresent()) {
            return false;
        }
        parentNode.get().children.add(new Node<K>(parentNode.get(), node, action));
        return true;
    }

    @Override
    public boolean remove(K key) {
        Optional<Node<K>> node = find(key);
        if (node.isEmpty()) {
            return false;
        }
        if (node.get() == root) {
            root = null;
            return true;
        }
        Node<K> parent = node.get().parent;
        parent.children.addAll(node.get().children);
        parent.children.remove(node.get());
        return true;
    }

    @Override
    public boolean update(K key, Action action) {
        Optional<Node<K>> node = find(key);
        if (node.isEmpty()) {
            return false;
        }
        node.get().action = action;
        return true;
    }

    @Override
    public Action get(K key) {
        Optional<Node<K>> node = find(key);
        return node.map(kNode -> kNode.action).orElse(null);
    }

    private Optional<Node<K>> find(K key) {
        Optional<Node<K>> found = Optional.empty();
        Queue<Node<K>> queue = new LinkedList<>(List.of(root));
        while (!queue.isEmpty()) {
            Node<K> el = queue.poll();
            if (Objects.equals(el.key, key)) {
                found = Optional.of(el);
                break;
            }
            queue.addAll(el.children);
        }
        return found;
    }

    @Override
    public String unOrdered() {
        return unOrdered(root, 0, new StringBuilder()).toString();
    }

    private StringBuilder unOrdered(Node<K> node, int level, StringBuilder out) {
        String prefix = "-".repeat(level);
        out.append(String.format("%s%s%s%n", (prefix + " ").stripLeading(), node.key + " ", node.action.name()));
        node.children.forEach(c -> unOrdered(c, level + 1, out));
        return out;
    }

    @Override
    public String ordered() {
        return ordered(root, 0, "", new StringBuilder()).toString();
    }

    private StringBuilder ordered(Node<K> node, int number, String prefix, StringBuilder out) {
        String prx = String.format("%s%s", prefix, number == 0 ? "" : number + ".");
        out.append(String.format("%s%s%n", (prx + " ").stripLeading(), node.action.name()));
        int subNumber = 1;
        for (Node<K> c : node.children) {
            ordered(c, subNumber, prx, out);
            subNumber++;
        }
        return out;
    }

    private static class Node<K> {
        K key;
        Action action;
        Node<K> parent;
        List<Node<K>> children = new LinkedList<>();

        private Node() {

        }

        private Node(K key, Action action) {
            this.key = key;
            this.action = action;
        }

        public Node(Node<K> parent, K key, Action action) {
            this(key, action);
            this.parent = parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?> node = (Node<?>) o;
            return Objects.equals(key, node.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

}
