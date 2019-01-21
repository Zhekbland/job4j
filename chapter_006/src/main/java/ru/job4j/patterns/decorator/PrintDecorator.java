package ru.job4j.patterns.decorator;

public class PrintDecorator implements PrintInterface {
    private String value;

    public PrintDecorator(String value) {
        this.value = value;
    }

    @Override
    public String print() {
        return value;
    }
}

