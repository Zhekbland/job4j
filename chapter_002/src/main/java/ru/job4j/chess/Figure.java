package ru.job4j.chess;

import ru.job4j.chess.figures.Cell;

public abstract class Figure {
    final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    public String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );
    }

    public Cell position() {
        return this.position;
    }

    public abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    public abstract Figure copy(Cell dest);

    public Cell findCell(int x, int y) {
        Cell result = Cell.A1;
        for (Cell cell : Cell.values()) {
            if (cell.x == x && cell.y == y) {
                result = cell;
                break;
            }
        }
        return result;
    }
}
