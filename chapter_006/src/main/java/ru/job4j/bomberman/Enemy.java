package ru.job4j.bomberman;

/**
 * Class Enemy creates Runnable for thread.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 21.02.2019.
 */
public class Enemy implements Runnable {
    private final Board board = new Board();
    private Cell sourceEnemy = new Cell(board.getLength());
    private Cell distanceEnemy = new Cell(board.getLength());

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Enemy wanted catch "
                    + sourceEnemy);
            board.move(sourceEnemy, distanceEnemy);
            sourceEnemy = distanceEnemy;
            distanceEnemy = new Cell(board.getLength());
            System.out.println("Emeny took "
                    + sourceEnemy + " will take " + distanceEnemy);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
