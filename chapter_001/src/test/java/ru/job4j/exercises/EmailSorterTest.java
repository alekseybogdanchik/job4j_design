package ru.job4j.exercises;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class EmailSorterTest {
    EmailSorter emailSorter = new EmailSorter();

    @Test
    public void emailSorter() {
        List<User> users = List.of(
                new User("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")),
                new User("user2", Set.of("foo@gmail.com", "ups@pisem.net")),
                new User("user3", Set.of("xyz@pisem.net", "vasya@pupkin.com")),
                new User("user4", Set.of("ups@pisem.net", "aaa@bbb.ru")),
                new User("user5", Set.of("xyz@pisem.net"))
        );

        List<User> expected = List.of(
                new User("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru",
                                                "ups@pisem.net", "aaa@bbb.ru")),
                new User("user3", Set.of("xyz@pisem.net", "vasya@pupkin.com"))
        );

        Comparator<User> comparator = (o1, o2) -> o1.getName().compareTo(o2.getName());

        List<User> rsl = emailSorter.sorter(users);
        rsl.sort(comparator);

        assertThat(rsl, is(expected));
    }
}