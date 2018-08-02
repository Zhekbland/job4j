package ru.job4j.array;

/**
 * Class MatrixCheck checks the array diagonally.
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
        for (int index = 0; index < lastIndArr; index++) {
            if ((data[0][0] != data[index + 1][index + 1])
                    || (data[lastIndArr][0]
                    != data[lastIndArr - 1 - index][index + 1])) {
                result = false;
                break;
            }
        }
        return result;
    }
}
