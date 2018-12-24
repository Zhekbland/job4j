package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class DynamicLinkedList realizes LinkedList.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 14.12.2018.
 */
public class DynamicLinkedList<E> implements Iterable<E> {
    private Node<E> first;
    private int size;
    private int modCount;

    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        size++;
        modCount++;
    }

    public E get(int index) {
        if (index >= size) {
            throw new NoSuchElementException();
        }
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = first;
            private int expectedModeCount = modCount;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No such element!");
                }
                if (expectedModeCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                Node<E> result = current;
                current = current.next;
                return result.value;
            }
        };
    }

    public static class Node<E> {
        E value;
        Node<E> next;

        Node(E value) {
            this.value = value;
        }
    }
}