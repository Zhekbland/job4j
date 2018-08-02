package ru.job4j.array;

/**
 * Class Square fills the array.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class Square {
    /**
     * Fill the array.
     * @param bound array.
     * @return Screen.
     */
    public int[] calculate(int bound) {
        int[] result = new int[bound];
        for (int index = 0; index < bound; index++) {
            result[index] = (index + 1) * (index + 1);
        }
        return result;
    }
}
