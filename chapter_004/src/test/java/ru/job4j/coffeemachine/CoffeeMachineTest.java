package ru.job4j.coffeemachine;

import org.junit.Test;
import ru.job4j.coffemachine.CoffeeMachine;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class CoffeeMachineTest is testing CoffeeMachine's method.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 21.11.2018.
 */
public class CoffeeMachineTest {
    @Test
    public void ourChangeIs15() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] result = coffeeMachine.change(50, 35);
        int[] expect = {10, 5};
        assertThat(result, is(expect));
    }

    @Test
    public void ourChangeIs65() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] result = coffeeMachine.change(100, 35);
        int[] expect = {10, 10, 10, 10, 10, 10, 5};
        assertThat(result, is(expect));
    }

    @Test
    public void ourChangeIs18() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] result = coffeeMachine.change(53, 35);
        int[] expect = {10, 5, 2, 1};
        assertThat(result, is(expect));
    }

    @Test
    public void ourChangeIs16() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] result = coffeeMachine.change(51, 35);
        int[] expect = {10, 5, 1};
        assertThat(result, is(expect));
    }
}
