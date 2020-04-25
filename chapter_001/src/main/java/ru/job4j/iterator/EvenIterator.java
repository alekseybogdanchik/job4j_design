package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator {
    private final int[] numbers;
    int index = 0;

    public EvenIterator(final int[] numbers) {
        this.numbers = numbers;
    }

    private static boolean isEven(int n) {
        return n % 2 == 0;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        for (int i = index; i < numbers.length; i++) {
            if (EvenIterator.isEven(numbers[i])) {
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Object next() {
        int rsl = -1;
        for (int i = index; i < numbers.length; i++) {
            if (EvenIterator.isEven(numbers[i])) {
                rsl = numbers[i];
                index = i + 1;
                break;
            }
        }
        if (rsl == -1) {
            throw new NoSuchElementException("No even elements");
        }
        return rsl;
    }
}
