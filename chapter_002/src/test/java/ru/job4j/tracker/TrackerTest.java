package ru.job4j.tracker;

/**
 * Class TrackerTest for testing.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
import ru.job4j.models.*;
import static org.hamcrest.core.Is.is;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrackerTest {

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void delete() {
        Tracker tracker = new Tracker();
        Item saveElement1 = new Item("test1", "testDescription", 123);
        tracker.add(saveElement1);
        Item saveElement2 = new Item("test2", "testDescription", 1234);
        tracker.add(saveElement2);
        Item deleteElement = new Item("test3", "testDescription", 12345);
        tracker.add(deleteElement);
        Item[] testArray = new Item[]{saveElement1, saveElement2};
        tracker.delete(deleteElement.getId());
        assertThat(tracker.getAll(), is(testArray));
    }

    @Test
    public void deleteFirstElement() {
        Tracker tracker = new Tracker();
        Item deleteElement = new Item("test1", "testDescription", 123);
        tracker.add(deleteElement);
        Item saveElement1 = new Item("test2", "testDescription", 1234);
        tracker.add(saveElement1);
        Item saveElement2 = new Item("test3", "testDescription", 12345);
        tracker.add(saveElement2);
        Item[] testArray = new Item[]{saveElement1, saveElement2};
        tracker.delete(deleteElement.getId());
        assertThat(tracker.getAll(), is(testArray));
    }

    @Test
    public void deleteMiddleElement() {
        Tracker tracker = new Tracker();
        Item saveElement1 = new Item("test1", "testDescription", 123);
        tracker.add(saveElement1);
        Item deleteElement = new Item("test2", "testDescription", 1234);
        tracker.add(deleteElement);
        Item saveElement2 = new Item("test3", "testDescription", 12345);
        tracker.add(saveElement2);
        Item[] testArray = new Item[]{saveElement1, saveElement2};
        tracker.delete(deleteElement.getId());
        assertThat(tracker.getAll(), is(testArray));
    }

    @Test
    public void findById() {
        Tracker tracker = new Tracker();
        Item testElement = new Item("test1", "testDescription", 123);
        tracker.add(testElement);
        assertThat(tracker.findById(testElement.getId()).getId(), is(testElement.getId()));
    }

    @Test
    public void findByName() {
        Tracker tracker = new Tracker();
        Item testElement1 = new Item("test1", "testDescription1", 123);
        Item testElement2 = new Item("test2", "testDescription2", 1234);
        tracker.add(testElement1);
        tracker.add(testElement2);
        Item[] testArray = new Item[]{testElement1};
        assertThat(tracker.findByName("test1"), is(testArray));
    }

    @Test
    public void getAll() {
        Tracker tracker = new Tracker();
        Item testElement1 = new Item("test1", "testDescription1", 123);
        Item testElement2 = new Item("test2", "testDescription2", 1234);
        tracker.add(testElement1);
        tracker.add(testElement2);
        Item[] testArray = new Item[]{testElement1, testElement2};
        assertThat(tracker.getAll(), is(testArray));

    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.getAll()[0], is(item));
    }
}