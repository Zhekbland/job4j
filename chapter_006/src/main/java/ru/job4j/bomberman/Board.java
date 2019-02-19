package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Class Board creates board of ReentrantLock.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 19.02.2019.
 */
public class Board {
    private final ReentrantLock[][] board = new ReentrantLock[5][5];

    public Board() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }

    public boolean move(Cell source, Cell dist) {
        boolean result = false;
        board[source.getRow()][source.getColumn()].lock();
        if (board[dist.getRow()][dist.getColumn()].tryLock()) {
            board[source.getRow()][source.getColumn()].unlock();
            result = true;
        } else {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (board[dist.getRow()][dist.getColumn()].tryLock()) {
                board[source.getRow()][source.getColumn()].unlock();
                result = true;
            }
        }
        return result;
    }

    public int getLength() {
        return this.board.length;
    }
}
