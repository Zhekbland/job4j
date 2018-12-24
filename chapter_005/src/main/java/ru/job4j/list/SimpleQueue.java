package ru.job4j.list;

/**
 * Class SimpleQueue realizes Queue from two stack.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 24.12.2018.
 */
public class SimpleQueue<T> {
    private SimpleStack<T> first = new SimpleStack<>();
    private SimpleStack<T> second = new SimpleStack<>();

    public void push(T value) {
        this.first.push(value);
    }

    public void transfer() {
        if (second.isEmpty()) {
            while (!first.isEmpty()) {
                this.second.push(this.first.poll());
            }
        }
    }

    public T poll() {
        transfer();
        return this.second.poll();
    }
}