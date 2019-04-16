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
    private final ITracker tracker;

    public StartUI(Input input, ITracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public static void accept(List<UserAction> list) {
        list.forEach(userAction -> System.out.println(userAction.info()));
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        do {
            menu.show(StartUI::accept);
            menu.select(input.ask("Select: ", menu.numbersOfOperations()));
        } while (!"y".equals(this.input.ask("Do you want to exit? (y): ")));
    }

    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}
