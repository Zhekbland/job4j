package ru.job4j.pool;

import ru.job4j.wait.SimpleBlockingQueue;

/**
 * Class ThreadPool.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 08.02.2019.
 */
public class ThreadPool {
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
    private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors();

    public ThreadPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            new MyThread().start();
        }
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        Runnable stop = new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().interrupt();
            }
        };
        for (int i = 0; i < POOL_SIZE; i++) {
            this.tasks.offer(stop);
        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            Runnable runnable = null;
            while (!this.isInterrupted()) {
                try {
                    runnable = tasks.poll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                runnable.run();
            }
        }
    }
}