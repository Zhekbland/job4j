package ru.job4j.array;

/**
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class BubbleSort {

    /**
     * Bubble sort.
     * @param array array of elements true, false.
     * @return array after bubble sort.
     */
    public int[] sort(int[] array) {
        for (int index = 0; index < array.length - 1; index++) {
            for (int index2 = 0; index2 < array.length - index - 1; index2++) {
                if (array[index2] > array[index2 + 1]) {
                    int temp = array[index2];
                    array[index2] = array[index2 + 1];
                    array[index2 + 1] = temp;
                }
            }
        }
        return array;
    }
}
