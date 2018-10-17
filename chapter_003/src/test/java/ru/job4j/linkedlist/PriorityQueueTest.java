package ru.job4j.linkedlist;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class PriorityQueueTest testing PriorityQueue.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 17.10.2018.
 */
public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("middle", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("low", 9));
        queue.put(new Task("middle", 3));
        queue.put(new Task("low", 8));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }
}
