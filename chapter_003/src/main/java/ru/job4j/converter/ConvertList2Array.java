package ru.job4j.converter;

import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int) (Math.ceil((double) list.size() / (double) rows));
        int[][] array = new int[rows][cells];
        int indexInList = 0;
        for (int indexRow = 0; indexRow < rows; indexRow++) {
            for (int indexCell = 0; indexCell < cells; indexCell++) {
                if (indexInList < list.size()) {
                    array[indexRow][indexCell] = list.get(indexInList++);
                } else {
                    break;
                }
            }
        }
        return array;
    }
}