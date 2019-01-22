package ru.job4j.synchronize;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class DynamicListTest is testing DynamicList.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 22.01.2019.
 */
public class DynamicListTest {
    private DynamicList<Integer> list = new DynamicList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
    }

    @Test
    public void when() throws InterruptedException {
        Iterator<Integer> itr = list.iterator();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 300; i++) {
                    itr.next();
                    list.add(i); // add new elements and don't have ConcurrentModificationException()
                }
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    itr.next();
                }
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        Integer result = itr.next();
        assertThat(result, is(800));
    }
}