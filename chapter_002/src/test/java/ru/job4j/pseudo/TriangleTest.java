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
public class TriangleTest {
    @Test
    public void whenDrawSquare() {
        Triangle triangle = new Triangle();
        assertThat(
                triangle.draw(),
                is(
                        new StringBuilder()
                                .append("    #    ").append(System.lineSeparator())
                                .append("   # #   ").append(System.lineSeparator())
                                .append("  #   #  ").append(System.lineSeparator())
                                .append(" #     # ").append(System.lineSeparator())
                                .append("#########").append(System.lineSeparator())
                                .toString()
                )
        );
    }
}
