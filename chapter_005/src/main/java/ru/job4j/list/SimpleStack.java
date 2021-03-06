package ru.job4j.list;

import java.util.Iterator;

/**
 * Class SimpleStack realizes Stack.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 24.12.2018.
 */
public class SimpleStack<T> implements Iterable<T> {
    private DynamicLinkedList stack = new DynamicLinkedList();

    public void push(T value) {
        this.stack.add(value);
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    public T poll() {
        return (T) this.stack.poll();
    }

    @Override
    public Iterator<T> iterator() {
        return this.stack.iterator();
    }
}
