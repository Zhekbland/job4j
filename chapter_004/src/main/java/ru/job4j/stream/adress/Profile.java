package ru.job4j.stream.adress;

import java.util.Objects;

/**
 * Class Profile is creating profile with Address Class.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 10.08.2019.
 */
public class Profile {

    private Address address;

    public Profile(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Profile profile = (Profile) o;
        return Objects.equals(address, profile.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
