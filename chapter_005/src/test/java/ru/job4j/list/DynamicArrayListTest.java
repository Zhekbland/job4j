package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


/**
 * Class DynamicArrayListTest is testing DynamicArrayList.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 14.12.2018.
 */
public class DynamicArrayListTest {
    private DynamicArrayList<Integer> list;

    @Before
    public void init() {
        list = new DynamicArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenGetElementInArrayAndGetExtendArray() {
        list.add(4);
        assertThat(list.get(3), is(4));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddElementForModeCountAndGetException() {
        Iterator<Integer> itr = list.iterator();
        itr.next();
        itr.next();
        list.add(4);
        itr.next();
    }
}