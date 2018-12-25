package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class DynamicLinkedList realizes LinkedList.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 25.12.2018.
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

    public boolean isEmpty() {
        return this.first == null;
    }

    public E poll() {
        if (this.first == null) {
            throw new NullPointerException("Null!");
        }
        Node<E> result = this.first;
        this.first = this.first.next;
        size--;
        modCount++;
        return result.value;
    }

    public boolean hasCycle(Node first) {
        boolean result = false;
        Node slow = first;
        Node fast = first;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow.equals(fast)) {
                result = true;
                break;
            }
        }
        return result;
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