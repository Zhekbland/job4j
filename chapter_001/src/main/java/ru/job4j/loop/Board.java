package ru.job4j.loop;

public class Board {
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
