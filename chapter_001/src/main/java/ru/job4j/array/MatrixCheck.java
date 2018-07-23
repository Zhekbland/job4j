package ru.job4j.array;

/**
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class MatrixCheck {

    /**
     * Check array, diagonal true or false.
     * @param data two-dimensional array.
     * @return diagonal true or false.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        int lastIndArr = data.length - 1;
        for (int i = 0; i < lastIndArr; i++) {
            if ((data[0][0] != data[i + 1][i + 1])
                    || (data[lastIndArr][0]
                    != data[lastIndArr - 1 - i][i + 1])) {
                result = false;
                break;
            }
        }
        return result;
    }
}
