package ru.job4j.chess.figures.white;

import ru.job4j.chess.Figure;
import ru.job4j.chess.figures.Cell;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class RookWhite extends Figure {

    public RookWhite(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        return new Cell[]{dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookWhite(dest);
    }
}
