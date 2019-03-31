package ru.job4j.bomberman;

import org.junit.Test;

/**
 * Class BoardTest is testing class Board.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 3.
 * @since 21.02.2019.
 */
public class BoardTest {
    private final Board board = new Board();

    @Test
    public void whenWeHave2Threads() throws InterruptedException {
        Thread enemy1 = new Thread(new Enemy());
        Thread enemy2 = new Thread(new Enemy());
        Thread player = new Thread(new Player());
        enemy1.setDaemon(true);
        enemy2.setDaemon(true);
        enemy1.start();
        enemy2.start();
        player.start();
//        player.join();
    }
}