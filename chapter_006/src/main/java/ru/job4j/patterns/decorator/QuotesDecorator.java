package ru.job4j.patterns.decorator;

class QuotesDecorator extends Decorators {

    public QuotesDecorator(PrintInterface component) {
        super(component);
    }


    @Override
    public String print() {
        return "\"" + component.print() + "\"";
    }
}
