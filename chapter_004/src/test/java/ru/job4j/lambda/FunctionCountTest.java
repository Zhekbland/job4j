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
 * @version 1.
 * @since 07.11.2018.
 */
public class FunctionCountTest {
    @Test
    public void lineFunctionTest() {
        FunctionCount function = new FunctionCount();
        List<Double> result = function.diapason(
                0, 5,
                function::lineFunction
        );
        List<Double> except = new ArrayList<>();
        for (int index = 0; index <= 5; index++) {
            except.add(function.lineFunction(index));
        }
        assertThat(result, is(except));
    }

    @Test
    public void squareFunctionTest() {
        FunctionCount function = new FunctionCount();
        List<Double> result = function.diapason(
                0, 6,
                function::squareFunction
        );
        List<Double> except = new ArrayList<>();
        for (int index = 0; index <= 6; index++) {
            except.add(function.squareFunction(index));
        }
        assertThat(result, is(except));
    }

    @Test
    public void logarithmicTest() {
        FunctionCount function = new FunctionCount();
        List<Double> result = function.diapason(
                0, 4,
                function::logarithmicFunction
        );
        List<Double> except = new ArrayList<>();
        for (int index = 0; index <= 4; index++) {
            except.add(function.logarithmicFunction(index));
        }
        assertThat(result, is(except));
    }
}
