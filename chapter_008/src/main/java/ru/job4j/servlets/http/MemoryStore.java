package ru.job4j.servlets.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class MemoryStore creates List of users.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 4.
 * @since 21.08.2019.
 */
public class MemoryStore implements Store {

    /**
     * Concurrent HashMap for concurrency.
     */
    private final Map<Integer, User> userMap = new ConcurrentHashMap<>();

    private static final MemoryStore INSTANCE = new MemoryStore();

    /**
     * Atomic variable for support concurrency.
     */
    private volatile AtomicInteger id = new AtomicInteger();

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public User add(User user) {
        User result = null;
        if (!this.userMap.containsValue(user)) {
            int userId = id.incrementAndGet();
            user.setId(userId);
            this.userMap.put(user.getId(), user);
            result = user;
        }
        return result;
    }

    @Override
    public void update(User user) {
        this.userMap.replace(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        this.userMap.remove(user.getId());
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.userMap.values());
    }

    @Override
    public User findById(int id) {
        return this.userMap.get(id);
    }
}
