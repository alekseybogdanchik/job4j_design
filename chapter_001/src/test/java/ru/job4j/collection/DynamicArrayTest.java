package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class DynamicArrayTest {
    private Iterator<Integer> iter;

    @Test
    public void whenAddOneThenOne() {
        DynamicArray<Integer> dynArray = new DynamicArray<>(3);
        dynArray.add(1);
        iter = dynArray.iterator();
        assertThat(iter.next(), is(1));
        assertThat(iter.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIterateAfterAddingThenE() {
        DynamicArray<Integer> dynArray = new DynamicArray<>(3);
        dynArray.add(1);
        iter = dynArray.iterator();
        assertThat(iter.hasNext(), is(true));
        dynArray.add(2);
        iter.next();
    }

    @Test
    public void whenAddMoreThenSizeThenSizeIncr() {
        DynamicArray<Integer> dynArray = new DynamicArray<>(2);
        dynArray.add(1);
        dynArray.add(2);
        dynArray.add(3);
        iter = dynArray.iterator();
        assertThat(iter.next(), is(1));
        assertThat(iter.next(), is(2));
        assertThat(iter.next(), is(3));
        assertThat(iter.hasNext(), is(false));
    }

    @Test
    public void set() {
        DynamicArray<Integer> dynArray = new DynamicArray<>(2);
        dynArray.add(1);
        dynArray.set(0, 2);
        iter = dynArray.iterator();
        assertThat(iter.next(), is(2));

    }

    @Test
    public void remove() {
        DynamicArray<Integer> dynArray = new DynamicArray<>(2);
        dynArray.add(1);
        dynArray.add(2);
        dynArray.add(3);
        dynArray.add(4);
        dynArray.remove(3);
        iter = dynArray.iterator();
        assertThat(iter.next(), is(1));
        assertThat(iter.next(), is(2));
        assertThat(iter.next(), is(3));
        assertThat(iter.hasNext(), is(false));
    }

    @Test
    public void get() {
        DynamicArray<Integer> dynArray = new DynamicArray<>(2);
        dynArray.add(1);
        assertThat(dynArray.get(0), is(1));
    }
}
