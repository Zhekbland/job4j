package ru.job4j.statistics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class AnalizeTest is testing class Analize.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 13.01.2019.
 */
public class AnalizeTest {
    private List<Analize.User> current = new ArrayList<>();
    private List<Analize.User> previous = new ArrayList<>();
    private Analize analize = new Analize();

    @Test
    public void whenWeAdd() {
        current.add(new Analize.User(1, "Zhenya"));
        current.add(new Analize.User(2, "Max"));
        current.add(new Analize.User(3, "Alex"));
        current.add(new Analize.User(4, "Inna"));

        previous.add(new Analize.User(1, "Zhenya"));
        previous.add(new Analize.User(2, "Max"));
        previous.add(new Analize.User(3, "Alex"));

        Analize.Info result = analize.diff(previous, current);
        Analize.Info expected = new Analize.Info(1, 0, 0);
        assertThat(result, is(expected));
    }

    @Test
    public void whenWeNothingChange() {
        current.add(new Analize.User(1, "Zhenya"));
        current.add(new Analize.User(2, "Max"));
        current.add(new Analize.User(3, "Alex"));
        current.add(new Analize.User(4, "Inna"));

        previous.add(new Analize.User(1, "Zhenya"));
        previous.add(new Analize.User(2, "Max"));
        previous.add(new Analize.User(3, "Alex"));
        previous.add(new Analize.User(4, "Inna"));
        Analize.Info result = analize.diff(previous, current);
        Analize.Info expected = new Analize.Info(0, 0, 0);
        assertThat(result, is(expected));
    }

    @Test
    public void whenWeDeleteAndChange() {
        current.add(new Analize.User(1, "Zhenya"));
        current.add(new Analize.User(2, "Maxx"));
        current.add(new Analize.User(3, "Alex"));

        previous.add(new Analize.User(1, "Zhenya"));
        previous.add(new Analize.User(2, "Max"));
        previous.add(new Analize.User(3, "Alex"));
        previous.add(new Analize.User(4, "Inna"));
        Analize.Info result = analize.diff(previous, current);
        Analize.Info expected = new Analize.Info(0, 1, 1);
        assertThat(result, is(expected));
    }

    @Test
    public void whenWeAddAndChange() {
        current.add(new Analize.User(1, "Zhenya"));
        current.add(new Analize.User(2, "Max"));
        current.add(new Analize.User(3, "Alexx"));
        current.add(new Analize.User(4, "Inna"));
        current.add(new Analize.User(5, "Petr"));

        previous.add(new Analize.User(1, "Zhenya"));
        previous.add(new Analize.User(2, "Max"));
        previous.add(new Analize.User(3, "Alex"));
        previous.add(new Analize.User(4, "Inna"));
        Analize.Info result = analize.diff(previous, current);
        Analize.Info expected = new Analize.Info(1, 1, 0);
        assertThat(result, is(expected));
    }

    @Test
    public void whenWeAddAndDeleteAndChange() {
        current.add(new Analize.User(1, "Zhenya"));
        current.add(new Analize.User(2, "Max"));
        current.add(new Analize.User(3, "Alexx"));
        current.add(new Analize.User(5, "Petr"));

        previous.add(new Analize.User(1, "Zhenya"));
        previous.add(new Analize.User(2, "Max"));
        previous.add(new Analize.User(3, "Alex"));
        previous.add(new Analize.User(4, "Inna"));
        Analize.Info result = analize.diff(previous, current);
        Analize.Info expected = new Analize.Info(1, 1, 1);
        assertThat(result, is(expected));
    }

    @Test
    public void whenWeAddAndDeleteAndChangeALot() {
        current.add(new Analize.User(1, "Evgeniy"));
        current.add(new Analize.User(2, "Max"));
        current.add(new Analize.User(4, "Innesa"));
        current.add(new Analize.User(5, "Sara"));
        current.add(new Analize.User(6, "John"));
        current.add(new Analize.User(7, "Mikle"));
        current.add(new Analize.User(8, "Bob"));

        previous.add(new Analize.User(1, "Zhenya"));
        previous.add(new Analize.User(2, "Max"));
        previous.add(new Analize.User(3, "Alex"));
        previous.add(new Analize.User(4, "Inna"));
        previous.add(new Analize.User(5, "Sara"));
        previous.add(new Analize.User(6, "John"));
        Analize.Info result = analize.diff(previous, current);
        Analize.Info expected = new Analize.Info(2, 2, 1);
        assertThat(result, is(expected));
    }

    @Test
    public void whenWeAddAndDeleteAndChangeALotAndDifferent() {

        current.add(new Analize.User(2, "Max"));
        current.add(new Analize.User(1, "Zhenyaser"));
        current.add(new Analize.User(6, "Johnes"));
        current.add(new Analize.User(7, "Rex"));

        previous.add(new Analize.User(1, "Zhenya"));
        previous.add(new Analize.User(2, "Max"));
        previous.add(new Analize.User(3, "Alex"));
        previous.add(new Analize.User(4, "Inna"));
        previous.add(new Analize.User(5, "Sara"));
        previous.add(new Analize.User(6, "John"));
        Analize.Info result = analize.diff(previous, current);
        Analize.Info expected = new Analize.Info(1, 2, 3);
        assertThat(result, is(expected));
    }
}