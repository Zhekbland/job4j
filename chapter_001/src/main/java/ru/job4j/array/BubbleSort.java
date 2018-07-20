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
        int temp = 0;
        for (int index = 0; index < array.length - 1; index++) {
            if (array[index] > array[index + 1] || array[0] > array[1]) {
                index = 0;
                for (int index2 = 0; index2 < array.length - 1; index2++) {
                    if (array[index2] > array[index2 + 1]) {
                        temp = array[index2];
                        array[index2] = array[index2 + 1];
                        array[index2 + 1] = temp;
                    }
                }
            }
        }
        return array;
    }
}
