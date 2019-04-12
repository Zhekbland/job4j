package ru.job4j.condition;

/**
 * Class Triangle calculate parameters of the triangle.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version 3
 * @since 08.04.2019
 */
public class Triangle {

    /**
     * Coordinates x1, y1.
     */
    private Point first;

    /**
     * Coordinates x2, y2.
     */
    private Point second;

    /**
     * Coordinates x3, y3.
     */
    private Point third;


    /**
     * Constructor takes variables.
     *
     * @param ap - coordinates x1, y1.
     * @param bp - coordinates x2, y2.
     * @param cp - coordinates x3, y3.
     */
    public Triangle(Point ap, Point bp, Point cp) {
        this.first = ap;
        this.second = bp;
        this.third = cp;
    }
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
    public double area() {
        double rsl = -1;
        double a = first.distance(second);
        double b = second.distance(third);
        double c = first.distance(third);
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
