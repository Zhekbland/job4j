package ru.job4j.tree;

import java.util.Optional;

/**
 * Interface SimpleTree.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 09.01.2019.
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);
}
