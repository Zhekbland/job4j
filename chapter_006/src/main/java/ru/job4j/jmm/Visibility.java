package ru.job4j.jmm;

import java.util.Scanner;

/**
 * Class Visibility.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 17.01.2019.
 */
public class Visibility {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        myThread.shutdown();
    }
}

class MyThread extends Thread {
    private volatile boolean running = true; //use volatile

    public void run() {
        while (running) {
            System.out.println("Running");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Stop");
            }
        }
    }

    public void shutdown() {
        this.running = false;
    }
}
