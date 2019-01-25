package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class SimpleBlockingQueue.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 25.01.2019.
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public void offer(T value) {
        synchronized (this.queue) {
            while (this.queue.size() == 10) {
                try {
                    this.queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.offer(value);
            this.queue.notify();
        }
    }

    public T poll() throws InterruptedException {
        T result;
        synchronized (this.queue) {
            while (this.queue.peek() == null) {
                this.queue.wait();
            }
            result = this.queue.poll();
            this.queue.notify();
        }
        return result;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
