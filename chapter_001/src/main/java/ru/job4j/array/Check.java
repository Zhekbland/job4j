package ru.job4j.array;

/**
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class Check {

    /**
     * What does array have, true or false?.
     * @param data array of elements true, false.
     * @return true or false.
     */
    public boolean mono(boolean[] data) {
        boolean result = false;
        int temp = 0;
        int temp2 = 0;
        for (int index = 0; index < data.length; index++) {
            if (data[index]) {
                temp++;
            } else {
                if (data[index] == result) {
                    temp2++;
                }
            }
            if (data.length == temp || data.length == temp2) {
                result = true;
            }
        }
        return result;
    }
}
