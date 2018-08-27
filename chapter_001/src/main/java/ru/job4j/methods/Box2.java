package ru.job4j.methods;

public class Box2 {
    private double width;
    private double height;
    private double depth;

    double volume() {
        return width * height * depth;
    }

    void setDim(double w, double h, double d) {
        width = w;
        height = h;
        depth = d;
    }
}
