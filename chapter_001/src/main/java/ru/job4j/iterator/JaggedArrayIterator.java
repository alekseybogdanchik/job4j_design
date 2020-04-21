package ru.job4j.iterator;

import java.util.Iterator;

public class JaggedArrayIterator<T> implements Iterator<T> {
    private int row = 0;
    private int cell = 0;
    private T[][] array;

    public JaggedArrayIterator (T[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = true;
        if (row == array.length - 1 && cell == array[row].length) {
            rsl = false;
        }
        return rsl;
    }

    @Override
    public T next() {
        T rsl = array[row][cell];
        if(cell < array[row].length - 1){
            cell++;
        } else if (row != array.length - 1){
            row++;
            cell = 0;
        } else {
            cell++;
        }
        return rsl;
    }
}
