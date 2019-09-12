package ru.job4j.servlets;

import ru.job4j.servlets.http.persistent.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserStorage {

    private static final UserStorage INSTANCE = new UserStorage();
    private List<User> users = new CopyOnWriteArrayList<>();

    private UserStorage() {
        this.users.add(new User("root", "root"));
    }

    public static UserStorage getInstance() {
        return INSTANCE;
    }

    public void add(User user) {
        this.users.add(user);
    }

    public List<User> getUsers() {
        return this.users;
    }

    public boolean isCredentional(String login, String password) {
        return this.users.parallelStream()
                .anyMatch(x -> (x.getLogin().equals(login) && x.getPassword().equals(password)));
    }
}
