package ru.job4j.difficultdotcom;

/**
 * Class ArrayChar for verify right write of letters.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  1
 */
import java.util.*;

public class DotCom {
    private ArrayList<String> locationCells;
    private String name;

    public void setLocationCells(ArrayList<String> loc) {
        locationCells = loc;
    }

    public void setName(String n) {
        name = n;
    }

    public String checkYourself(String userInput) {
        String result = "Miss";
        int index = locationCells.indexOf(userInput);
        if (index >= 0) {
            locationCells.remove(index);
            if (locationCells.isEmpty()) {
                result = "Dead";
                System.out.println("Ouh! You killed" + name + "   : ( ");
            } else {
                result = "hit";
            }
        }
        return result;
    }
}
