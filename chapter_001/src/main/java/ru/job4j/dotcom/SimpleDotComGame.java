package ru.job4j.dotcom;

/**
 * Class ArrayChar for verify right write of letters.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class SimpleDotComGame {

    /**
     * Create game DotComGame.
     * @param args - arg.
     */
    public static void main(String[] args) {
        int numOfGuesses = 0;
        GameHelper helper = new GameHelper();
        SimpleDotCom theDotCom = new SimpleDotCom();
        int randomNum = (int) (Math.random() * 5);
        int[] location = new int[] {randomNum, randomNum + 1, randomNum + 2};
        theDotCom.setLocationCells(location);
        boolean isAlive = true;
        while (isAlive) {
            String guess = helper.getUserInput("Enter the number");
            String result = theDotCom.checkYourself(guess);
            numOfGuesses++;
            if (result.equals("Dead")) {
                isAlive = false;
                System.out.println("Number of guesses: " + numOfGuesses);
            }
        }

    }
}
