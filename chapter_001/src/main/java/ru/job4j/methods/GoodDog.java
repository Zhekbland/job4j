package ru.job4j.methods;

public class GoodDog {
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int s) {
        if (s > 80) {
            s = 80;
        }
        this.size = s;
    }

    void bark() {
        if (size > 60) {
            System.out.println("Гав Гав");
        } else if (size > 14) {
            System.out.println("Вуф Вуф");
        } else {
            System.out.println("Тяф Тяф");
        }
    }
}
