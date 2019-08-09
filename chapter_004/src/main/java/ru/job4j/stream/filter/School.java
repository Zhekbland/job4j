package ru.job4j.stream.filter;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class School is creating filter for class of students.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 09.08.2019.
 */
public class School {

    /**
     * Method collect filter for class of students.
     *
     * @param students class of students.
     * @param predict  input filter.
     * @return filtered list of student.
     */
    public static List<Student> collect(List<Student> students, Predicate<Student> predict) {
        return students.stream().filter(predict).collect(Collectors.toList());
    }
}
