package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class SimpleQueueTest is testing SimpleQueue.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 24.12.2018.
 */
public class SimpleQueueTest {
    private SimpleQueue<Integer> queue;

    @Before
    public void setUp() {
        queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
    }

    @Test
    public void whenWePollAndGetFirstElement() {
        assertThat(queue.poll(), is(1));
    }

    @Test
    public void whenWePollPollAndGetFirstElement() {
        queue.poll();
        queue.poll();
        assertThat(queue.poll(), is(3));
    }
}