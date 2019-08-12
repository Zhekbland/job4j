package ru.job4j.stream.filter;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class SchoolTest is testing.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 09.08.2019.
 */
public class SchoolTest {

    private List<Student> students;

    @Before
    public void init() {
        this.students = Arrays.asList(
                new Student(45),
                new Student(90),
                new Student(30),
                new Student(40),
                new Student(50),
                new Student(60),
                new Student(65),
                new Student(80),
                new Student(99),
                new Student(75),
                new Student(100),
                new Student(0)
        );
    }

    @Test
    public void whenScoreGraterThenOrEqualSeventyAndLessThenOrEqualOneHundred() {
        List<Student> result = School.collect(this.students, x -> x.getScore() >= 70 && x.getScore() <= 100);
        List<Student> expected = Arrays.asList(
                new Student(90),
                new Student(80),
                new Student(99),
                new Student(75),
                new Student(100)
        );
        assertThat(result, is(expected));
    }

    @Test
    public void whenScoreGraterThenOrEqualFiftyAndLessThenSeventy() {
        List<Student> result = School.collect(this.students, x -> x.getScore() >= 50 && x.getScore() < 70);
        List<Student> expected = Arrays.asList(
                new Student(50),
                new Student(60),
                new Student(65)
        );
        assertThat(result, is(expected));
    }

    @Test
    public void whenScoreGraterThenZeroAndLessThenFifty() {
        List<Student> result = School.collect(this.students, x -> x.getScore() > 0 && x.getScore() < 50);
        List<Student> expected = Arrays.asList(
                new Student(45),
                new Student(30),
                new Student(40)
        );
        assertThat(result, is(expected));
    }

    @Test
    public void whenWeTurnListOfStudentIntoMap() {
        Student student1 = new Student("Bob", 45);
        Student student2 = new Student("Sam", 90);
        Student student3 = new Student("John", 30);
        Student student4 = new Student("John", 30);
        Student student5 = new Student("Silvia", 40);

        List<Student> studentList = Arrays.asList(student1, student2, student3, student4, student5);
        Map<String, Student> expected = new HashMap<>();
        expected.put("Bob", student1);
        expected.put("Sam", student2);
        expected.put("John", student3);
        expected.put("Silvia", student5);
        Map<String, Student> result = School.toMap(studentList);
        assertThat(result, is(expected));
    }
}