package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleArray does simple actions in array.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 3.
 * @since 07.12.2018.
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] objects;
    private int index = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public void add(T model) {
        if (index + 1 > this.objects.length) {
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        this.objects[index++] = model;
    }

    public void set(int index, T model) {
        if (index >= this.objects.length) {
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        if (index >= this.index) {
            throw new NullPointerException("Null!");
        }
        this.objects[index] = model;
    }

    public void delete(int index) {
        if (index >= this.objects.length) {
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        if (this.index > index) {
            System.arraycopy(this.objects, index + 1, this.objects, index, (this.objects.length - 1) - index);
            this.objects[this.index - 1] = null;
            this.index--;
        }
    }

    public T get(int index) {
        if (index >= this.objects.length) {
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        return (T) this.objects[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int itrIndex = 0;

            @Override
            public boolean hasNext() {
                return index > itrIndex;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No elements next!");
                }
                return (T) objects[itrIndex++];
            }
        };
    }
}
