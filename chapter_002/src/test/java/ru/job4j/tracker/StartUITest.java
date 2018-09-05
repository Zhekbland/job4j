package ru.job4j.tracker;

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
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6", "y"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.getAll()[0].getName(), is( "test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item("test name", "desc"));
        //создаём StubInput с последовательностью действий(производим замену заявки)
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6", "y"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is( "test replace"));
    }

    @Test
    public void whenUserDeleteThenTrackerDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name1", "desc"));
        Input input = new StubInput(new String[] {"3", item.getId(), "6", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll(), is(tracker.findByName( "test name1")));
    }

    @Test
    public void whenUserDeleteWrongIdThenTrackerDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name2", "desc"));
        Input input = new StubInput(new String[] {"3", "12345", "6", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is( "test name2"));
    }

    @Test
    public void whenUserFindByIdThenTrackerFindItemById() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name3", "desc"));
        Input input = new StubInput(new String[] {"4", item.getId(), "6", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getId(), is(item.getId()));
    }


    @Test
    public void whenUserFindByNameThenTrackerFindItemsByName() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test name4", "desc"));
        Item item2 = tracker.add(new Item("test name4", "desc2"));
        Input input = new StubInput (new String[] {"5", item2.getName(), "6", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName(item1.getName()), is(tracker.findByName("test name4")));
    }

}
