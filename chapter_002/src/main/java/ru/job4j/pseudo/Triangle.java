package ru.job4j.pseudo;
/**
 * Class Triangle.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class Triangle implements Shape {

    /**
     * Print triangle.
     * @return ready triangle.
     */
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("    #    ").append(System.lineSeparator());
        pic.append("   # #   ").append(System.lineSeparator());
        pic.append("  #   #  ").append(System.lineSeparator());
        pic.append(" #     # ").append(System.lineSeparator());
        pic.append("#########").append(System.lineSeparator());
        return pic.toString();
    }
}
