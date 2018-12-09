package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class SimpleArrayTest is testing class SimpleArray.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 3.
 * @since 07.12.2018.
 */
public class SimpleArrayTest {
    @Test
    public void whenWeDeleteOneIntegerElement() {
        SimpleArray<Integer> list = new SimpleArray<>(5);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.delete(4);
        Integer result = list.get(3);
        assertThat(result, is(4));
    }

    @Test
    public void whenWeSetOneIntegerElement() {
        SimpleArray<Integer> list = new SimpleArray<>(5);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.set(4, 10);
        Integer result = list.get(4);
        assertThat(result, is(10));
    }

    @Test
    public void whenWeDeleteLastStringElementFromArray() {
        SimpleArray<String> list = new SimpleArray<>(5);
        list.add("Inna");
        list.add("Man");
        list.add("Petr");
        list.add("Mike");
        list.add("Linet");
        list.delete(4);
        String result = list.get(3);
        assertThat(result, is("Mike"));
    }

    @Test
    public void whenWeSetOneStringElementInArray() {
        SimpleArray<String> list = new SimpleArray<>(5);
        list.add("Inna");
        list.add("Man");
        list.add("Petr");
        list.add("Mike");
        list.add("Linet");
        list.set(0, "Dimitry");
        String result = list.get(0);
        assertThat(result, is("Dimitry"));
    }

    @Test
    public void whenWeGetNullElementFromArray() {
        SimpleArray<String> list = new SimpleArray<>(5);
        list.add("123");
        list.add("222");
        list.add("333");
        list.add("444");
        String result = list.get(4);
        String expected = null;
        assertThat(result, is(expected));
    }

    @Test
    public void whenWeCreateArrayWithOneElement() {
        SimpleArray<String> list = new SimpleArray<>(5);
        list.add("123");
        boolean result = list.iterator().hasNext();
        assertThat(result, is(true));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenWeCreateArrayWithOneElementAndGetException() {
        SimpleArray<String> list = new SimpleArray<>(5);
        Iterator<String> iterList = list.iterator();
        list.add("123");
        iterList.next();
        iterList.next();
    }

    @Test
    public void whenWeGetElementOfBound() {
        SimpleArray<String> list = new SimpleArray<>(5);
        Iterator<String> iterList = list.iterator();
        list.add("123");
        list.add("222");
        list.add("333");
        list.add("444");
        list.add("555");
        iterList.next();
        iterList.next();
        iterList.next();
        iterList.next();
        iterList.next();
        boolean result = iterList.hasNext();
        assertThat(result, is(false));
    }

    @Test
    public void whenWeGetNextElementTrue() {
        SimpleArray<String> list = new SimpleArray<>(5);
        Iterator<String> iterList = list.iterator();
        list.add("123");
        list.add("222");
        list.add("333");
        list.add("444");
        list.add("555");
        iterList.next();
        iterList.next();
        iterList.next();
        iterList.next();
        boolean result = iterList.hasNext();
        assertThat(result, is(true));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenWeGetExceptionOutOfBound() {
        SimpleArray<String> list = new SimpleArray<>(5);
        list.add("123");
        list.add("222");
        list.add("333");
        list.add("444");
        list.add("555");
        list.get(5);
    }

    @Test(expected = NullPointerException.class)
    public void whenWeSetElementWhichWasNotCreate() {
        SimpleArray<String> list = new SimpleArray<>(5);
        list.add("123");
        list.add("222");
        list.add("333");
        list.add("444");
        list.set(4, "555");
    }
}