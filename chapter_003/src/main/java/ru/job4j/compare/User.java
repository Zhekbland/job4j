package ru.job4j.compare;

public class User implements Comparable<User> {
    private String name;
    public int age;

    @Override
    public int compareTo(User o) {
        return Integer.compare(age, o.age);
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
