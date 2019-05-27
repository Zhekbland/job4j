package ru.job4j.patterns.singleton;

import org.junit.Test;
import ru.job4j.models.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Testing
 * TrackerSingleEagerLoading.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 27.05.2019.
 */
public class TrackerSingleLazyLoadingTest {

    private TrackerSingleLazyLoading trackerSingleton = TrackerSingleLazyLoading.getInstance();

    @Test
    public void whenWeDoAllActions() {
        Item previous = new Item("test1", "testDescription", 123L);
        trackerSingleton.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        trackerSingleton.replace(previous.getId(), next);
        Item deleteElement = new Item("test3", "testDescription", 12345);
        trackerSingleton.add(deleteElement);
        trackerSingleton.delete(deleteElement.getId());
        Item result1 = trackerSingleton.findById(next.getId());
        Item[] result2 = trackerSingleton.findByName(next.getName());
        Item[] result2Array = {next};
        Item saveElement = new Item("test4", "testDescription", 123);
        trackerSingleton.add(saveElement);
        Item[] result3Array = new Item[]{next, saveElement};
        Item[] result3 = trackerSingleton.getAll();
        assertThat(result1, is(next));
        assertThat(result2, is(result2Array));
        assertThat(result3, is(result3Array));
    }
}