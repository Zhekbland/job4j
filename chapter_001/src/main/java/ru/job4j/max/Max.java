package ru.job4j.max;

/**
 * Class Max calculates Maximum from three values by condition.
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
    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     * Maximum from three values by condition.
     * @param first value.
     * @param second value.
     * @param third value.
     * @return maximum from 3 values.
     */
    public int max(int first, int second, int third) {
        return this.max(this.max(first, second), third);
    }
}
