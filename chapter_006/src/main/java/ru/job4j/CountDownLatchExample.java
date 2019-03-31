package ru.job4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// CountDownLatch
public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            executorService.submit(new Processor(i, countDownLatch));
        }

        executorService.shutdown();

        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            countDownLatch.countDown();
        }
    }
}

class Processor implements Runnable {
    private CountDownLatch countDownLatch;
    private int id;

    public Processor(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            this.countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread with id" + id + " proceeded");
    }
}
