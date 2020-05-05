package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean rsl = false;
        for (int i = 0; i < mem.size(); i++) {
            if (id.equals(mem.get(i).getId())) {
                mem.set(i, model);
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        for (int i = 0; i < mem.size(); i++) {
            if (id.equals(mem.get(i).getId())) {
                mem.remove(i);
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public T findById(String id) {
        T rsl = null;
        for (T t : mem) {
            if (id.equals(t.getId())) {
                rsl = t;
                break;
            }
        }
        return rsl;
    }
}
