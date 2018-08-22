package ru.job4j.dotcom;

/**
 * Class ArrayChar for verify right write of letters.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class SimpleDotComTest {

    /**
     * Test DotCom.
     * @param args
     */
    public static void main(String[] args) {
        SimpleDotCom dot = new SimpleDotCom();
        int[] locations = new int[] {2, 3, 4};
        dot.setLocationCells(locations);
        String userGuess = "5";
        String result = dot.checkYourself(userGuess);
    }
}
