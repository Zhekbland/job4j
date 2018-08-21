package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class CopyArrayStrTest {

    @Test
    public void combineTwoStrArrays() {
        CopyArrayStr copy = new CopyArrayStr();
        String[] inputArr1 = new String[] {"Hellow", " !", " My"};
        String[] inputArr2 = new String[] {" name", " is", " Zheka."};
        String[] result = copy.fullArray(inputArr1, inputArr2);
        String[] expect = new String[] {"Hellow", " !", " My", " name", " is", " Zheka."};
        assertThat(result, is(expect));


    }

}
