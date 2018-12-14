package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class DynamicArrayList realizes ArrayList (dynamic).
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 14.12.2018.
 */
public class DynamicArrayList<E> implements Iterable<E> {
    private Object[] container;
    private int capacity = 0;
    private int modeCount = 0;
    private static final int DEFAULT_CAPACITY = 3;

    public void add(E value) {
        if (container == null) {
            container = new Object[DEFAULT_CAPACITY];
        }
        if (container.length == capacity) {
            capacityExtend();
        }
        container[capacity++] = value;
        modeCount++;
    }

    public void capacityExtend() {
        container = Arrays.copyOf(container, capacity * 2);
    }

    public E get(int index) {
        if (index >= container.length) {
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        return (E) container[index];

    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;
            private int expectedModeCount = modeCount;

            @Override
            public boolean hasNext() {
                return capacity > index;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No element");
                }
                if (modeCount > expectedModeCount) {
                    throw new ConcurrentModificationException();
                }
                return (E) container[index++];
            }
        };
    }
}
