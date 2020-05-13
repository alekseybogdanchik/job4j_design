package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class SimpleSetTest {
    @Test
    public void add() {
        SimpleSet<Integer> simpleSet = new SimpleSet<>(3);
        simpleSet.add(1);
        Iterator<Integer> iterator = simpleSet.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void whenAddSameElements() {
        SimpleSet<Integer> simpleSet = new SimpleSet<>(4);
        simpleSet.add(1);
        simpleSet.add(1);
        simpleSet.add(2);
        simpleSet.add(3);
        simpleSet.add(3);
        Iterator<Integer> iterator = simpleSet.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
    }
}
