package ru.job4j.loop;

/**
 * Class Counter finds even numbers and  sums their.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class Counter {

    /**
     * Find and return even numbers from start to finish.
     * @param start number from start.
     * @param finish number for finish finding.
     * @return sum of even numbers
     */
   public int add(int start, int finish) {
        int sum = 0;
        for (start = start; start <= finish; start++) {
            if (start % 2 == 0) {
                sum = sum + start;
            }
        }
        return sum;
    }
}
