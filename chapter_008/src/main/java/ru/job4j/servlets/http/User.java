package ru.job4j.servlets.http;

import java.util.Objects;

/**
 * Class User creates instances of users.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 3.
 * @since 21.08.2019.
 */
public class User {

    private long id;
    private String name;
    private String login;
    private String email;

    public User(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
    }

    public User(long id, String name, String login, String email) {
        this(name, login, email);
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(name, user.name)
                && Objects.equals(login, user.login)
                && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, email);
    }

    @Override
    public String toString() {
        return "User{" + "id='" + id + '\'' + ", name='" + name + '\''
                + ", login='" + login + '\'' + ", email='" + email + '\''
                + '}' + "\n";
    }
}
