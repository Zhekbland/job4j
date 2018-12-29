package ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class SimpleSetTest is testing SimpleSet.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 26.12.2018.
 */
public class SimpleSetTest {

    @Test
    public void whenWeAddDuplicateAndFinallyDoNotHaveDuplicate() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(3);
        set.add(3);
        set.add(4);
        Iterator<Integer> itr = set.iterator();
        itr.next();
        itr.next();
        itr.next();
        assertThat(itr.next(), is(4));
    }
}