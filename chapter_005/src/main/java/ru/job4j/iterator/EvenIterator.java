package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class EvenIterator iterates even elements.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 02.12.2018.
 */
public class EvenIterator implements Iterator {
    private final int[] values;
    private int index;

    public EvenIterator(final int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return values.length > index && (values[index] % 2 == 0);
    }

    @Override
    public Object next() throws NoSuchElementException {
        while (values.length > index && (values[index] % 2 != 0)) {
            index++;
        }
        if (values.length <= index) {
            throw new NoSuchElementException("No elements next.");
        }
        return values[index++];
    }
}
