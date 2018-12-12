package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class SimpleArrayListTest is testing SimpleArrayList.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 12.12.2018.
 */
public class SimpleArrayListTest {
    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));

    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));

    }

    @Test
    public void whenDeleteFirstElementThenUseGetResultOne() {
        list.delete();
        list.delete();
        assertThat(list.get(0), is(1));
    }

    @Test
    public void whenDeleteAllElementThenUseGetSizeResultNull() {
        list.delete();
        list.delete();
        list.delete();
        assertThat(list.getSize(), is(0));
    }

    @Test(expected = NullPointerException.class)
    public void whenDeleteEmptyListThenGetException() {
        list.delete();
        list.delete();
        list.delete();
        list.delete();
    }
}