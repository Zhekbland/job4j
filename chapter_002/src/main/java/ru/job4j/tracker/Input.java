package ru.job4j.tracker;

import java.util.List;

/**
 * Class TrackerTest for testing.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public interface Input {
    String ask(String question);
    int ask(String question, List<Integer> range);
}

