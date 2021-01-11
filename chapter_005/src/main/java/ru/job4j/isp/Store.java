package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;


public class Store<T> {
    private final List<T> list = new ArrayList<>();

    public T add(T t) {
        this.list.add(t);
        return t;
    }

    public boolean delete(T t) {
        boolean rsl = false;
        for (int i = 0; i < list.size(); i++) {
            if (t.equals(t)) {
                list.remove(i);
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    public List<T> showAll() {
        return this.list;
    }
}
