package ru.job4j.calculator;

/**
 * Class Fit calculates perfect weight for men and women.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class Fit {
    /**
     * Calculate perfect weight for men.
     * @param height height.
     * @return perfect weight.
     */
    public double manWeight(double height) {
        return (height - 100) * 1.15;
    }
    /**
     * Perfect weight for women.
     * @param height height.
     * @return perfect weight.
     */
    public double womanWeight(double height) {
        return (height - 110) * 1.15;
    }
}
