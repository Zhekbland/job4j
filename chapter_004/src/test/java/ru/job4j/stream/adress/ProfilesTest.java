package ru.job4j.stream.adress;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProfilesTest {

    @Test
    public void whenWeCreateListOfAddress() {
        List<Profile> profileList = Arrays.asList(
                new Profile(new Address("Moscow", "Putina", 5, 207)),
                new Profile(new Address("NN", "Mednolitynaya", 10, 2)),
                new Profile(new Address("Bryansk", "Petra", 10, 1))
        );
        List<Address> expected = Arrays.asList(
                new Address("Moscow", "Putina", 5, 207),
                new Address("NN", "Mednolitynaya", 10, 2),
                new Address("Bryansk", "Petra", 10, 1)
        );
        List<Address> result = Profiles.collect(profileList);
        assertThat(result, is(expected));
    }
}