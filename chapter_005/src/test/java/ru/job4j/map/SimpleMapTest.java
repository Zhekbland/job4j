package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class SimpleMapTest is testing SimpleMap.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 3.
 * @since 03.01.2019.
 */
public class SimpleMapTest {
    private SimpleMap<User, String> map;
    User first, second, third, fourth, fith;

    @Before
    public void setUp() {
        this.map = new SimpleMap<>();
        this.first = new User("Zheka", 0,
                new GregorianCalendar(1992, 1, 23));
        this.second = new User("Petr", 2,
                new GregorianCalendar(1988, 5, 15));
        this.third = new User("Zheka", 0,
                new GregorianCalendar(1992, 1, 23));
        this.fourth = new User("Max", 9,
                new GregorianCalendar(1978, 3, 6));
        this.fith = new User("John", 2,
                new GregorianCalendar(1971, 8, 14));
    }

    @Test
    public void whenWeInsertElementsAndGetChangeCapacity() {
        map.insert(first, "1");
        map.insert(second, "2");
        map.insert(fourth, "2");
        map.insert(fith, "2");
    }

    @Test
    public void whenWeInsertDuplicateAndGetFalse() {
        map.insert(first, "1");
        map.insert(second, "2");
        boolean result = map.insert(third, "1");
        assertThat(result, is(false));
    }

    @Test
    public void whenWeGetSecondElement() {
        map.insert(first, "1");
        map.insert(second, "2");
        String result = "2";
        String expected = map.get(second);
        assertThat(result, is(expected));
    }

    @Test
    public void whenWeDeleteSecondElement() {
        map.insert(first, "1");
        map.insert(second, "2");
        boolean result = map.delete(second);
        assertThat(result, is(true));
    }

    @Test
    public void whenWeIterateAndGetTrue() {
        map.insert(first, "1");
        map.insert(second, "2");
        Iterator<SimpleMap.Map> itr = map.iterator();
        itr.next();
        boolean result = itr.hasNext();
        assertThat(result, is(true));
    }

    @Test
    public void whenWeIterateAndGetFalse() {
        map.insert(first, "1");
        map.insert(second, "2");
        Iterator<SimpleMap.Map> itr = map.iterator();
        itr.next();
        itr.next();
        boolean result = itr.hasNext();
        assertThat(result, is(false));
    }
}