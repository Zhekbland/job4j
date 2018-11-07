package ru.job4j.lambda;

public class MathUtil {
    public static double add(int left, int second) {
        return left + second;
    }

    public static double div(int left, int second) {
        return left / second;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.multiple(
                0, 10, 2,
                MathUtil::add,
                result -> System.out.println(result)
        );
    }
}
