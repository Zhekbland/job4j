package ru.job4j.phonebook;

import java.util.ArrayList;
import java.util.List;

/**
 * Class PhoneDictionary create phonebook.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 08.10.2018.
 */
public class PhoneDictionary {
    private List<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (int value = 0; value < persons.size(); value++) {
            if (persons.get(value).getName().contains(key)
                    || persons.get(value).getSurname().contains(key)
                    || persons.get(value).getPhone().contains(key)
                    || persons.get(value).getAddress().contains(key)
                    ) {
                result.add(persons.get(value));
            }
        }
        return result;
    }
}
