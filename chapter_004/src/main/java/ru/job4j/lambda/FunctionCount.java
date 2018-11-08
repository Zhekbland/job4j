package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Class FunctionCount counts lineFunction, squareFunction and logarithmicFunction.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 08.11.2018.
 */
public class FunctionCount {
    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> functions = new ArrayList<>();
        for (int index = start; index <= end; index++) {
            functions.add(func.apply((double) index));
        }
        return functions;
    }
}
