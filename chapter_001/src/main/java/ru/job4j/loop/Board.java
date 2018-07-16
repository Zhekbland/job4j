package ru.job4j.loop;

/**
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version &Id&
 * @since  0.1
 */
public class Board {

    /**
     * Create a chess board
     * @param height of the pyramid.
     * @param width of the pyramid.
     * @return Screen.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int indexH = 0; indexH < height; indexH++) {
            for (int indexW = 0; indexW < width; indexW++) {
                if ((indexH + indexW) % 2 == 0) {
                    screen.append("x");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return  screen.toString();
    }
}
