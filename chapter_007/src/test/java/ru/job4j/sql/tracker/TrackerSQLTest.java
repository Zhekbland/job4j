package ru.job4j.sql.tracker;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Item;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Class TrackerSQLTest is testing TrackerSQL.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 16.04.2019.
 */
public class TrackerSQLTest {
    private List<Item> listItems = new ArrayList<>();

    @Before
    public void init() {
        listItems.add(new Item("Evgeniy", "junior"));
        listItems.add(new Item("Petr", "God"));
        listItems.add(new Item("Max", "middle"));
        listItems.add(new Item("Andrey", "senior"));
        listItems.add(new Item("Max", "junior"));
    }

    @Test
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }

    @Test
    public void addItem() {
        TrackerSQL sql = new TrackerSQL();
        sql.init();
        for (Item item : listItems) {
            sql.add(item);
        }
        Item[] result = sql.getAll();
        sql.close();
        Item[] expect = new Item[listItems.size()];
        listItems.toArray(expect);
        assertThat(result, is(expect));
    }

    @Test
    public void deleteItem() {
        TrackerSQL sql = new TrackerSQL();
        sql.init();
        for (Item item : listItems) {
            sql.add(item);
        }
        boolean result = sql.delete("3");
        sql.close();
        assertThat(result, is(true));
    }

    @Test
    public void findByName() {
        String name = "Max";
        TrackerSQL sql = new TrackerSQL();
        sql.init();
        for (Item item : listItems) {
            sql.add(item);
        }
        Item[] result = sql.findByName(name);
        sql.close();
        listItems.removeIf(item -> !item.getName().equals(name));
        Item[] expect = listItems.toArray(new Item[0]);
        assertThat(result, is(expect));
    }

    @Test
    public void findById() {
        TrackerSQL sql = new TrackerSQL();
        sql.init();
        for (Item item : listItems) {
            sql.add(item);
        }
        Item result = sql.findById("3");
        Item expect = new Item(listItems.get(2).getName(), listItems.get(2).getDescription());
        sql.close();
        assertThat(result, is(expect));
    }

    @Test
    public void findByIdIsEmpty() {
        TrackerSQL sql = new TrackerSQL();
        sql.init();
        for (Item item : listItems) {
            sql.add(item);
        }
        Item result = sql.findById("9");
        Item expect = new Item();
        sql.close();
        assertThat(result, is(expect));
    }

    @Test
    public void replace() {
        TrackerSQL sql = new TrackerSQL();
        sql.init();
        for (Item item : listItems) {
            sql.add(item);
        }
        boolean result = sql.replace("2", new Item("Inna", "team_lead"));
        sql.close();
        assertThat(result, is(true));
    }
}