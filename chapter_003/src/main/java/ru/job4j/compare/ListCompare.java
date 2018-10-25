package ru.job4j.compare;

import java.util.Comparator;

/**
 * Class ListCompare to compares and sorts elements.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 25.10.2018.
 */
public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result = 0;
        int shortestLength = (left.length() <= right.length()) ? left.length() : right.length();
        for (int index = 0; index < shortestLength; index++) {
            if (left.charAt(index) != right.charAt(index)) {
                result = Character.compare(left.charAt(index), right.charAt(index));
                break;
            }
        }
        return result != 0 ? result : Integer.compare(left.length(), right.length());
    }
}
