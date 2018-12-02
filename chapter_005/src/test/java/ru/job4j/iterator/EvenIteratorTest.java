package ru.job4j.iterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class EvenIteratorTest is testing EvenIterator.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 02.12.2018.
 */
public class EvenIteratorTest {
    @Test
    public void whenWeGetEvenElement() throws NoSuchElementException {
        EvenIterator it = new EvenIterator(new int[]{1, 3, 5, 4});
        int result = (Integer) it.next();
        assertThat(result, is(4));
    }

    @Test
    public void whenWeGetFalseInformationAboutNextElement() throws NoSuchElementException {
        EvenIterator it = new EvenIterator(new int[]{4, 2, 1, 1});
        it.next();
        it.next();
        boolean result = it.hasNext();
        assertThat(result, is(false));
    }

    @Test
    public void whenWeGetTrueInformationAboutNextElement() throws NoSuchElementException {
        EvenIterator it = new EvenIterator(new int[]{4, 2, 1, 1});
        it.next();
        boolean result = it.hasNext();
        assertThat(result, is(true));
    }
}