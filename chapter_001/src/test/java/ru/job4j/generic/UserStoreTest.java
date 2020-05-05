package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void whenAddUserThenStoreHasIt() {
        UserStore userStore = new UserStore();
        User user1 = new User("1111");
        User user2 = new User("2222");
        userStore.add(user1);
        userStore.add(user2);
        User result = userStore.findById(user2.getId());
        assertThat(result.getId(), is(user2.getId()));
    }

    @Test
    public void replace() {
        UserStore userStore = new UserStore();
        User existing = new User("1111");
        User replacing = new User("2222");
        userStore.add(existing);
        userStore.replace(existing.getId(), replacing);
        User result = userStore.findById(replacing.getId());
        assertThat(result.getId(), is(replacing.getId()));
    }

    @Test
    public void delete() {
        UserStore userStore = new UserStore();
        User user1 = new User("1111");
        userStore.add(user1);
        boolean result = userStore.delete(user1.getId());
        assertThat(result, is(true));
    }
}
