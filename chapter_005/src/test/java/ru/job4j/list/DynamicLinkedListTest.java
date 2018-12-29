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
 * @version 2.
 * @since 25.12.2018.
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

    @Test
    public void whenWeHaveCycleAndGetTrue() {
        DynamicLinkedList.Node first = new DynamicLinkedList.Node(1);
        DynamicLinkedList.Node second = new DynamicLinkedList.Node(2);
        DynamicLinkedList.Node third = new DynamicLinkedList.Node(3);
        DynamicLinkedList.Node fourth = new DynamicLinkedList.Node(4);
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = first;
        boolean result = list.hasCycle(first);
        assertThat(result, is(true));
    }

    @Test
    public void whenWeDoNotHaveAndGetFalse() {
        DynamicLinkedList.Node first = new DynamicLinkedList.Node(1);
        DynamicLinkedList.Node second = new DynamicLinkedList.Node(2);
        DynamicLinkedList.Node third = new DynamicLinkedList.Node(3);
        DynamicLinkedList.Node fourth = new DynamicLinkedList.Node(4);
        first.next = second;
        second.next = third;
        third.next = fourth;
        boolean result = list.hasCycle(third);
        assertThat(result, is(false));
    }

    @Test
    public void whenWeHaveCycleInMiddleAndGetTrue() {
        DynamicLinkedList.Node first = new DynamicLinkedList.Node(1);
        DynamicLinkedList.Node second = new DynamicLinkedList.Node(2);
        DynamicLinkedList.Node third = new DynamicLinkedList.Node(3);
        DynamicLinkedList.Node fourth = new DynamicLinkedList.Node(4);
        first.next = second;
        second.next = second;
        third.next = fourth;
        boolean result = list.hasCycle(first);
        assertThat(result, is(true));
    }
}