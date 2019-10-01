package ru.job4j.servlets.http.logic;

import ru.job4j.servlets.http.persistent.DBStore;
import ru.job4j.servlets.http.persistent.Store;
import ru.job4j.servlets.http.persistent.User;
import ru.job4j.servlets.http.persistent.Validate;

import java.util.List;

/**
 * Class ValidateService validates duplicate id in List of users.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 4.
 * @since 21.08.2019.
 */
public class ValidateService implements Validate {

    /**
     * Instance of singleton.
     */
    private final Store logic = DBStore.getInstance();

    /**
     * Instance of singleton.
     */
    private final static ValidateService INSTANCE = new ValidateService();

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public User add(User user) {
        List<User> users = findAll();
        return !users.contains(user) ? logic.add(user) : null;
    }

    @Override
    public boolean update(User user) {
        boolean result = findByLogin(user.getLogin()) != null;
        if (result) {
            logic.update(user);
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        boolean result = findById(user.getId()) != null;
        if (result) {
            logic.delete(user);
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        return logic.findAll();
    }

    @Override
    public User findById(int id) {
        return this.logic.findById(id);
    }

    @Override
    public User findByLogin(String login) {
        return this.logic.findAll().parallelStream().filter(user -> user.getLogin().equals(login)).findFirst()
                .orElse(null);
    }

    @Override
    public boolean isCredentional(String login, String password) {
        return this.logic.findAll().parallelStream()
                .anyMatch(user -> (user.getLogin().equals(login) && user.getPassword().equals(password)));
    }
}