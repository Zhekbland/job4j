package ru.job4j.converter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class UserConvertTest testing class UserConvert.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 21.10.2018.
 */
public class UserConvertTest {
    @Test
    public void whenListConvertToMap() {
        UserConvert users = new UserConvert();
        List<User> list = new ArrayList<>();
        list.add(new User(1, "Zheka", "NN"));
        list.add(new User(2, "Alex", "NY"));
        list.add(new User(3, "Dimitry", "Coluer"));
        HashMap<Integer, User> result = users.process(list);
        HashMap<Integer, User> expect = new HashMap<>();
        expect.put(1, new User(1, "Zheka", "NN"));
        expect.put(2, new User(2, "Alex", "NY"));
        expect.put(3, new User(3, "Dimitry", "Coluer"));
        assertThat(result, is(expect));
    }
}
