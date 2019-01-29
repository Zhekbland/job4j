package ru.job4j.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class EmailNotification send notifications.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 29.01.2019.
 */
public class EmailNotification {
    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        this.pool.submit(new Runnable() {
            @Override
            public void run() {
                String subject = "Notification " + user.getUsername()
                        + " to email " + user.getEmail();
                String body = "Add new event to " + user.getUsername();
                send(subject, body, user.getEmail());
            }
        });
    }

    public void close() {
        pool.shutdown();
        while (!this.pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {
    }
}
