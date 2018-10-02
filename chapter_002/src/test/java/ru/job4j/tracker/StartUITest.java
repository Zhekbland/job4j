package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import ru.job4j.models.*;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Class TrackerTest for testing.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class StartUITest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    StringBuilder pic = new StringBuilder();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenUserDeleteThenTrackerDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name1", "desc"));
        Input input = new StubInput(new String[]{"3", item.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll(), is(tracker.findByName("test name1")));
    }

    @Test
    public void whenUserDeleteWrongIdThenTrackerDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name2", "desc"));
        Input input = new StubInput(new String[]{"3", "12345", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name2"));
    }

    @Test
    public void whenUserFindByIdThenTrackerFindItemById() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name3", "desc"));
        Input input = new StubInput(new String[]{"4", item.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getId(), is(item.getId()));
    }


    @Test
    public void whenUserFindByNameThenTrackerFindItemsByName() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test name4", "desc"));
        Item item2 = tracker.add(new Item("test name4", "desc2"));
        Input input = new StubInput(new String[]{"5", item2.getName(), "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName(item1.getName()), is(tracker.findByName("test name4")));
    }

    @Test
    public void whenUserAddItemThenTrackerPrintAllItems() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test name1", "desc1"));
        Item item2 = tracker.add(new Item("test name2", "desc2"));
        Item item3 = tracker.add(new Item("test name3", "desc3"));
        Input input = new StubInput(new String[]{"1", "y"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("0. Add new item.").append(System.lineSeparator())
                                .append("1. Show all items.").append(System.lineSeparator())
                                .append("2. Edit item.").append(System.lineSeparator())
                                .append("3. Delete item.").append(System.lineSeparator())
                                .append("4. Find item by Id.").append(System.lineSeparator())
                                .append("5. Find items by name.").append(System.lineSeparator())
                                .append("6. Exit from program.").append(System.lineSeparator())
                                .append("------------ Show all items --------------").append(System.lineSeparator())
                                .append(item1.toString()).append(System.lineSeparator())
                                .append(item2.toString()).append(System.lineSeparator())
                                .append(item3.toString()).append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenUserDeleteItemThenTrackerDelete() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test name1", "desc1"));
        Item item2 = tracker.add(new Item("test name2", "desc2"));
        Item item3 = tracker.add(new Item("test name3", "desc3"));
        Input input = new StubInput(new String[]{"3", item2.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("0. Add new item.").append(System.lineSeparator())
                                .append("1. Show all items.").append(System.lineSeparator())
                                .append("2. Edit item.").append(System.lineSeparator())
                                .append("3. Delete item.").append(System.lineSeparator())
                                .append("4. Find item by Id.").append(System.lineSeparator())
                                .append("5. Find items by name.").append(System.lineSeparator())
                                .append("6. Exit from program.").append(System.lineSeparator())
                                .append("Item was delete.").append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenUserEditItemThenTrackerEdit() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test name", "desc"));
        Item item2 = tracker.add(new Item("test name2", "desc2"));
        Item item3 = tracker.add(new Item("test name", "desc3"));
        Item item4 = tracker.add(new Item("test name4", "desc4"));
        Input input = new StubInput(new String[]{"5", item1.getName(), "y"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("0. Add new item.").append(System.lineSeparator())
                                .append("1. Show all items.").append(System.lineSeparator())
                                .append("2. Edit item.").append(System.lineSeparator())
                                .append("3. Delete item.").append(System.lineSeparator())
                                .append("4. Find item by Id.").append(System.lineSeparator())
                                .append("5. Find items by name.").append(System.lineSeparator())
                                .append("6. Exit from program.").append(System.lineSeparator())
                                .append("Found 2 item/s").append(System.lineSeparator())
                                .append(item1.toString()).append(System.lineSeparator())
                                .append(item3.toString()).append(System.lineSeparator())
                                .toString()
                )
        );
    }
}
