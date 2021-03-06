package ru.job4j.stream.adress;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class Profiles has static method collect.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 10.08.2019.
 */
public class Profiles {

    /**
     * Method collect create List of Addresses from List of Profiles.
     *
     * @param profiles is List of Profiles.
     * @return List of Addresses.
     */
    public static List<Address> collect(List<Profile> profiles) {
        return profiles.stream()
                .map(Profile::getAddress)
                .collect(Collectors.toList());
    }

    public static List<Address> sortedCollect(List<Profile> profiles) {
        return profiles.stream()
                .sorted(Comparator.comparing(x -> x.getAddress().getCity()))
                .distinct()
                .map(Profile::getAddress)
                .collect(Collectors.toList());

    }
}
