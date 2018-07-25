package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean checkWinnerHorizontalX() {
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

    public boolean checkWinnerVerticalX() {
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

    public boolean checkWinnerDiagonalX() {
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

    public boolean checkWinnerDiagonalXreturn() {
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

    public boolean checkWinnerHorizontalO() {
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

    public boolean checkWinnerVerticalO() {
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

    public boolean checkWinnerDiagonalO() {
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

    public boolean checkWinnerDiagonalOreturn() {
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
                || checkWinnerDiagonalX() || checkWinnerDiagonalXreturn();
    }

    public boolean isWinnerO() {
        return checkWinnerVerticalO() || checkWinnerHorizontalO()
                || checkWinnerDiagonalO() || checkWinnerDiagonalOreturn();
    }

    public boolean hasGap() {
        boolean result = true;
        for (int col = 0; col < table.length; col++) {
            result = true;
            for (int row = 0; row < table.length - 1; row++) {
                if (checkWinnerVerticalX() == checkWinnerHorizontalX()
                        == checkWinnerDiagonalX() == checkWinnerDiagonalXreturn()
                        == checkWinnerVerticalO() == checkWinnerHorizontalO()
                        == checkWinnerDiagonalO() == checkWinnerDiagonalOreturn() && (table[col][row].hasMarkX())) {
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
}
