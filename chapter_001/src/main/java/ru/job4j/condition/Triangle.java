package ru.job4j.condition;

/**
 * Class Triangle calculate parameters of the triangle.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version 2
 * @since 08.04.2019
 */
public class Triangle {

    /**
     * Calculating the semi-perimeter along the lengths of the sides.
     * @param a is distance between points a b.
     * @param b is distance between points a c.
     * @param c is distance between points b c.
     * @return Perimeter.
     */
    public double period(double a, double b, double c) {
        return (a + b + c) / 2;
    }

    /**
     * Calculate the area of the triangle.
     *
     * @return area of triangle if exist else -1
     */
    public double area(int x1, int y1, int x2, int y2, int x3, int y3) {
        double rsl = -1;
        double a = new Point().distance(x1, y1, x2, y2);
        double b = new Point().distance(x2, y2, x3, y3);
        double c = new Point().distance(x1, y1, x3, y3);
        double p = period(a, b, c);
        if (this.exist(a, b, c)) {
            rsl = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        }
        return rsl;
    }

    /**
     * Verify can build triangle with lengths of sides
     * @param ab length from point a b.
     * @param ac length from point a c.
     * @param bc length from point b c.
     * @return exist or not exist triangle.
     */
    private boolean exist(double ab, double ac, double bc) {
        return ((ab + ac) > bc) && ((ab + bc) > ac) && ((ac + bc) > ab);
    }
}
