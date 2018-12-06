package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class Converter converts iterator of iterator into iterator.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 03.12.2018.
 */
public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Iterator<Integer>> generalIterator = it;
            Iterator<Integer> currentIterator = generalIterator.next();

            @Override
            public boolean hasNext() {
                boolean result = true;
                if (!currentIterator.hasNext()) {
                    result = false;
                    while (generalIterator.hasNext()) {
                        currentIterator = generalIterator.next();
                        if (currentIterator.hasNext()) {
                            result = true;
                            break;
                        }
                    }
                }
                return result;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return currentIterator.next();
            }
        };
    }
}
