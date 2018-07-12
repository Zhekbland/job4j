package ru.job4j.loop;

public class Counter {
   public int add(int start, int finish) {
        int temp = 0;
        for (start = 0; start <= 10; start++) {
            if (start % 2 == 0) {
                temp = start + finish;
                finish = temp;
            }
        }
        return finish;
    }
}
