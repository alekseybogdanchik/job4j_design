package ru.job4j.iterator;

import java.util.Iterator;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator inner = it.next();

            @Override
            public boolean hasNext() {
                while (it.hasNext() && !inner.hasNext()) {
                    inner = it.next();
                }
                return inner.hasNext();
            }

            @Override
            public Integer next() {
                if (!inner.hasNext()) {
                    inner = it.next();
                }
                return (Integer) inner.next();
            }
        };
    }
}
