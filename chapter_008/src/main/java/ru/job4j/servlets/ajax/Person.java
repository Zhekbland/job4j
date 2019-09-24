package ru.job4j.servlets.ajax;

import java.util.Objects;

/**
 * POJO Class Person for JSON mapper.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 20.09.2019.
 */
public class Person {
    private int id;
    private String name;
    private String surname;
    private String gender;
    private String description;

    public Person() {
    }

    public Person(int id, String name, String surname, String gender, String description) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name) && Objects.equals(surname, person.surname)
                && Objects.equals(gender, person.gender) && Objects.equals(description, person.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, gender, description);
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name='" + name + '\'' + ", surname='" + surname + '\''
                + ", gender='" + gender + '\'' + ", description='" + description + '\'' + '}';
    }
}