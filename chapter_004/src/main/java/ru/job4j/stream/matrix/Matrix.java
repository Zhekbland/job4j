package ru.job4j.stream.matrix;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class Matrix has static method.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 10.08.2019.
 */
public class Matrix {

    /**
     * Method toList transforms matrix into flatMap.
     *
     * @param matrix of elements.
     * @return List of elements.
     */
    public static List<Integer> toList(List<List<Integer>> matrix) {
        return matrix.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
