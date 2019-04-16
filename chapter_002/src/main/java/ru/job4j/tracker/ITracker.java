package ru.job4j.tracker;

import ru.job4j.models.Item;

public interface ITracker {
    Item add(Item item);

    boolean replace(String id, Item item);

    boolean delete(String id);

    Item[] getAll();

    Item[] findByName(String key);

    Item findById(String id);
}
