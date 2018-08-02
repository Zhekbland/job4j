package ru.job4j.array;

/**
 * Class Matrix creates table of multiplication.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class Matrix {

    /**
     * Create table of multiplication.
     * @param size array matrix.
     * @return table of multiplication.
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int index1 = 0; index1 < table.length; index1++) {
            for (int index2 = 0; index2 < table.length; index2++) {
                table[index1][index2] = (index1 + 1) * (index2 + 1);
            }
        }
        return table;
    }
}
