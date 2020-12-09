package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;


public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return search(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return search(value, comparator.reversed());
    }

    public <T> T search(List<T> value, Comparator<T> comparator) {
        T rsl = null;
        if (value.size() != 0) {
            rsl = value.get(0);
        }
        for (T t : value) {
            int c = comparator.compare(rsl, t);
            if (c < 0) {
                rsl = t;
            }
        }
        return rsl;
    }
}
