package ru.job4j.servlets.http.persistent;

import java.util.List;

/**
 * Interface Validate.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 21.08.2019.
 */
public interface Validate {
    User add(User user);

    boolean update(User user);

    boolean delete(User user);

    List<User> findAll();

    User findById(int id);

    User findByLogin(String login);

    boolean isCredentional(String login, String password);
}
