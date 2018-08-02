package ru.job4j.converter;

/**
 * Class Converter converts currencies.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class Converter {

    /**
     * Convert ruble to Euro.
     * @param value ruble.
     * @return Euro.
     */
    public int rubleToEuro(int value) {
        return value / 70;
    }

    /**
     * Convert ruble to Dollar.
     * @param value Dollar.
     * @return Dollar.
     */
    public int rubleToDollar(int value) {
        return value / 60;
    }

    /**
     * Convert Euro to Ruble.
     * @param value Euro.
     * @return Euro.
     */
    public int euroToRuble(int value) {
        return value * 70;
    }

    /**
     * Convert dollar to ruble.
     * @param value dollar.
     * @return Dollar.
     */
    public int dollarToRuble(int value) {
        return value * 60;
    }
}