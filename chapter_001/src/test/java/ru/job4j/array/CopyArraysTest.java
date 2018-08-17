package ru.job4j.array;

/**
 * Test.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CopyArraysTest {

    @Test
    public void combineTwoArrays() {
        CopyArrays copy = new CopyArrays();
        int[] input = new int[] {4, 1, 6, 2, 8};
        int[] input2 = new int[] {2, 5, 7, 9};
        int[] result = copy.combineArrays(input, input2);
        int[] expect = new int[] {4, 1, 6, 2, 8, 2, 5, 7, 9};
        assertThat(result, is(expect));
    }
}
