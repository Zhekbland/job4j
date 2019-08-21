package ru.job4j.servlets.http;

import java.util.List;

/**
 * Interface Store.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 21.08.2019.
 */
public interface Store {

    void add(User user);

    void update(User user);

    void delete(User user);

    List<User> findAll();

    User findById(String id);
}
