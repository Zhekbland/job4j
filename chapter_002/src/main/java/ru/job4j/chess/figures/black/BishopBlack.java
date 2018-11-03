package ru.job4j.chess.figures.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.Figure;


public class BishopBlack extends Figure {

    public BishopBlack(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isDiagonal(source, dest)) {
            throw new ImpossibleMoveException("Do not move that");
        }
        Cell[] steps = new Cell[Math.abs(dest.x - source.x)];
        int stepX = position().getX();
        int stepY = position().getY();
        int deltaX = (source.x > dest.x) ? -1 : 1;
        int deltaY = (source.y > dest.y) ? -1 : 1;
        for (int index = 0; index < steps.length; index++) {
            stepX = stepX + deltaX;
            stepY = stepY + deltaY;
            steps[index] = findCell(stepX, stepY);
        }
        return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        boolean result = true;
        if (source.x == dest.x || source.y == dest.y) {
            result = false;
        }
        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
