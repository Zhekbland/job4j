package ru.job4j.difficultdotcom;

/**
 * Class ArrayChar for verify right write of letters.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  1
 */
import java.util.ArrayList;

public class DotComBust {

    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;

    private void setUpGame() {
        DotCom one = new DotCom();
        one.setName("Pets.com");
        DotCom two = new DotCom();
        two.setName("eToys.com");
        DotCom three = new DotCom();
        three.setName("Go2.com");
        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(three);
        System.out.println("Your goal is dead three 'sites'.");
        System.out.println("Pets.com, eToys.com, Go2.com");
        System.out.println("Try to dead these sites in the minimum number of steps");
        for (DotCom dotComToSet : dotComsList) {
            ArrayList<String> newLocation = helper.placeDotCom(3);
            dotComToSet.setLocationCells(newLocation);
        }
    }

    private void startPlaying() {
        while (!dotComsList.isEmpty()) {
            String userGuess = helper.getUserInput("Make a move");
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "Miss";
        for (DotCom dotComToTest : dotComsList) {
            result = dotComToTest.checkYourself(userGuess);
            if (result.equals("Hit")) {
                break;
            }
            if (result.equals("Dead")) {
                dotComsList.remove(dotComToTest);
                break;
            }
        }
        System.out.println(result);
    }

    private void finishGame() {
        System.out.println("All sites were dead! Your stocks are now worthless.");
        if (numOfGuesses <= 18) {
            System.out.println("It took you just" + numOfGuesses + "attempts");
            System.out.println("You had time before your investments drowned.");
        } else {
            System.out.println("It took you quite a lot of time" + numOfGuesses + "attempts");
            System.out.println("Fishs are dancing around your investments");
        }
    }

    public static void main(String[] args) {
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }
}
