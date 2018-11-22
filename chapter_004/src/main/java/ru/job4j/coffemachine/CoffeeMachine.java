package ru.job4j.coffemachine;

import java.util.ArrayList;
import java.util.List;

/**
 * Class CoffeeMachine is counting change and giving the least amount of coins.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 21.11.2018.
 */
public class CoffeeMachine {
    public int[] change(int value, int price) {
        int[] coins = {10, 5, 2, 1};
        int change = value - price;
        List<Integer> tempResult = new ArrayList<>();
        for (int index = 0; index < coins.length && change != 0; index++) {
            while (change >= coins[index]) {
                change = change - coins[index];
                tempResult.add(coins[index]);
            }
        }
        int[] result = new int[tempResult.size()];
        for (int index = 0; index < result.length; index++) {
            result[index] = tempResult.get(index);
        }
        return result;
    }
}
