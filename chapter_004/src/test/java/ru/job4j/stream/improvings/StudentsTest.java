package ru.job4j.stream.improvings;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class StudentsTest is testing method.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 11.08.2019.
 */
public class StudentsTest {

    @Test
    public void whenWeFiltersListOfStudents() {
        List<Student> studentList = Arrays.asList(
                new Student("Jack", 80),
                new Student("Sam", 40),
                new Student("Alien", 50),
                new Student("Rop", 30),
                null,
                new Student("Bob", 70),
                new Student("Din", 67),
                null,
                new Student("Silvia", 90),
                new Student("Tom", 0)
        );
        List<Student> expected = List.of(
                new Student("Silvia", 90),
                new Student("Jack", 80),
                new Student("Bob", 70),
                new Student("Din", 67)


        );
        List<Student> result = Students.levelOf(studentList, 60);
        assertThat(result, is(expected));
    }
}