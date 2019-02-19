package ru.job4j.bomberman;

import java.util.Random;

/**
 * Class Cell creates cells.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 19.02.2019.
 */
public class Cell {
    private final int row;
    private final int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Cell(int length) {
        Random random = new Random();
        this.row = random.nextInt(length);
        this.column = random.nextInt(length);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Cell{" + "row=" + row + ", column=" + column + '}';
    }
}
