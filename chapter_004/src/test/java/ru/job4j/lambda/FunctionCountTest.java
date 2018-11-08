package ru.job4j.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class FunctionCountTest tests FunctionCount.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 08.11.2018.
 */
public class FunctionCountTest {
    @Test
    public void lineFunctionTest() {
        FunctionCount function = new FunctionCount();
        List<Double> result = function.diapason(
                0, 5,
                index -> (double) 3 + index
        );
        List<Double> except = new ArrayList<>();
        for (int index = 0; index <= 5; index++) {
            except.add((double) 3 + index);
        }
        assertThat(result, is(except));
    }

    @Test
    public void squareFunctionTest() {
        FunctionCount function = new FunctionCount();
        List<Double> result = function.diapason(
                1, 6,
                index -> Math.pow(index, 2)
        );
        List<Double> except = new ArrayList<>();
        for (int index = 1; index <= 6; index++) {
            except.add(Math.pow(index, 2));
        }
        assertThat(result, is(except));
    }

    @Test
    public void logarithmicTest() {
        FunctionCount function = new FunctionCount();
        List<Double> result = function.diapason(
                1, 7,
                index -> (Math.log(index))
        );
        List<Double> except = new ArrayList<>();
        for (int index = 1; index <= 7; index++) {
            except.add(Math.log(index));
        }
        assertThat(result, is(except));
    }
}
