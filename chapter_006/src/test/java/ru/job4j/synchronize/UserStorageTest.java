package ru.job4j.synchronize;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class UserStorageTest is testing class UserStorage.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 20.01.2019.
 */
public class UserStorageTest {
    private UserStorage userStorage = new UserStorage();
    private User user1 = new User(1, 20000);
    private User user2 = new User(2, 12000);
    private Random random = new Random();

    @Before
    public void setUp() {
        userStorage.add(user1);
        userStorage.add(user2);
    }

    @Test
    public void whenWeTransferALotAndGet32000Always() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    userStorage.transfer(1, 2, random.nextInt(1000));
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    userStorage.transfer(2, 1, random.nextInt(1000));
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        Integer result = userStorage.getAmount(1) + userStorage.getAmount(2);
        assertThat(result, is(32000));
    }

    @Test
    public void whenWeCannotDeleteAndGetFalse() {
        boolean result = userStorage.delete(new User(3, 5000));
        assertThat(result, is(false));
    }

    @Test
    public void whenWeDoNotTransferAndGetFalse() {
        boolean result = userStorage.transfer(1, 5, 60000);
        assertThat(result, is(false));
    }
}