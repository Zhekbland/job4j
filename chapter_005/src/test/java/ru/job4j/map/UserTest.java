package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Class UserTest is testing class User.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 26.12.2018.
 */
public class UserTest {
    @Test
    public void whenWePrintMap() {
        User first = new User("Zheka", 0,
                new GregorianCalendar(1992, 1, 23));
        User second = new User("Zheka", 0,
                new GregorianCalendar(1992, 1, 23));
        Map<User, Object> map = new HashMap<>();
        map.put(first, "first");
        map.put(second, "second");
        System.out.println(map);
    }

}