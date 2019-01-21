package ru.job4j.synchronize;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Class UserStorage creates data structure.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 20.01.2019.
 */
@ThreadSafe
public class UserStorage {
    private Map<Integer, Integer> storage = new HashMap<>();

    public synchronized boolean add(User user) {
        boolean result = true;
        if (!storage.containsKey(user.getId())) {
            storage.put(user.getId(), user.getAmount());
        } else {
            result = false;
        }
        return result;
    }

    public synchronized boolean update(User user) {
        boolean result = true;
        if (!storage.containsKey(user.getId())) {
            result = false;
        } else {
            storage.put(user.getId(), user.getAmount());
        }
        return result;
    }

    public synchronized boolean delete(User user) {
        boolean result = true;
        if (!storage.containsKey(user.getId())) {
            result = false;
        } else {
            storage.remove(user.getId(), user.getAmount());
        }
        return result;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = true;
        if (!storage.containsKey(fromId) || !storage.containsKey(toId)
                || storage.get(fromId) < amount) {
            result = false;
        } else {
            User userFrom = new User(fromId, storage.get(fromId) - amount);
            update(userFrom);
            User userIn = new User(toId, storage.get(toId) + amount);
            update(userIn);
        }
        return result;
    }

    public synchronized Integer getAmount(int id) {
        return storage.get(id);
    }
}
