package ru.job4j.bomberman;

/**
 * Class Player creates Runnable for thread.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 20.02.2019.
 */
public class Player implements Runnable {
    private final Board board = new Board();
    private Cell sourcePlayer = new Cell(0, 0);
    private Cell distancePlayer = new Cell(board.getLength());

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (board.getReentrantLock(sourcePlayer).hasQueuedThreads()) {
                System.out.println("caught");
                Thread.currentThread().interrupt();
                break;
            }
            System.out.println("Player wanted take "
                    + sourcePlayer.toString());
            board.move(sourcePlayer, distancePlayer);
            sourcePlayer = distancePlayer;
            distancePlayer = new Cell(board.getLength());
            System.out.println("Player took place "
                    + sourcePlayer.toString() + " will  take " + distancePlayer);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println("stop Player");
                Thread.currentThread().interrupt();
            }
        }
    }
}
