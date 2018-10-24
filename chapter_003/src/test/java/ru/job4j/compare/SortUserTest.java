package ru.job4j.compare;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void whenListSortIntoTreeSet() {
        SortUser users = new SortUser();
        Set<User> result = users.sort(Arrays.asList(
                new User("Petr", 32),
                new User("Zheka", 26),
                new User("Inna", 29)
        ));
        Set<User> expect = new TreeSet<>();
        expect.addAll(Arrays.asList(
                new User("Zheka", 26),
                new User("Inna", 29),
                new User("Petr", 32)
        ));
        assertThat(result, is(expect));
    }
}
