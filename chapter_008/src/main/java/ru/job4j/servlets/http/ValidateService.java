package ru.job4j.servlets.http;

import java.util.List;

/**
 * Class ValidateService validates duplicate id in List of users.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 21.08.2019.
 */
public class ValidateService {

    private final Store logic = MemoryStore.getInstance();

    private final static ValidateService INSTANCE = new ValidateService();

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public void add(User user) {
        if (findById(user)) {
            logic.add(user);
        }
    }


    public void update(User user) {
        if (!findById(user)) {
            logic.update(user);
        }
    }

    public void delete(User user) {
        if (!findById(user)) {
            logic.delete(user);
        }
    }

    public List<User> findAll() {
        return logic.findAll();
    }

    public boolean findById(User user) {
        return logic.findById(user.getId()) == null;
    }
}
