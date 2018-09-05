package ru.job4j.tracker;

/**
 * Class TrackerTest for testing.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
import ru.job4j.models.*;

public class StartUI {

    private static final String ADD = "0";

    private static final String SHOW_ALL_ITEMS = "1";

    private static final String EDIT_ITEMS = "2";

    private static final String DELETE_ITEMS = "3";

    private static final String FIND_ITEMS_BY_ID = "4";

    private static final String FIND_ITEMS_BY_NAME = "5";

    private static final String EXIT = "6";

    private final Input input;

    private final ru.job4j.tracker.Tracker tracker;

    public StartUI(Input input, ru.job4j.tracker.Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Initialization.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Please, enter the tasks's name: ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW_ALL_ITEMS.equals(answer)) {
                this.showAllItems();
            } else if (EDIT_ITEMS.equals(answer)) {
                this.editItems();
            } else if (DELETE_ITEMS.equals(answer)) {
                this.deleteItems();
            } else if (FIND_ITEMS_BY_ID.equals(answer)) {
                this.findItemsById();
            } else if (FIND_ITEMS_BY_NAME.equals(answer)) {
                this.findItemsByName();
            } else if (EXIT.equals(answer)) {
                System.out.println("You want to leave?");
                String name = this.input.ask("Enter Y or N : ");
                if (name.equals("Y") || name.equals("y")) {
                    exit = true;
                }
            }
        }
        System.out.println("Good bay");
    }

    /**
     * Create item.
     */
    private void createItem() {
        System.out.println("----------Add new item-----------");
        String name = this.input.ask("Enter item's name : ");
        String desc = this.input.ask("Enter item's description");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("---------New item with getId : " + item.toString() + " ----------");
    }


    /**
     * Show all items.
     */
    private void showAllItems() {
        System.out.println("------------ Show all items --------------");
        Item[]items = this.tracker.getAll();
        if (items.length > 0) {
            for (Item item : this.tracker.getAll()) {
                System.out.println(item.toString());
            }
        } else  {
            System.out.println("You don't have any. Please, add items.");
        }
    }

    /**
     * Edit items.
     */
    private void editItems() {
        System.out.println("----------Add item's id for edit-----------");
        String id = this.input.ask("Enter item's id : ");
        if (this.tracker.findById(id) != null) {
            String name1 = this.input.ask("Enter item's name : ");
            String desc1 = this.input.ask("Enter item's description : ");
            Item neItem = new Item(name1, desc1);
            tracker.replace(id, neItem);
            System.out.println("---------Item was replace, now new : " + neItem.toString() + " ----------");
        } else {
            System.out.println("You entered wrong id");
        }
    }

    /**
     * Delete items.
     */
    private void deleteItems() {
        String id = this.input.ask("Enter item's id for delete : ");
        if (this.tracker.delete(id)) {
            System.out.println("Item was delete.");
        } else {
            System.out.println("Item not found");
        }
    }

    /**
     * Find item by id.
     */
    private void findItemsById() {
        String id = this.input.ask("Enter item's id which you find : ");
        Item item = this.tracker.findById(id);
        if (item != null) {
            System.out.println("---------Item which you find - " + item.toString()
            + " ----------");
        } else {
            System.out.println("You enter wrong id!");
        }
    }

    /**
     * Find item by name.
     */
    private void findItemsByName() {
        String name = this.input.ask("Enter item's name which you find : ");
        Item[] items = this.tracker.findByName(name);
        System.out.println("Found " + items.length + " item/s");
        for (Item item : items) {
            System.out.println(item.toString());
        }
    }


    /**
     * showMenu
     */
    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item.");
        System.out.println("1. Show all items.");
        System.out.println("2. Edit item.");
        System.out.println("3. Delete item.");
        System.out.println("4. Find item by Id.");
        System.out.println("5. Find items by name.");
        System.out.println("6. Exit Program.");
        System.out.println("Select:");

    }

    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
