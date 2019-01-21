package ru.job4j.patterns.decorator;

abstract class Decorators implements PrintInterface {
    PrintInterface component;

    public Decorators(PrintInterface component) {
        this.component = component;
    }
}
