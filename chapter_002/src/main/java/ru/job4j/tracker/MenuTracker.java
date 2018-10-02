package ru.job4j.tracker;

import ru.job4j.models.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Tracker's menu.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 08.09.2018.
 */
public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private List<UserAction> actions = new ArrayList<>();

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public int getActionsLength() {
        return this.actions.size();
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
        private String s;

        public AddItem(int add, String s) {
            this.add = add;
            this.s = s;
        }

        @Override
        public int key() {
            return add;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------Add new item-----------");
            String name = input.ask("Enter item's name : ");
            String desc = input.ask("Enter item's description");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("---------New item with getId : " + item.toString() + " ----------");
        }

        @Override
        public String info() {
            return String.format("%d. %s.", this.key(), s);
        }
    }

    private class ShowAllItem implements UserAction {
        private int show;
        private String s;

        public ShowAllItem(int show, String s) {
            this.show = show;
            this.s = s;
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
            return String.format("%d. %s.", this.key(), s);
        }
    }

    private class EditItem implements UserAction {
        private int edit;
        private String s;

        public EditItem(int edit, String s) {
            this.edit = edit;
            this.s = s;
        }

        @Override
        public int key() {
            return edit;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----------Add item's id for edit-----------");
            String id = input.ask("Enter item's id : ");
            if (tracker.findById(id) != null) {
                String name1 = input.ask("Enter item's name : ");
                String desc1 = input.ask("Enter item's description : ");
                Item neItem = new Item(name1, desc1);
                tracker.replace(id, neItem);
                System.out.println("---------Item was replace, now new : " + neItem.toString() + " ----------");
            } else {
                System.out.println("You entered wrong id");
            }
        }

        @Override
        public String info() {
            return String.format("%d. %s.", this.key(), s);
        }
    }

    private class DeleteItem implements UserAction {
        private int delete;
        private String s;

        public DeleteItem(int delete, String s) {
            this.delete = delete;
            this.s = s;
        }

        @Override
        public int key() {
            return delete;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter item's id for delete : ");
            if (tracker.delete(id)) {
                System.out.println("Item was delete.");
            } else {
                System.out.println("Item not found");
            }
        }

        @Override
        public String info() {
            return String.format("%d. %s.", this.key(), s);
        }
    }

    private class FindByIdItem implements UserAction {
        private int findById;
        private String s;

        public FindByIdItem(int findById, String s) {
            this.findById = findById;
            this.s = s;
        }

        @Override
        public int key() {
            return findById;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter item's id which you find : ");
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
            return String.format("%d. %s.", this.key(), s);
        }
    }

    private class FindByNameItem implements UserAction {
        private int findByName;
        private String s;

        public FindByNameItem(int findByName, String s) {
            this.findByName = findByName;
            this.s = s;
        }

        @Override
        public int key() {
            return findByName;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter item's name which you find : ");
            Item[] items = tracker.findByName(name);
            System.out.println("Found " + items.length + " item/s");
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }

        @Override
        public String info() {
            return String.format("%d. %s.", this.key(), s);
        }
    }

    private class ExitProgram implements UserAction {
        private int exit;
        private String s;

        public ExitProgram(int exit, String s) {
            this.exit = exit;
            this.s = s;
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
            return String.format("%d. %s.", this.key(), s);
        }
    }
}