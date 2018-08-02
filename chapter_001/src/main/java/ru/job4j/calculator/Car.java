package ru.job4j.calculator;

/**
 * Class Car checks how many km may I drive.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */
public class Car {
    private double volume;

    /**
     * How many km may I drive.
     * @param kilometer amount of km.
     */
    public void drive(int kilometer) {
        this.volume = this.volume - kilometer;
    }

    /**
     * Fill the tank.
     * @param gas amount of gas.
     */
    public void fill(int gas) {
        this.volume = this.volume + 10 * gas;
    }

    /**
     * Is tank empty.
     * @return can I drive.
     */
    public boolean canDrive() {
        boolean result = this.volume > 0;
        return result;
    }

    /**
     * Print screen how many km may i drive.
     */
    public void gasInfo() {
        System.out.println("I can drive " + this.volume + " kilometers.");
    }
}