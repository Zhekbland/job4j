package ru.job4j.tracker;

import ru.job4j.models.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Tracker'event menu.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 08.09.2018.
 */
public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private List<UserAction> actions = new ArrayList<>();
    private List<Integer> range = new ArrayList<>();

    public MenuTracker(Input input, Tracker tracker) {
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

    private class AddItem implements UserAction {
        private int add;
        private String event;

        public AddItem(int add, String event) {
            this.add = add;
            this.event = event;
        }

        @Override
        public int key() {
            return add;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------Add new item-----------");
            String name = input.ask("Enter item'event name : ");
            String desc = input.ask("Enter item'event description");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("---------New item with getId : " + item.toString() + " ----------");
        }

        @Override
        public String info() {
            return String.format("%d. %s.", this.key(), event);
        }
    }

    private class ShowAllItem implements UserAction {
        private int show;
        private String event;

        public ShowAllItem(int show, String event) {
            this.show = show;
            this.event = event;
        }

        @Override
        public int key() {
            return show;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
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

        @Override
        public String info() {
            return String.format("%d. %s.", this.key(), event);
        }
    }

    private class EditItem implements UserAction {
        private int edit;
        private String event;

        public EditItem(int edit, String event) {
            this.edit = edit;
            this.event = event;
        }

        @Override
        public int key() {
            return edit;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
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

        @Override
        public String info() {
            return String.format("%d. %s.", this.key(), event);
        }
    }

    private class DeleteItem implements UserAction {
        private int delete;
        private String event;

        public DeleteItem(int delete, String event) {
            this.delete = delete;
            this.event = event;
        }

        @Override
        public int key() {
            return delete;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter item'event id for delete : ");
            if (tracker.delete(id)) {
                System.out.println("Item was delete.");
            } else {
                System.out.println("Item not found");
            }
        }

        @Override
        public String info() {
            return String.format("%d. %s.", this.key(), event);
        }
    }

    private class FindByIdItem implements UserAction {
        private int findById;
        private String event;

        public FindByIdItem(int findById, String event) {
            this.findById = findById;
            this.event = event;
        }

        @Override
        public int key() {
            return findById;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter item'event id which you find : ");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println("---------Item which you find - " + item.toString()
                        + " ----------");
            } else {
                System.out.println("You enter wrong id!");
            }
        }

        @Override
        public String info() {
            return String.format("%d. %s.", this.key(), event);
        }
    }

    private class FindByNameItem implements UserAction {
        private int findByName;
        private String event;

        public FindByNameItem(int findByName, String event) {
            this.findByName = findByName;
            this.event = event;
        }

        @Override
        public int key() {
            return findByName;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
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

        @Override
        public String info() {
            return String.format("%d. %s.", this.key(), event);
        }
    }

    private class ExitProgram implements UserAction {
        private int exit;
        private String event;

        public ExitProgram(int exit, String event) {
            this.exit = exit;
            this.event = event;
        }

        @Override
        public int key() {
            return exit;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
        }

        @Override
        public String info() {
            return String.format("%d. %s.", this.key(), event);
        }
    }
}