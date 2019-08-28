package ru.job4j.servlets.http;

import java.util.List;

/**
 * Class ValidateService validates duplicate id in List of users.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 4.
 * @since 21.08.2019.
 */
public class ValidateService {

    private final Store logic = MemoryStore.getInstance();

    private final static ValidateService INSTANCE = new ValidateService();

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public User add(User user) {
        return logic.add(user);
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
