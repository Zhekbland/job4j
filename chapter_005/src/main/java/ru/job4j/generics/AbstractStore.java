package ru.job4j.generics;

import java.util.Iterator;

/**
 * Class AbstractStore.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 10.12.2018.
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> store;

    public AbstractStore(int size) {
        this.store = new SimpleArray<>(size);
    }

    @Override
    public void add(T model) {
        this.store.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        Iterator<T> itr = this.store.iterator();
        for (int indArray = 0; itr.hasNext(); indArray++) {
            T temp = itr.next();
            if (temp != null && temp.getId() != null && temp.getId().equals(id)) {
                store.set(indArray, model);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        Iterator<T> itr = store.iterator();
        for (int indArray = 0; itr.hasNext(); indArray++) {
            T temp = itr.next();
            if (temp != null && temp.getId() != null && temp.getId().equals(id)) {
                store.delete(indArray);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        Iterator<T> itr = this.store.iterator();
        while (itr.hasNext()) {
            T temp = itr.next();
            if (temp.getId().equals(id)) {
                result = temp;
                break;
            }
        }
        return result;
    }
}
