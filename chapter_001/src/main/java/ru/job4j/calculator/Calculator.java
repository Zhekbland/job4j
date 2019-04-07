package ru.job4j.calculator;

/**
 * Class Calculator count sum, subtraction division multiplication of numbers.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class Calculator {
    private double result;

    /**
     * Sum of first and second.
     * @param first number.
     * @param second number.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Subtraction of first and second.
     * @param first number.
     * @param second number.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Division of first and second.
     * @param first number.
     * @param second number.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Multiplication of first and second.
     * @param first number.
     * @param second number.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    public double getResult() {
        return result;
    }
}
