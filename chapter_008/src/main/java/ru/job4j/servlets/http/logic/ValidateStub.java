package ru.job4j.servlets.http.logic;

import ru.job4j.servlets.http.persistent.User;
import ru.job4j.servlets.http.persistent.Validate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class ValidateStub creates stub for mockito-power testing.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 11.09.2019.
 */
public class ValidateStub implements Validate {
    private final Map<Integer, User> store = new HashMap<>();
    private int ids = 0;

    @Override
    public User add(User user) {
        user.setId(this.ids++);
        this.store.put(user.getId(), user);
        return user;
    }

    @Override
    public boolean update(User user) {
        boolean result = findById(user.getId()) != null;
        if (result) {
            this.store.replace(user.getId(), user);
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        boolean result = findById(user.getId()) != null;
        if (result) {
            this.store.remove(user.getId());
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.store.values());
    }

    @Override
    public User findById(int id) {
        return this.store.get(id);
    }

    @Override
    public User findByLogin(String login) {
        return findAll().parallelStream().filter(user -> user.getLogin().equals(login)).findFirst().orElseThrow();
    }

    @Override
    public boolean isCredentional(String login, String password) {
        return findAll().parallelStream()
                .anyMatch(user -> (user.getLogin().equals(login) && user.getPassword().equals(password)));
    }
}
