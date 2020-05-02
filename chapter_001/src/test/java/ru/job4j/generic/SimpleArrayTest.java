package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class SimpleArrayTest {
    private Iterator<Integer> iter;

    @Test
    public void addOneThenOne() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        iter = simpleArray.iterator();
        simpleArray.add(1);
        assertThat(iter.next(), is(1));
        assertThat(iter.hasNext(), is(false));
    }

    @Test
    public void whenAddStringReturnString() {
        Iterator<String> iter;
        SimpleArray<String> simpleArray = new SimpleArray<>(3);
        iter = simpleArray.iterator();
        simpleArray.add("test");
        assertThat(iter.next(), is("test"));
        assertThat(iter.hasNext(), is(false));
    }

    @Test
    public void addOneThenAddTwo() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        iter = simpleArray.iterator();
        simpleArray.add(1);
        assertThat(iter.next(), is(1));
        assertThat(iter.hasNext(), is(false));
        simpleArray.add(2);
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addTwoInSizeOneThenE() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(1);
        simpleArray.add(1);
        simpleArray.add(2);
    }

    @Test
    public void set() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(1);
        iter = simpleArray.iterator();
        simpleArray.add(1);
        simpleArray.set(0, 2);
        assertThat(iter.next(), is(2));
    }

    @Test
    public void remove() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(5);
        iter = simpleArray.iterator();
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.remove(3);
        assertThat(iter.next(), is(1));
        assertThat(iter.next(), is(2));
        assertThat(iter.next(), is(3));
        assertThat(iter.hasNext(), is(false));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveOutOfBounds() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(2);
        iter = simpleArray.iterator();
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.remove(2);
    }

    @Test
    public void get() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(1);
        simpleArray.add(1);
        assertThat(simpleArray.get(0), is(1));
    }
}
