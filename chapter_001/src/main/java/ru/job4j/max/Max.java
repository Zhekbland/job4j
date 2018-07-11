package ru.job4j.max;

/**
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class Max {
    /**
     * Maximum from two values by condition.
     * @param first value.
     * @param second value.
     * @return maximum from 2 values.
     */
    public int max (int first, int second) {
        return first > second ? first : second;
    }

    public int max(int first, int second, int third){
        return this.max(this.max(first, second), third);
    }
}
