package ru.job4j.start;

import ru.job4j.models.*;

public class Tracker {
    public Item[] items = new Item[3];

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        tracker.items[0] = new Item("name1", "desc1", 01);
        tracker.items[1] = new Task("name2", "desc2");
        tracker.items[2] = new Bug();
        for (Item item : tracker.items) {
            if (item instanceof Task) {
                Task task = (Task) item;
                System.out.println(task.calculatePrice());
            }
            System.out.println(item.getName() + " " + item.getDescription());
        }


    }
}
