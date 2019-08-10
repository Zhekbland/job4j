package ru.job4j.stream.filter;

import java.util.Objects;

/**
 * Class Student is creating the student.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 09.08.2019.
 */
public class Student {

    private String name;
    private final int score;

    /**
     * Constructor inits field score but if input parameter is different to write 0;
     *
     * @param score input parameter.
     */
    public Student(int score) {
        if (score > 0 && score <= 100) {
            this.score = score;
        } else {
            this.score = 0;
        }
    }

    public Student(String name, int score) {
        this(score);
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
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
        return score == student.score
                && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score);
    }
}
