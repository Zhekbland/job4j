package ru.job4j.nonblock;

/**
 * Class Base creates fields id and version.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 28.01.2019.
 */
public class Base {
    private int id;
    private int version;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
