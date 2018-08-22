package ru.job4j.dotcom;
/**
 * Class ArrayChar for verify right write of letters.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class SimpleDotCom {
    int[] locationCells;
    int numOfHits = 0;

    /**
     * Create field for game and verify parameters.
     * @param stringGuess
     * @return result of game.
     */
    public String checkYourself(String stringGuess) {
        int guess = Integer.parseInt(stringGuess);
        String result = "Miss";
        for (int cell : locationCells) {
            if (guess == cell) {
                result = "Hit";
                numOfHits++;
                break;
            }
        }
        if (numOfHits == locationCells.length) {
            result = "Dead";
        }
        System.out.println(result);
        return result;
    }

    public void setLocationCells(int[] loc) {
        locationCells = loc;
    }
}
