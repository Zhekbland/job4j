package ru.job4j.tracker;

import ru.job4j.models.*;

import java.util.*;

/**
 * Class Tracker create database.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class Tracker {
    private Item[] items = new Item[100];
    private  int position = 0;
    private static final Random RN = new Random();

    /**
     * Create data.
     * @param item data.
     * @return item.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
        return item;
    }

    /**
     * Replace created data.
     * @param id of data
     * @param item data.
     * @return true or false.
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getId().equals(id)) {
                item.setId(id);
                this.items[index] = item;
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Delete data from database.
     * @param id data
     * @return true or false.
     */
    public boolean delete(String id) {
        boolean result = false;
        for (int index = 0; index != this.position; index++) {
            if (this.items[index].getId().equals(id)) {
                if (items.length > this.position) {
                    System.arraycopy(this.items, index + 1, this.items, index, this.position - index);
                    this.position--;
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * This method find data by id.
     * @param id dara
     * @return found data.
     */
    protected Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * This method find data by name.
     * @param key name of data.
     * @return found data by name.
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[this.position];
        int resultIndex = 0;
        for (int index = 0; index != this.position; index++) {
            if (this.items[index].getName().equals(key)) {
                result[resultIndex] = items[index];
                resultIndex++;
            }
        }
        return Arrays.copyOf(result, resultIndex);
    }

    /**
     * This method generates id.
     * @return id.
     */
    String generateId() {
        return  String.valueOf(System.currentTimeMillis() + RN.nextInt(100));
    }

    /**
     * This method returns all data.
     * @return all data.
     */
    public Item[] getAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }
}

