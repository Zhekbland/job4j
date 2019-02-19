package ru.job4j.bomberman;

import org.junit.Test;

/**
 * Class BoardTest is testing class Board.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 19.02.2019.
 */
public class BoardTest {
    private Board board = new Board();
    private Cell sourcePlayer = new Cell(1, 1);
    private Cell distancePlayer = new Cell(board.getLength());
    private Cell sourceEnemy = new Cell(1, 2);
    private Cell distanceEnemy = new Cell(board.getLength());

    @Test
    public void whenWeDoNotMove() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                board.move(sourcePlayer, distancePlayer);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean result = board.move(sourceEnemy, distancePlayer);
                System.out.println(result);
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    @Test
    public void whenWeHave2Threads() throws InterruptedException {

        Thread enemy = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    System.out.println("sourceEnemy take place now - next place "
                            + sourceEnemy + " " + distanceEnemy);
                    while (!board.move(sourceEnemy, distanceEnemy)) {
                        distanceEnemy = new Cell(board.getLength());
                    }
                    sourceEnemy = distanceEnemy;
                    distanceEnemy = new Cell(board.getLength());
                    System.out.println("sourceEnemy will take place - next place "
                            + sourceEnemy.toString() + " " + distanceEnemy);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread player = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    System.out.println("sourcePlayer take place - next place "
                            + sourcePlayer.toString() + " " + distancePlayer);
                    while (!board.move(sourcePlayer, distancePlayer)) {
                        distancePlayer = new Cell(board.getLength());
                    }
                    sourcePlayer = distancePlayer;
                    distancePlayer = new Cell(board.getLength());
                    System.out.println("sourcePlayer will take place - next place "
                            + sourcePlayer.toString() + " " + distancePlayer);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        enemy.start();
        player.start();
        enemy.join();
        player.join();
    }
}