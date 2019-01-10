package ru.job4j.tree;

import java.util.*;

/**
 * Class Tree creates SimpleTree.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 3.
 * @since 09.01.2019.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;

    public Tree(E root) {
        this.root = new Node<E>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (!findBy(child).isPresent()) {
            result = true;
            findBy(parent).get().add(new Node<>(child));
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                result = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    public boolean isBinary() {
        boolean result = true;
        Iterator<E> itr = iterator();
        while (itr.hasNext()) {
            E resultItr = itr.next();
            if (findBy(resultItr).get().leaves().size() > 2) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            Queue<Node<E>> descendants = new LinkedList<>(Collections.singleton(root));

            @Override
            public boolean hasNext() {
                return !descendants.isEmpty();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No element!");
                }
                Node<E> result = descendants.poll();
                E e = result.getValue();
                descendants.addAll(result.leaves());
                return e;
            }
        };
    }
}
