package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Class MatrixIterator iterates matrix arrays.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 01.12.2018.
 */
public class MatrixIterator implements Iterator {
    private final int[][] values;
    private int indexI;
    private int indexJ;

    public MatrixIterator(final int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return values.length > indexI && values[indexI].length > indexJ;
    }

    @Override
    public Object next() {
        int result = values[indexI][indexJ];
        if (values[indexI].length - 1 > indexJ) {
            indexJ++;
        } else {
            indexI++;
            indexJ = 0;
        }
        return result;
    }
}
