package ru.job4j.stream.adress;

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
}
