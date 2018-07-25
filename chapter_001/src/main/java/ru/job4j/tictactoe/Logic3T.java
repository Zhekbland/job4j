package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    private boolean checkWinnerHorizontalX() {
        boolean result = true;
        for (int row = 0; row < table.length; row++) {
            result = true;
            for (int col = 0; col < table.length - 1; col++) {
                if (!(table[row][col].hasMarkX() && table[row][col + 1].hasMarkX())) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private boolean checkWinnerVerticalX() {
        boolean result = true;
        for (int col = 0; col < table.length; col++) {
            result = true;
            for (int row = 0; row < table.length - 1; row++) {
                if (!(table[row][col].hasMarkX() && table[row + 1][col].hasMarkX())) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private boolean checkWinnerDiagonalX() {
        boolean result = true;
        for (int row = 0; row < table.length - 1; row++) {
            result = true;
            for (int col = 0; col < table.length - 1; col++) {
                if (!(table[0][0].hasMarkX() && table[col + 1][col + 1].hasMarkX())) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private boolean checkWinnerDiagonalXdifferent() {
        boolean result = true;
        for (int row = 0; row < table.length - 1; row++) {
            result = true;
            for (int col = 0; col < table.length - 1; col++) {
                if (!(table[table.length - 1][0].hasMarkX() && table[table.length - 1 - col - 1][col + 1].hasMarkX())) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private boolean checkWinnerHorizontalO() {
        boolean result = true;
        for (int row = 0; row < table.length; row++) {
            result = true;
            for (int col = 0; col < table.length - 1; col++) {
                if (!(table[row][col].hasMarkO() && table[row][col + 1].hasMarkO())) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private boolean checkWinnerVerticalO() {
        boolean result = true;
        for (int col = 0; col < table.length; col++) {
            result = true;
            for (int row = 0; row < table.length - 1; row++) {
                if (!(table[row][col].hasMarkO() && table[row + 1][col].hasMarkO())) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private boolean checkWinnerDiagonalO() {
        boolean result = true;
        for (int row = 0; row < table.length - 1; row++) {
            result = true;
            for (int col = 0; col < table.length - 1; col++) {
                if (!(table[0][0].hasMarkO() && table[col + 1][col + 1].hasMarkO())) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private boolean checkWinnerDiagonalOdifferent() {
        boolean result = true;
        for (int row = 0; row < table.length - 1; row++) {
            result = true;
            for (int col = 0; col < table.length - 1; col++) {
                if (!(table[table.length - 1][0].hasMarkO() && table[table.length - 1 - col - 1][col + 1].hasMarkO())) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    public boolean isWinnerX() {
        return checkWinnerVerticalX() || checkWinnerHorizontalX()
                || checkWinnerDiagonalX() || checkWinnerDiagonalXdifferent();
    }

    public boolean isWinnerO() {
        return checkWinnerVerticalO() || checkWinnerHorizontalO()
                || checkWinnerDiagonalO() || checkWinnerDiagonalOdifferent();
    }

    public boolean hasGap() {
        boolean result = true;
        int countX = 0;
        int countO = 0;
        for (int col = 0; col < table.length; col++) {
            for (int row = 0; row < table.length; row++) {
                if (table[col][row].hasMarkX()) {
                    countX = countX + 1;
                } else if (table[col][row].hasMarkO()) {
                    countO = countO + 1;
                }
                if ((countO == 4 && countX == 5) || (countO == 5 && countX == 4)) {
                    result = false;
                }
            }
        }
        return result;
    }
}
