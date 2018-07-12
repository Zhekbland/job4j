package ru.job4j.loop;

/**
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Factorial {

    /**
     * Calculate factorial.
     * @param n value for calculate factorial.
     * @return result.
     */
    public int calc(int n) {
        int result = 1;
        if (n != 0) {
            for (int index = 1; index <= n; index++) {
                result = result * index;
            }
        }
        return result;
    }
}
