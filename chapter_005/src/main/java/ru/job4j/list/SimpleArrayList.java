package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Class SimpleArrayList realises Linked List.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 12.12.2018.
 */
public class SimpleArrayList<E> {

    private int size;
    private Node<E> first;

    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        size++;
    }

    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    public E delete() {
        if (this.first == null) {
            throw new NullPointerException("Null!");
        }
        Node<E> result = this.first;
        this.first = this.first.next;
        size--;
        return (E) result;
    }

    public int getSize() {
        return this.size;
    }

    private static class Node<E> {
        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}
