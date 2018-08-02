package ru.job4j.array;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class BubbleSortTest {

    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        BubbleSort array = new BubbleSort();
        int[] input = new int[] {1, 5, 4, 2, 3, 6, 9, 7, 8, 0};
        int[] resultArray = array.sort(input);
        int[] expectArray = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(resultArray, Matchers.is(expectArray));
        //напишите здесь тест, проверяющий сортировку массива из 10 элементов методом пузырька, например {1, 5, 4, 2, 3, 1, 7, 8, 0, 5}.
    }
}
