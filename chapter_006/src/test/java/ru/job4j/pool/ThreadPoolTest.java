package ru.job4j.pool;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class ThreadPoolTest is testing ThreadPool.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 08.02.2019.
 */
public class ThreadPoolTest {
    private int result = 0;

    private void increment() {
        result++;
    }

    @Test
    public void whenAdd() {
        ThreadPool pool = new ThreadPool();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                increment();
            }
        };
        for (int i = 0; i < 10; i++) {
            pool.work(runnable);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        pool.shutdown();
        assertThat(result, is(10));
    }
}