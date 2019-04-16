package ru.job4j.models;

import java.util.Objects;

/**
 *
 */
public class Item {
    public String name;
    public String description;
    public long create;
    private String id;

    public Item() {
    }

    public Item(String name, String description, long create) {
        this.name = name;
        this.description = description;
        this.create = create;
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String toString() {
        return "Item's name: " + name + "   item's description: " + description
                + "    item's id: " + id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public long getCreate() {
        return this.create;
    }

    public  String getId() {
        return  this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return create == item.create
                && Objects.equals(name, item.name)
                && Objects.equals(description, item.description)
                && Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, create, id);
    }
}
