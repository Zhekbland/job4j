package ru.job4j.chess.figures.white;

import ru.job4j.chess.Figure;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PawnWhite extends Figure {
    public PawnWhite(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] steps = new Cell[0];
        if (!(source.y == dest.y + 1 && source.x == dest.x)) {
            throw new ImpossibleMoveException("Do not move that!");
        }
        steps = new Cell[]{dest};
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnWhite(dest);
    }
}
