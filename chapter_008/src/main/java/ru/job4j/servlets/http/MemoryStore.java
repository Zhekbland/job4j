package ru.job4j.servlets.http;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class MemoryStore creates List of users.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 3.
 * @since 21.08.2019.
 */
public class MemoryStore implements Store {

    private final List<User> userList = new CopyOnWriteArrayList<>();

    private static final MemoryStore INSTANCE = new MemoryStore();

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public User add(User user) {
        user.setId(generateId(user));
        this.userList.add(user);
        return user;
    }

    private long generateId(User user) {
        return Math.abs(user.hashCode());
    }

    @Override
    public void update(User user) {
        this.userList.remove(findById(user));
        this.userList.add(user);
    }

    @Override
    public void delete(User user) {
        this.userList.remove(findById(user));
    }

    @Override
    public List<User> findAll() {
        return this.userList;
    }

    @Override
    public User findById(User user) {
        return this.userList.stream()
                .filter(x -> (x.getId() == generateId(user)) || x.getId() == user.getId())
                .findAny().orElse(null);
    }
}
