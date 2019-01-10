package ru.job4j.tree;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class TreeTest is testing class Tree.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 3.
 * @since 09.01.2019.
 */
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenWeIterateAndGet6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        Iterator<Integer> itr = tree.iterator();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
        Integer result = itr.next();
        assertThat(result, is(6));
    }

    @Test
    public void whenWeIterateAndGetFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        Iterator<Integer> itr = tree.iterator();
        itr.next();
        itr.next();
        itr.next();
        Boolean result = itr.hasNext();
        assertThat(result, is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenWeIterateFullQueueAndGetException() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(2, 10);
        Iterator<Integer> itr = tree.iterator();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
    }

    @Test
    public void whenWeHaveOnlyRootAndGetTrue() {
        Tree<Integer> tree = new Tree<>(1);
        Iterator<Integer> itr = tree.iterator();
        Boolean result = itr.hasNext();
        assertThat(result, is(true));
    }

    @Test
    public void whenWeCheckTreeIsBinaryAndGetTrue() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        boolean result = tree.isBinary();
        assertThat(result, is(true));
    }

    @Test
    public void whenWeCheckTreeIsBinaryAndGetFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        tree.add(3, 8);
        boolean result = tree.isBinary();
        assertThat(result, is(false));
    }
}