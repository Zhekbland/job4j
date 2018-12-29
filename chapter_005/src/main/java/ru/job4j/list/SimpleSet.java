package ru.job4j.list;

import java.util.Iterator;

/**
 * Class SimpleSet realizes Set.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 26.12.2018.
 */
public class SimpleSet<E> implements Iterable<E> {
    private DynamicArrayList<E> set = new DynamicArrayList<>();

    public void add(E value) {
        if (set.getCapacity() == 0) {
            set.add(value);
        } else {
            checkDuplicate(value);
        }
    }

    public void checkDuplicate(E value) {
        boolean duplicate = false;
        for (E element : set) {
            if (element.equals(value)) {
                duplicate = true;
                break;
            }
        }
        if (!duplicate) {
            set.add(value);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }
}
