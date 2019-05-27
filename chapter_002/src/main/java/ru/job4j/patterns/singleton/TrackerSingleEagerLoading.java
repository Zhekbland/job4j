package ru.job4j.patterns.singleton;

import ru.job4j.models.Item;
import ru.job4j.tracker.Tracker;

/**
 * Create Singleton with EagerLoading.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 24.05.2019.
 */
public class TrackerSingleEagerLoading {

    private static int count;
    /**
     * Eager loading (momentum creating INSTANCE of TrackerSingleEagerLoading)
     */
    private static final TrackerSingleEagerLoading INSTANCE = new TrackerSingleEagerLoading();
    private Tracker tracker = new Tracker();

    /**
     * Constructor counts amount of INSTANCE.
     */
    private TrackerSingleEagerLoading() {
        count++;
    }

    /**
     * @return INSTANCE of TrackerSingleEagerLoading.
     */
    public static TrackerSingleEagerLoading getInstance() {
        return INSTANCE;
    }

    /**
     * Get amount of instances.
     *
     * @return - amount of instances.
     */
    public static int getAmountOfInstance() {
        return count;
    }

    /**
     * Create data.
     *
     * @param item data.
     * @return item.
     */
    public Item add(Item item) {
        return tracker.add(item);
    }

    /**
     * Replace created data.
     *
     * @param id   of data
     * @param item data.
     * @return true or false.
     */
    public boolean replace(String id, Item item) {
        return tracker.replace(id, item);
    }

    /**
     * Delete data from database.
     *
     * @param id data
     * @return true or false.
     */
    public boolean delete(String id) {
        return tracker.delete(id);
    }

    /**
     * This method find data by id.
     *
     * @param id dara
     * @return found data.
     */
    public Item findById(String id) {
        return tracker.findById(id);
    }

    /**
     * This method find data by name.
     *
     * @param key name of data.
     * @return found data by name.
     */
    public Item[] findByName(String key) {
        return tracker.findByName(key);
    }

    /**
     * This method returns all data.
     *
     * @return all data.
     */
    public Item[] getAll() {
        return tracker.getAll();
    }
}
