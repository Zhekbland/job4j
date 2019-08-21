package ru.job4j.servlets.http;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class MemoryStore creates List of users.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 21.08.2019.
 */
public class MemoryStore implements Store {

    private final List<User> userList = new CopyOnWriteArrayList<>();

    private static final MemoryStore INSTANCE = new MemoryStore();

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        this.userList.add(user);
    }

    @Override
    public void update(User user) {
        User result = findById(user.getId());
        if (result != null) {
            this.userList.remove(result);
            this.userList.add(user);
        }
    }

    @Override
    public void delete(User user) {
        this.userList.remove(user);
    }

    @Override
    public List<User> findAll() {
        return this.userList;
    }

    @Override
    public User findById(String id) {
        User result = null;
        for (User user : this.userList) {
            if (user.getId().equals(id)) {
                result = user;
                break;
            }
        }
        return result;
    }
}
