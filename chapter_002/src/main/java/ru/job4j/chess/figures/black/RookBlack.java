package ru.job4j.chess.figures.black;

import ru.job4j.chess.Figure;
import ru.job4j.chess.figures.Cell;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class RookBlack extends Figure {
    public RookBlack(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        return new Cell[]{dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookBlack(dest);
    }
}
