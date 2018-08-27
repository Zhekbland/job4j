package ru.job4j.methods;

public class BoxDemo4 {

    public static void main(String[] args) {
        Box1 mybox1 = new Box1();
        Box1 mybox2 = new Box1();
        mybox1.width = 10;
        mybox1.height = 20;
        mybox1.depth = 15;
        mybox2.width = 3;
        mybox2.height = 6;
        mybox2.depth = 9;
        Square b = new Square();
        System.out.println("Volume equal " + mybox1.volume());
        System.out.println("Volume equal " + mybox2.volume());
        System.out.println("Square of number " + b.square(3));
    }
}
