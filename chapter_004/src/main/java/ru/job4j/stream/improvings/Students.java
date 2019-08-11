package ru.job4j.stream.improvings;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class Students has static method levelOf.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 11.08.2019.
 */
public class Students {

    /**
     * Method level of filtered List of students with notNull,
     * scope greater than bound and sorted.
     *
     * @param students List of students.
     * @param bound    scope for filter.
     * @return filtered List of students.
     */
    public static List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .flatMap(Stream::ofNullable)
                .sorted((o1, o2) -> o2.getScope().compareTo(o1.getScope()))
                .takeWhile(x -> x.getScope() > bound)
                .collect(Collectors.toList());
    }
}
