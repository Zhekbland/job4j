package ru.job4j.patterns.decorator;

class BracketsDecorator extends Decorators {

    public BracketsDecorator(PrintInterface component) {
        super(component);
    }

    @Override
    public String print() {
        return "[" + component.print() + "]";
    }
}
