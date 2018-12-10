package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class RoleStoreTest is testing class AbstractStore.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 10.12.2018.
 */
public class RoleStoreTest {
    private RoleStore roleStore = new RoleStore(5);

    @Before
    public void setUp() {
        roleStore.add(new Role("1111"));
        roleStore.add(new Role("222"));
        roleStore.add(new Role("3333"));
        roleStore.add(new Role("444"));
        roleStore.add(new Role("5555"));
    }

    @Test
    public void whenWeAddModelOfUser() {
        assertThat(roleStore.findById("222").getId(), is("222"));
    }

    @Test
    public void whenWeReplaceModelOfUser() {
        boolean result = roleStore.replace("222", new Role("999"));
        assertThat(result, is(true));
    }

    @Test
    public void whenWeDeleteModelOfUser() {
        boolean result = roleStore.delete("222");
        assertThat(result, is(true));
    }
}