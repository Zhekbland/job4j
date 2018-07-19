package ru.job4j.array;

/**
 * Wrapper char.
 */
public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Verify, that the word begins with a prefix.
     * @param prefix prefix.
     * @return if the word begings with prefix.
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
            if ((value[0] == data[0]) && (value[1] == data[1])) {
                result = true;
            } else {
                result = false;
            }
        return result;
    }
}