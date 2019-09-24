package ru.job4j.servlets.ajax;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PersonTest {

    @Test
    public void createPerson() {
        Person person = new Person(1, "Inna", "Inna",
                "female", "person");
        person.setId(2);
        person.setName("Petr");
        person.setSurname("Ars");
        person.setGender("male");
        person.setDescription("developer");
        assertThat(person.getId(), is(2));
        assertThat(person.getName(), is("Petr"));
        assertThat(person.getSurname(), is("Ars"));
        assertThat(person.getGender(), is("male"));
        assertThat(person.getDescription(), is("developer"));
    }
}