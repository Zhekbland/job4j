package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Class Point finds points of x and y.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version 1
 * @since 08.04.2019
 */
public class PointTest {
    @Test
    public void whenZeroAndTenThenTen() {
        Point point = new Point();
        double result = point.distance(0, 0, 0, 10);
        assertThat(result, is(10D));
    }
}