package ru.job4j.compare;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Class ListCompareTest tests class ListCompare.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 25.10.2018.
 */
public class ListCompareTest {
    @Test
    public void whenStringsAreEqualThenZero() {
        ListCompare compare = new ListCompare();
        int result = compare.compare(
                "Ivanov",
                "Ivanov"
        );
        assertThat(result, is(0));
    }

    @Test
    public void whenLeftLessThanRightResultShouldBeNegative() {
        ListCompare compare = new ListCompare();
        int result = compare.compare(
                "Ivanov",
                "Ivanova"
        );
        assertThat(result, lessThan(0));
    }

    @Test
    public void whenLeftGreaterThanRightResultShouldBeNegative() {
        ListCompare compare = new ListCompare();
        int result = compare.compare(
                "Petrov",
                "Ivanova"
        );
        assertThat(result, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftGreaterThanRightShouldBePositive() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                "Petrov",
                "Patrov"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftLessThanRightShouldBeNegative() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                "Patrova",
                "Petrov"
        );
        assertThat(rst, lessThan(0));
    }

    @Test
    public void whenLeftGreaterThanRightResultShouldBePositive() {
        ListCompare compare = new ListCompare();
        int result = compare.compare(
                "Ivanovaaa",
                "Ivanov"
        );
        assertThat(result, greaterThan(0));
    }
}
