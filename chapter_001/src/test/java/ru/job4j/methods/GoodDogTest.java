package ru.job4j.methods;

/**
 * Class ArrayChar for verify right write of letters.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class GoodDogTest {

    /**
     * Create dogs with parameters, and they bark with different feathers.
     * @param args
     */
    public static void main(String[] args) {
        GoodDog one = new GoodDog();
        one.setSize(70);
        GoodDog two = new GoodDog();
        two.setSize(8);
        System.out.println("Первая собака: " + one.getSize());
        System.out.println("Вторая собака: " + two.getSize());
        one.bark();
        two.bark();
    }
}
