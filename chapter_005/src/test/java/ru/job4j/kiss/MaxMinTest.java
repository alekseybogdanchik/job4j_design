package ru.job4j.kiss;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class MaxMinTest {

    @Test
    public void whenMax77andMin1() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = Arrays.asList(1, 20, 55, 77);
        Integer rslMax = maxMin.max(list, (Integer::compareTo));
        Integer rslMin = maxMin.min(list, (Integer::compareTo));
        int expectedMax = 77;
        int expectedMin = 1;
        assertThat(rslMax, is(expectedMax));
        assertThat(rslMin, is(expectedMin));
    }

    @Test
    public void whenMaxZebraMinCat() {
        MaxMin maxMin = new MaxMin();
        List<String> list = Arrays.asList("dog", "cat", "fish", "zebra");
        String rslMax = maxMin.max(list, (String::compareTo));
        String rslMin = maxMin.min(list, (String::compareTo));
        String expectedMax = "zebra";
        String expectedMin = "cat";
        assertThat(rslMax, is(expectedMax));
        assertThat(rslMin, is(expectedMin));
    }
}
