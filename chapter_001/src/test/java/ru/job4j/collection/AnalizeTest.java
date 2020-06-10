package ru.job4j.collection;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizeTest {
    Analize analize = new Analize();

    @Test
    public void when1add1Change1Delete() {
        List<Analize.User> previous = List.of(
                new Analize.User(111, "aaaa"),
                new Analize.User(222, "bbbb"),
                new Analize.User(333, "cccc")
        );
        List<Analize.User> current = List.of(
                new Analize.User(111, "zzzz"),
                new Analize.User(333, "cccc"),
                new Analize.User(555, "eeee")
        );
        Analize.Info expected = analize.diff(previous, current);
        assertThat(expected.added, is(1));
        assertThat(expected.changed, is(1));
        assertThat(expected.deleted, is(1));
    }

    @Test
    public void when2add1Change() {
        List<Analize.User> previous = List.of(
                new Analize.User(111, "aaaa")
        );
        List<Analize.User> current = List.of(
                new Analize.User(111, "zzzz"),
                new Analize.User(333, "cccc"),
                new Analize.User(555, "eeee")
        );
        Analize.Info expected = analize.diff(previous, current);
        assertThat(expected.added, is(2));
        assertThat(expected.changed, is(1));
        assertThat(expected.deleted, is(0));
    }

    @Test
    public void whenAllDeleted() {
        List<Analize.User> previous = List.of(
                new Analize.User(111, "aaaa"),
                new Analize.User(222, "zzzz")
        );
        List<Analize.User> current = List.of();
        Analize.Info expected = analize.diff(previous, current);
        assertThat(expected.added, is(0));
        assertThat(expected.changed, is(0));
        assertThat(expected.deleted, is(2));
    }
    @Test
    public void whenAllAdd() {
        List<Analize.User> previous = List.of();
        List<Analize.User> current = List.of(
                new Analize.User(111, "aaaa"),
                new Analize.User(222, "zzzz")
        );
        Analize.Info expected = analize.diff(previous, current);
        assertThat(expected.added, is(2));
        assertThat(expected.changed, is(0));
        assertThat(expected.deleted, is(0));
    }
}
