package ru.job4j.servlets.http.logic;

import ru.job4j.servlets.http.persistent.DBStore;
import ru.job4j.servlets.http.persistent.Store;
import ru.job4j.servlets.http.persistent.User;

import java.util.List;

/**
 * Class ValidateService validates duplicate id in List of users.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 4.
 * @since 21.08.2019.
 */
public class ValidateService {

    private final Store logic = DBStore.getInstance();

    private final static ValidateService INSTANCE = new ValidateService();

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public User add(User user) {
        List<User> users = findAll();
        return !users.contains(user) ? logic.add(user) : null;
    }

    public boolean update(User user) {
        boolean result = findById(user.getId()) != null;
        if (result) {
            logic.update(user);
        }
        return result;
    }

    public boolean delete(User user) {
        boolean result = findById(user.getId()) != null;
        if (result) {
            logic.delete(user);
        }
        return result;
    }

    public List<User> findAll() {
        return logic.findAll();
    }


    public User findById(int id) {
        return logic.findById(id);
    }
}
