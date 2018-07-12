package ru.job4j.loop;

public class Counter {
   public int add(int start, int finish) {
        int sum = 0;
        for (start = start; start <= finish; start++) {
            if (start % 2 == 0) {
                sum = sum + start;
            }
        }
        return sum;
    }
}
