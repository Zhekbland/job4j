package ru.job4j.phonebook;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class Person create database.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 16.10.2018.
 */
public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Zheka", "Shpytev", "961961961",
                        "Russia"));
        List<Person> persons = phones.find("Zhek");
        assertThat(persons.iterator().next().getName(), is("Zheka"));
    }

    @Test
    public void whenFindByName2() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Zheka", "Shpytev", "961961961",
                        "Russia"));
        phones.add(
                new Person("Peter", "Arsentev", "321312312",
                        "Russia"));
        List<Person> persons = phones.find("ete");
        assertThat(persons.iterator().next().getName(), is("Peter"));
    }
}
