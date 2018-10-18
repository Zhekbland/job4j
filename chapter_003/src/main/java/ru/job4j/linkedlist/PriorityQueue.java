package ru.job4j.linkedlist;

import java.util.LinkedList;

/**
 * Class Task create priority list.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 17.10.2018.
 */
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    public void put(Task task) {
        int index = tasks.size();
        for (int value = 0; value < tasks.size(); value++) {
            if (tasks.get(value).getPriority() > task.getPriority()) {
                index = value;
                    break;
                }
        }
        tasks.add(index, task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}
