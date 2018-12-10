package ru.job4j.generics;

/**
 * Class UserStore.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 10.12.2018.
 */
public class UserStore extends AbstractStore<User> {
    public UserStore(int size) {
        super(size);
    }
}
