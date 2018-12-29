package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class SimpleStackTest is testing SimpleStack.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 24.12.2018.
 */
public class SimpleStackTest {
    private SimpleStack<Integer> stack;

    @Before
    public void setUp() {
        this.stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    @Test
    public void whenWePollAndGetFirstElementWithIterator() {
        stack.poll();
        Iterator<Integer> itr = stack.iterator();
        assertThat(itr.next(), is(2));
    }
}