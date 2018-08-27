package ru.job4j.methods;

public class BoxDemo5 {

    public static void main(String[] args) {
        Box2 mybox1 = new Box2();
        Box2 mybox2 = new Box2();
        mybox1.setDim(10, 20, 15);
        mybox2.setDim(3, 6, 9);
        System.out.println("Volume equal " + mybox1.volume());
        System.out.println("Volume equal " + mybox2.volume());
    }
}
