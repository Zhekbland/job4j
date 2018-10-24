package ru.job4j.compare;

import org.junit.Test;

import java.util.*;

/**
 * Class SortUserTest to do test.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 24.10.2018.
 */
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

    @Test
    public void whenListSortNameLength() {
        SortUser users = new SortUser();
        List<User> result = users.sortNameLength(Arrays.asList(
                new User("Peter", 32),
                new User("Evgenii", 26),
                new User("Inna", 29),
                new User("Vladislav", 28)
        ));
        List<User> expect = Arrays.asList(
                new User("Inna", 29),
                new User("Peter", 32),
                new User("Evgenii", 26),
                new User("Vladislav", 28)
        );
        assertThat(result, is(expect));
    }

    @Test
    public void whenListSortByAllFields() {
        SortUser users = new SortUser();
        List<User> result = users.sortByAllFields(Arrays.asList(
                new User("Petr", 32),
                new User("Petr", 23),
                new User("Vladislav", 26),
                new User("Inna", 50),
                new User("Inna", 29),
                new User("Vladislav", 28),
                new User("Evgenii", 26),
                new User("Evgenii", 23)
        ));
        List<User> expect = Arrays.asList(
                new User("Evgenii", 23),
                new User("Evgenii", 26),
                new User("Inna", 29),
                new User("Inna", 50),
                new User("Petr", 23),
                new User("Petr", 32),
                new User("Vladislav", 26),
                new User("Vladislav", 28)
        );
        assertThat(result, is(expect));
    }
}
