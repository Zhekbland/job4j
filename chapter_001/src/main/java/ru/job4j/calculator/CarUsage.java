package ru.job4j.calculator;

/**
 * Class CarUsage checks can i drive and how many km may drive.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class CarUsage {

    /**
     * To screen can i drive and how many km may drive.
     * @param args .
     */
    public static void main(String[] args) {
        Car audi = new Car();
        boolean driving = audi.canDrive();
        System.out.println("Can you drive? :" + driving);
        System.out.println("I'm going to a gas station.");
        int gas = 25;
        audi.fill(gas);
        driving = audi.canDrive();
        System.out.println("Can you drive now? :" + driving);
        System.out.println("Now. I'm going to my granny.");
        System.out.println("It's 10 kilometers from here.");
        int distance = 10;
        audi.drive(distance);
        audi.gasInfo();
    }
}