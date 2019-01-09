package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleMap realizes Map.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 3.
 * @since 03.01.2019.
 */
public class SimpleMap<K, V> implements Iterable<SimpleMap.Map> {
    private static final int DEFAULT_CAPACITY = 3;
    private static final float LOAD_FACTOR = 0.5f;

    private Map<K, V>[] container;
    private int capacity = 0;
    private int modeCount = 0;

    public boolean insert(K key, V value) {
        boolean result = false;
        if (container == null) {
            container = new Map[DEFAULT_CAPACITY];
        }
        if (capacity >= container.length * LOAD_FACTOR) {
            capacityExtend();
        }
        int hash = hash(key);
        int index = index(hash);
        if (container[index] == null) {
            container[index] = new Map<>(key, value);
            result = true;
            this.capacity++;
            this.modeCount++;
        }
        return result;
    }

    public void capacityExtend() {
        Map<K, V>[] tempMap = this.container;
        this.container = new Map[container.length * 2];
        this.capacity = 0;
        for (int index = 0; index < tempMap.length; index++) {
            if (tempMap[index] != null) {
                insert(tempMap[index].key, tempMap[index].value);
            }
        }
    }

    private int hash(K key) {
        return (key.hashCode()) ^ (key.hashCode() >>> container.length);
    }

    private int index(int hash) {
        return container.length - 1 & hash;
    }

    public V get(K key) {
        int index = index(hash(key));
        if (container[index] == null || container.length - 1 < index) {
            throw new NoSuchElementException("No Element!");
        }
        V result = container[index].value;
        return result;
    }

    public boolean delete(K key) {
        boolean result = false;
        int index = index(hash(key));
        if (container[index] != null && index < container.length) {
            container[index] = null;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<Map> iterator() {
        return new Iterator<Map>() {
            private int index = 0;
            private int expectedModeCount = modeCount;

            @Override
            public boolean hasNext() {
                boolean result = true;
                if (container == null || index > container.length - 1) {
                    result = false;
                } else if (container[index] == null) {
                    result = false;
                    while (container[index] == null && index < container.length - 1) {
                        index++;
                        if (container[index] != null) {
                            result = true;
                            break;
                        }
                    }
                }
                return result;
            }

            @Override
            public Map next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No element");
                }
                if (modeCount > expectedModeCount) {
                    throw new ConcurrentModificationException();
                }
                return container[index++];
            }
        };
    }

    class Map<K, V> {
        K key;
        V value;

        Map(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}