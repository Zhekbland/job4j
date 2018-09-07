package ru.job4j.pseudo;
/**
 * Class Square.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class Square implements Shape {

    /**
     * Print square.
     * @return ready square.
     */
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("########").append(System.lineSeparator());
        pic.append("#      #").append(System.lineSeparator());
        pic.append("#      #").append(System.lineSeparator());
        pic.append("#      #").append(System.lineSeparator());
        pic.append("########").append(System.lineSeparator());
        return pic.toString();
    }
}
