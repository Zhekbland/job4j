package ru.job4j.outputinput;

import java.util.Random;

public class ProbeOut {

    public static void main(String[] args) {
        Random rd = new Random();
        double x = rd.nextInt(100);
        int b = rd.nextInt(100);
        int c = rd.nextInt(100);
        System.out.println("\n" + b + "\n" + c);
        int y = 1;
        int d = 3;
        y = d < y ? y : d;
        System.out.print(y + "\n");
        System.out.print(y);
    }
}
