package ru.job4j.jmm;

/**
 * Class RaceCondition.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 17.01.2019.
 */
public class RaceCondition {
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        RaceCondition test = new RaceCondition();
        test.doWork();
    }

    public synchronized void increment() { //use synchronized
        counter++;
    }

    public void doWork() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(this.counter);
    }
}
