package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class UserStoreTest is testing AbstractStore.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 10.12.2018.
 */
public class UserStoreTest {
    private UserStore userStore = new UserStore(5);

    @Before
    public void setUp() {
        userStore.add(new User("1111"));
        userStore.add(new User("222"));
        userStore.add(new User("3333"));
        userStore.add(new User("444"));
        userStore.add(new User("5555"));
    }

    @Test
    public void whenWeAddModelOfUser() {
        assertThat(userStore.findById("222").getId(), is("222"));
    }

    @Test
    public void whenWeReplaceModelOfUser() {
        boolean result = userStore.replace("222", new User("999"));
        assertThat(result, is(true));
    }

    @Test
    public void whenWeDeleteModelOfUser() {
        boolean result = userStore.delete("222");
        assertThat(result, is(true));
    }
}