package ru.job4j.array;

/**
 * Test.
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru)
 * @version $Id$
 * @since  0.1
 */

import java.util.Scanner;

public class ArrayStr {

    /**
     * The array takes words and prints them.
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("How many words will array have?");
        int size = in.nextInt();
        String[] arr = new String[size];

        for (int i = 0; i < size; i++) {
            System.out.println("Enter word № " + (i + 1));
            arr[i] = in.next();
        }
        System.out.println("The array contains these words");
        for (String i:arr) {
            System.out.print(i + "\t");
        }
    }
}
