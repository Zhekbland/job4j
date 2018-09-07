package ru.job4j.pseudo;
/**
 * Class Paint.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class Paint {

    /**
     * Print Shapes.
     * @param shape triangle, square.
     */
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }
}
