package ru.job4j.outputinput;
/**
 * Class ArrayChar for verify right write of letters.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */

public class NumberPi {

    /**
     * Number PI
     * @param args - arg.
     */
    public static void main(String[] args) {
        double p = Math.PI;
        double e = Math.E;
        System.out.printf("Число Пи с точнотью до сотых" + " " + "%1.2f", p);
        System.out.println();
        System.out.printf("Число E с точнотью до десятых" + " " + "%1.1f", e);
    }
}
