package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class LinkedForwardTest {
    private Iterator<Integer> iter;

    @Test
    public void add() {
        LinkedForward<Integer> lf = new LinkedForward<>();
        lf.add(1);
        lf.add(2);
        lf.add(3);
        iter = lf.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(1));
        assertThat(iter.next(), is(2));
        assertThat(iter.next(), is(3));
        assertThat(iter.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIterAfterAddThenE() {
        LinkedForward<Integer> lf = new LinkedForward<>();
        lf.add(1);
        iter = lf.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(1));
        lf.add(2);
        iter.hasNext();
    }

        @Test
    public void get() {
        LinkedForward<Integer> lf = new LinkedForward<>();
        lf.add(1);
        lf.add(2);
        lf.add(3);
        assertThat(lf.get(0), is(1));
        assertThat(lf.get(1), is(2));
        assertThat(lf.get(2), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void ifGetIndexOutOfBoundsThanE() {
        LinkedForward<String> lf = new LinkedForward<>();
        lf.add("test");
        lf.get(1);
    }
}