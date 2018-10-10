package ru.job4j.tracker;

public abstract class BaseAction implements UserAction {
    private final int key;
    private final String event;

    protected BaseAction(final int key, final String event) {
        this.key = key;
        this.event = event;
    }

    @Override
    public int key() {
        return key;
    }

    @Override
    public String info() {
        return String.format("%d. %s.", this.key(), event);
    }


}
