package ru.job4j.condition;

/**
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version &Id&
 * @since  0.1
 */
public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    public  Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Calculating the semi-perimeter along the lengths of the sides.
     * @param ab is distance between points a b.
     * @param ac is distance between points a c.
     * @param bc is distance between points b c.
     * @return Perimeter.
     */
    public double period(double ab, double ac, double bc){
        return (ab + ac + bc) / 2;
    }

    /**
     * Calculate the area of the triangle.
     *
     * @return area of triangle if exist else -1
     */
    public double area(){
        double rsl = -1;
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }

    /**
     * Verify can build triangle with lengths of sides
     * @param ab length from point a b.
     * @param ac length from point a c.
     * @param bc length from point b c.
     * @return
     */
    private boolean exist(double ab, double ac, double bc){
        return (ab + bc) > ac ? true : false;
    }
}
