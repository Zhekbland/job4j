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
        if (tasks.size() > 0) {
            for (int index = 0; index < tasks.size(); index++) {
                if (tasks.get(index).getPriority() > task.getPriority()) {
                    tasks.add(index, task);
                    break;
                } else if (index == (tasks.size() - 1)) {
                    tasks.addLast(task);
                    break;
                }
            }
        } else {
            tasks.add(task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}
