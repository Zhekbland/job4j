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
        for (int index = 0; index < data.length - 1; index++) {
            if (value[index] == data[index]) {
                if (value[value.length - 1] == value[index]) {
                    break;
                }
            } else {
                result = false;
                break;
            }
        }
        return result;
    }
}