package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Class TrackerTest for testing.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */

public class StartUI {

    private final Input input;
    private final ru.job4j.tracker.Tracker tracker;

    public StartUI(Input input, ru.job4j.tracker.Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        do {
            menu.show();
            menu.select(input.ask("Select: ", menu.numbersOfOperations()));
        } while (!"y".equals(this.input.ask("Do you want to exit? (y): ")));
    }

    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
    }
}
