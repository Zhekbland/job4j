package ru.job4j.chess;

import ru.job4j.chess.figures.Cell;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }


    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException,
            OccupiedWayException, FigureNotFoundException {
        boolean rst = false;
        int index = this.findBy(source);
        if ((index == -1)) {
            throw new FigureNotFoundException("Figure not found");
        }
        Cell[] steps = this.figures[index].way(source, dest);
        for (Cell cell : steps) {
            if (this.findBy(cell) != -1) {
                throw new OccupiedWayException("The cell is busy!");
            }
        }
        this.figures[index] = this.figures[index].copy(dest);
        rst = true;
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}