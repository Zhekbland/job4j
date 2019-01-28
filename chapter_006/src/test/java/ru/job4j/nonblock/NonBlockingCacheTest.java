package ru.job4j.nonblock;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class NonBlockingCacheTest is testing NonBlockingCache.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 28.01.2019.
 */
public class NonBlockingCacheTest {
    private NonBlockingCache cache = new NonBlockingCache();
    Base first;
    Base second;
    Base third;

    @Before
    public void setUp() {
        this.first = new Base(1, 0);
        this.second = new Base(2, 0);
        this.third = new Base(3, 0);
        cache.add(first);
        cache.add(second);
        cache.add(third);
    }

    @Test
    public void when() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Random random = new Random(1000);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < random.nextInt(); i++) {
                        cache.update(first);
                    }
                } catch (Exception e) {
                    ex.set(e);
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < random.nextInt(); i++) {
                        cache.update(first);
                    }
                } catch (Exception e) {
                    ex.set(e);
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    @Test
    public void whenWeDeleteAndGetTrue() {
        assertThat(cache.delete(first), is(true));
    }

    @Test
    public void whenWeAddSameBaseAndGetFalse() {
        assertThat(cache.add(new Base(1, 2)), is(false));
    }
}