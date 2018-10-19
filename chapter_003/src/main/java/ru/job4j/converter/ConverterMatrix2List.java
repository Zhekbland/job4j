package ru.job4j.converter;

import java.util.ArrayList;
import java.util.List;

public class ConverterMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] rowArray : array) {
            for (int indexOfCell : rowArray) {
                list.add(indexOfCell);
            }
        }
        return list;
    }
}
