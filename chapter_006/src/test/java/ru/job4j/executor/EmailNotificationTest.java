package ru.job4j.executor;

import org.junit.Test;

/**
 * Class EmailNotificationTest is testing class EmailNotification.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 29.01.2019.
 */
public class EmailNotificationTest {
    private EmailNotification notification = new EmailNotification();

    @Test
    public void whenWeSendEmailAndUseThreadPool() {
        User user = new User("Pavel", "pavel@yandex.ru");
        for (int i = 0; i < 10; i++) {
            notification.emailTo(user);
        }
        notification.close();
    }
}