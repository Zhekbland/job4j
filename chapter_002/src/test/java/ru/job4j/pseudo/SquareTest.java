package ru.job4j.pseudo;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class Test.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class SquareTest {
    @Test
    public void whenDrawSquare() {
        Square square = new Square();
        assertThat(
                square.draw(),
                is(
                        new StringBuilder()
                                .append("########").append(System.lineSeparator())
                                .append("#      #").append(System.lineSeparator())
                                .append("#      #").append(System.lineSeparator())
                                .append("#      #").append(System.lineSeparator())
                                .append("########").append(System.lineSeparator())
                                .toString()
                )
        );
    }
}
