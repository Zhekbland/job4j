package ru.job4j.synchronize;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.DynamicArrayList;

import java.util.Iterator;

/**
 * Class DynamicList creates threadsafe list.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 22.01.2019.
 */
@ThreadSafe
public class DynamicList<E> implements Iterable<E> {
    @GuardedBy("this")
    private DynamicArrayList array = new DynamicArrayList();

    public synchronized void add(E value) {
        array.add(value);
    }

    public synchronized E get(int index) {
        return (E) array.get(index);
    }

    public synchronized DynamicArrayList<E> copy(DynamicArrayList<E> array) {
        DynamicArrayList<E> result = new DynamicArrayList<>();
        for (E element : array) {
            result.add(element);
        }
        return result;
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return copy(array).iterator();
    }
}