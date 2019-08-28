package ru.job4j.servlets.http;

import java.util.List;

/**
 * Interface Store.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 3.
 * @since 21.08.2019.
 */
public interface Store {

    User add(User user);

    void update(User user);

    void delete(User user);

    List<User> findAll();

    User findById(int id);
}
