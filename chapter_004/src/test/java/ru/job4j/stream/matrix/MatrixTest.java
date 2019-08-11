package ru.job4j.stream.matrix;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class MatrixTest is testing class Matrix.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 10.08.2019.
 */
public class MatrixTest {

    @Test
    public void whenWeTransformMatrixIntoList() {
        List<List<Integer>> matrix = List.of(
                List.of(1, 2),
                List.of(3, 4)
        );
        List<Integer> expected = List.of(1, 2, 3, 4);
        List<Integer> result = Matrix.toList(matrix);
        assertThat(result, is(expected));
    }

}