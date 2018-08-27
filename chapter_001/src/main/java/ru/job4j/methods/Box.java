package ru.job4j.methods;

public class Box {
    double width;
    double height;
    double depth;

    void volume() {
        System.out.println("Volume equal ");
        System.out.println(width * height * depth);
    }
}
