package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Class FunctionCount counts lineFunction, squareFunction and logarithmicFunction.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 07.11.2018.
 */
public class FunctionCount {
    public double lineFunction(double x) {
        return 3 + x;
    }

    public double squareFunction(double x) {
        return Math.pow(x, 2);
    }

    public double logarithmicFunction(double x) {
        return Math.log(x);
    }

    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> functions = new ArrayList<>();
        for (int index = start; index <= end; index++) {
            functions.add(func.apply((double) index));
        }
        return functions;
    }
}
