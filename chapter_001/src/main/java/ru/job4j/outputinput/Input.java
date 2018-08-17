package ru.job4j.outputinput;

import java.util.Scanner;

public class Input {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the numbers...");
        int n = in.nextInt();
        int d = 0;
        switch (n) {
            case 1:
                d = 10;
                break;
            case 2:
                d = 20;
                break;
            case 3:
                d = 30;
                break;
                default: d = 666;
        }
        System.out.println(d);
    }
}
