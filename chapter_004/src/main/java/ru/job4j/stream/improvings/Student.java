package ru.job4j.stream.improvings;

import java.util.Objects;

/**
 * Class Student create name and scope of student.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 11.08.2019.
 */
public class Student {

    private final String name;
    private final Integer scope;

    public Student(String name, Integer scope) {
        this.name = name;
        this.scope = scope;
    }

    public Integer getScope() {
        return scope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(name, student.name)
                && Objects.equals(scope, student.scope);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, scope);
    }
}
