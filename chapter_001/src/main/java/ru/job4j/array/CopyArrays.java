package ru.job4j.array;

/**
 * Class Turn turns the array.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
import java.util.Arrays;

public class CopyArrays {

    /**
     * Combine array (arr1) and array (arr2) into combineArray
     * @param arr1 first array.
     * @param arr2 second array.
     * @return combine arrays into combArr.
     */
    public int[] combineArrays(int[] arr1, int[] arr2) {
        int[] combArr = Arrays.copyOf(arr1, arr1.length + arr2.length);
        System.arraycopy(arr2, 0, combArr, arr1.length, arr2.length);
        return combArr;
    }
}
