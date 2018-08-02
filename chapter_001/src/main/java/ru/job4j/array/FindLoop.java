package ru.job4j.array;

/**
 * Class FindLoop finds a value in the array.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class FindLoop {
    /**
     * Find the number in the array.
     * @param data array.
     * @param el find number.
     * @return el.
     */
    public int indexOf(int[] data, int el) {
        int result = -1;
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                result = index;
                break;
            }
        }
        return result;
    }
}
