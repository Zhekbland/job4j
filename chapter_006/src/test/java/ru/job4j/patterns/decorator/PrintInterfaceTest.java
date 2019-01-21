package ru.job4j.patterns.decorator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PrintInterfaceTest {
    @Test
    public void whenWeCreatePatternDecorator() {
        PrintInterface printer = new BracketsDecorator(new QuotesDecorator(new PrintDecorator("Hello")));
        String result = printer.print();
        String expected = "[" + "\"" + "Hello" + "\"" + "]";
        assertThat(result, is(expected));
    }
}