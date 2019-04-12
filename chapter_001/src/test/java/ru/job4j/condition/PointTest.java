package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Class Point finds points of x and y.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version 3
 * @since 08.04.2019
 */
public class PointTest {

    @Test
    public void whenZeroAndTenThenTen() {
        Point first = new Point(0, 0);
        Point second = new Point(0, 10);
        double result = first.distance(second);
        first.info();
        second.info();
        System.out.println(String.format("Result is %s", result));
        assertThat(result, is(10D));
    }

    @Test
    public void whenCheckItself() {
        Point point = new Point(0, 0);
        double result = point.distance(point);
        assertThat(result, is(0D));
    }

    @Test
    public void whenShowInfo() {
        Point first = new Point(1, 1);
        first.info();
        Point second = new Point(2, 2);
        second.info();
    }

    @Test
    public void whenZeroAndTenThenTenThreeDimensional() {
        Point first = new Point(0, 8, 4);
        Point second = new Point(16, 10, 20);
        double result = first.distanceThree(second);
        first.infoThreeDimensional();
        second.infoThreeDimensional();
        System.out.println(String.format("Result is %s", result));
        assertThat(result, closeTo(23D, 0.3));
    }

    @Test
    public void whenCheckItselfThreeDimensional() {
        Point point = new Point(0, 0, 0);
        double result = point.distanceThree(point);
        assertThat(result, is(0D));
    }

    @Test
    public void whenShowInfoThreeDimensional() {
        Point first = new Point(1, 1, 2);
        first.infoThreeDimensional();
        Point second = new Point(5, 5, 5);
        second.infoThreeDimensional();
    }
}