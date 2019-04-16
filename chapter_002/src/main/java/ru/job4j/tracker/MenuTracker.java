package ru.job4j.tracker;

import ru.job4j.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Class Tracker'event menu.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 08.09.2018.
 */
public class MenuTracker {

    private Input input;
    private ITracker tracker;
    private List<UserAction> actions = new ArrayList<>();
    private List<Integer> range = new ArrayList<>();

    public MenuTracker(Input input, ITracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public int getActionsLength() {
        return this.actions.size();
    }

    public List<Integer> numbersOfOperations() {
        if (!(range.size() == actions.size())) {
            for (int i = 0; i < getActionsLength(); i++) {
                range.add(i);
            }
        }
        return range;
    }

    public void fillActions() {
        this.actions.add(new AddItem(0, "Add new item"));
        this.actions.add(new ShowAllItem(1, "Show all items"));
        this.actions.add(new MenuTracker.EditItem(2, "Edit item"));
        this.actions.add(new MenuTracker.DeleteItem(3, "Delete item"));
        this.actions.add(new FindByIdItem(4, "Find item by Id"));
        this.actions.add(new FindByNameItem(5, "Find items by name"));
        this.actions.add(new ExitProgram(6, "Exit from program"));
    }

    public void show(Consumer<List<UserAction>> consumer) {
        consumer.accept(actions);
    }

    /**
     * Method execute actions.
     *
     * @param key uniq key.
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    private class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, ITracker tracker) {
            System.out.println("----------Add new item-----------");
            String name = input.ask("Enter item'event name : ");
            String desc = input.ask("Enter item'event description");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("---------New item with getId : " + item.toString() + " ----------");
        }
    }

    private class ShowAllItem extends BaseAction {

        public ShowAllItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, ITracker tracker) {
            System.out.println("------------ Show all items --------------");
            Item[] items = tracker.getAll();
            if (items.length > 0) {
                for (Item item : items) {
                    System.out.println(item.toString());
                }
            } else {
                System.out.println("You don't have any. Please, add items.");
            }
        }
    }

    private class EditItem extends BaseAction {

        public EditItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, ITracker tracker) {
            System.out.println("----------Add item'event id for edit-----------");
            String id = input.ask("Enter item'event id : ");
            String name1 = input.ask("Enter item'event name : ");
            String desc1 = input.ask("Enter item'event description : ");
            Item neItem = new Item(name1, desc1);
            if (tracker.replace(id, neItem)) {
                System.out.println("---------Item was replace, now new : " + neItem.toString() + " ----------");
            } else {
                System.out.println("You entered wrong id, item wasn't edit.");
            }
        }
    }

    private class DeleteItem extends BaseAction {

        public DeleteItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, ITracker tracker) {
            String id = input.ask("Enter item'event id for delete : ");
            if (tracker.delete(id)) {
                System.out.println("Item was delete.");
            } else {
                System.out.println("Item not found");
            }
        }
    }

    private class FindByIdItem extends BaseAction {

        public FindByIdItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, ITracker tracker) {
            String id = input.ask("Enter item'event id which you find : ");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println("---------Item which you find - " + item.toString()
                        + " ----------");
            } else {
                System.out.println("You enter wrong id!");
            }
        }
    }

    private class FindByNameItem extends BaseAction {

        public FindByNameItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, ITracker tracker) {
            String name = input.ask("Enter item'event name which you find : ");
            Item[] items = tracker.findByName(name);
            if (items.length > 0) {
                System.out.println("Found " + items.length + " item/event");
                for (Item item : items) {
                    System.out.println(item.toString());
                }
            } else {
                System.out.println("You entered wrong name. " + "Found " + items.length + " item/event");
            }
        }
    }

    private class ExitProgram extends BaseAction {

        public ExitProgram(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, ITracker tracker) {
        }
    }
}