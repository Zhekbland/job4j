package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class DynamicLinkedListTest is testing DynamicLinkedList.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 14.12.2018.
 */
public class DynamicLinkedListTest {

    private DynamicLinkedList<Integer> list;

    @Before
    public void setUp() {
        list = new DynamicLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenWeGetElementIndexTwo() {
        assertThat(list.get(0), is(3));
    }

    @Test
    public void whenGetThirdElementIterator() {
        Iterator<Integer> itr = list.iterator();
        itr.next();
        itr.next();
        assertThat(itr.next(), is(1));
    }

    @Test
    public void whenWeGetHasNextFalse() {
        Iterator<Integer> itr = list.iterator();
        itr.next();
        itr.next();
        itr.next();
        assertThat(itr.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenWeGetModCountException() {
        Iterator<Integer> itr = list.iterator();
        itr.next();
        list.add(4);
        itr.next();
    }
}