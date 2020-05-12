package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class ForwardLinkedTest {
    private Iterator<Integer> iter;

    @Test
    public void add() {
        ForwardLinked<Integer> lf = new ForwardLinked<>();
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
        ForwardLinked<Integer> lf = new ForwardLinked<>();
        lf.add(1);
        iter = lf.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(1));
        lf.add(2);
        iter.hasNext();
    }

    @Test
    public void get() {
        ForwardLinked<Integer> lf = new ForwardLinked<>();
        lf.add(1);
        lf.add(2);
        lf.add(3);
        assertThat(lf.get(0), is(1));
        assertThat(lf.get(1), is(2));
        assertThat(lf.get(2), is(3));
    }

    @Test
    public void getLast() {
        ForwardLinked<Integer> lf = new ForwardLinked<>();
        lf.add(1);
        assertThat(lf.deleteLast(), is(1));
        lf.add(2);
        assertThat(lf.deleteLast(), is(2));
        lf.add(3);
        assertThat(lf.deleteLast(), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void ifGetIndexOutOfBoundsThanE() {
        ForwardLinked<String> lf = new ForwardLinked<>();
        lf.add("test");
        lf.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.deleteFirst();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }

    @Test
    public void deleteLast() {
        ForwardLinked<Integer> lf = new ForwardLinked<>();
        lf.add(1);
        lf.add(2);
        lf.add(3);
        assertThat(lf.deleteLast(), is(3));
        lf.deleteLast();
        iter = lf.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(1));
        assertThat(iter.hasNext(), is(false));
    }
}