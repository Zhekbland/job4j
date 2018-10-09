package ru.job4j.tracker;

import java.util.List;
import java.util.Scanner;

/**
 * Class TrackerTest for testing.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    public int ask(String question, List<Integer> range) {
        int key = Integer.valueOf(this.ask(question));
        if (!(range.contains(key))) {
            throw new MenuOutException("Out of menu range");
        }
        return key;
    }
}
