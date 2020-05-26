package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class SimpleMapTest {
    SimpleMap<String, Integer> map = new SimpleMap<>();

    @Test
    public void whenAddOneThenOne() {
        boolean expected = map.insert("Petrov", 1);
        assertThat(expected, is(true));
        assertThat(map.get("Petrov"), is(1));
    }

    @Test
    public void whenAddThreeThenThree() {
        map.insert("Petrov", 1);
        map.insert("Kolobkov", 2);
        boolean expected = map.insert("Rasputin", 3);
        Iterator<SimpleMap.Node> iterator = map.iterator();
        assertThat(expected, is(true));
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        iterator.next();
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void whenCapacity2Add3ThenResize() {
        SimpleMap<String, Integer> map = new SimpleMap<>(2);
        map.insert("Petrov", 1);
        map.insert("Kolobkov", 2);
        boolean expected = map.insert("Rasputin", 3);
        Iterator<SimpleMap.Node> iterator = map.iterator();
        assertThat(expected, is(true));
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        iterator.next();
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void whenAddSameKeyThenValueReplaced() {
        map.insert("Petrov", 1);
        map.insert("Petrov", 2);
        assertThat(map.get("Petrov"), is(2));
    }

    @Test
    public void whenDeleteOneAndGetItThenE() {
        map.insert("Petrov", 1);
        map.insert("Kolobkov", 2);
        boolean expected = map.delete("Kolobkov");
        assertThat(expected, is(true));
    }
}