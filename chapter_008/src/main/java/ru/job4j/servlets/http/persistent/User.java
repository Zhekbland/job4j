package ru.job4j.servlets.http.persistent;

import java.util.Objects;

/**
 * Class User creates instances of users.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 4.
 * @since 21.08.2019.
 */
public class User {

    private int id;
    private String name;
    private String login;
    private String email;
    private String password;
    private Role role;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(int id, String name, String login, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
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
        return Objects.equals(name, user.name)
                && Objects.equals(login, user.login)
                && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, email);
    }

    @Override
    public String toString() {
        return "User: name = " + this.name + ", login = "
                + this.login + ", email = " + this.email + "    ";
    }
}