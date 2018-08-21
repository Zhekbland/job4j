package ru.job4j.array;

/**
 * Class Turn turns the array.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
import java.util.Arrays;

public class CopyArrayStr {

    /**
     * Combine strings.
     * Combine array (arr1) and array (arr2) into combineArray
     * @param arr1 first array.
     * @param arr2 second array.
     * @return combine arrays into combArr.
     */
    public String[] fullArray(String[] arr1, String[] arr2) {
        String[] fullArr = Arrays.copyOf(arr1, arr1.length + arr2.length);
        System.arraycopy(arr2, 0, fullArr, arr1.length, arr2.length);
        return fullArr;
    }
}
