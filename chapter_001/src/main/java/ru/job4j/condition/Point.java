package ru.job4j.condition;

/**
 * Class Point finds points of x and y.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version 4
 * @since 08.04.2019
 */
public class Point {

    /**
     * Contains x coordinate.
     */
    private int x;

    /**
     * Contains y coordinate.
     */
    private int y;

    /**
     * Contains z coordinate.
     */
    private int z;

    /**
     * Constructor takes variables.
     *
     * @param first  - x.
     * @param second - y.
     */
    public Point(int first, int second) {
        this.x = first;
        this.y = second;
    }

    /**
     * Constructor takes variables.
     *
     * @param first  - x.
     * @param second - y.
     * @param third  - z.
     */
    public Point(int first, int second, int third) {
        this.x = first;
        this.y = second;
        this.z = third;
    }

    /**
     * The distance between points in the coordinate system.
     *
     * @param that - another Point with coordinates x, y.
     * @return distance.
     */
    public double distance(Point that) {
        return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
    }

    /**
     * The distance between points in the coordinate system.
     *
     * @param that - another Point with coordinates x, y, z.
     * @return distance.
     */
    public double distanceThree(Point that) {
        return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2)
                + Math.pow(this.z - that.z, 2));
    }

    /**
     * Print.
     */
    public void info() {
        System.out.println(String.format("Point[%s, %s]", this.x, this.y));
    }

    /**
     * Print.
     */
    public void infoThreeDimensional() {
        System.out.println(String.format("Point[%s, %s, %s]", this.x, this.y, this.z));
    }
}