package ru.job4j.condition;

/**
 * Class Point finds points of x and y.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version 2
 * @since 08.04.2019
 */
public class Point {

    /**
     * The distance between points in the coordinate system.
     *
     * @param x1 - x1.
     * @param y1 - y1.
     * @param x2 - x2.
     * @param y2 - y2.
     * @return distance.
     */
    public double distance(int x1, int y1, int x2, int y2) {
//        double first = Math.pow(x2 - x1, 2);
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

//    /**
//     * Sum of first and second.
//     * @param that -parameter for calculate.
//     * @return distance to the point.
//     */
//    public double distanceTo(Point that) {
//        return  Math.sqrt(
//                Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2)
//        );
//    }
//
//    /**
//     * Displays on the screen distance between points A and B.
//     * @param args .
//     */
//    public static void main(String[] args) {
//        Point a = new Point(0, 1);
//        Point b = new Point(2, 5);
//        System.out.println("x1 = " + a.x);
//        System.out.println("y1 = " + a.y);
//        System.out.println("x2 = " + b.x);
//        System.out.println("y2 = " + b.y);
//        double result = a.distanceTo(b);
//        System.out.println("Расстояние между точками А и В : " + result);
//    }
}