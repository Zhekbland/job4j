package ru.job4j.outputinput;

import java.util.Arrays;

/**
 *
 */
public class Array {
    /**
     *
     * @param args - arg.
     */
    public static void main(String[] args) {
        int[][] ar = new int[3][3];
        int j;
        for (int i = 0; i < ar.length; i++) {
            for (j = 0; j < i + 1; j++) {
                ar[i][j] = (int) (Math.random() * 18);
                System.out.print(ar[i][j] + "\t");
            }
            System.out.println("\n");
        }
        int sum = 0;
        int[] ar2 = new int[] {5, 1, 3, 2, 9};
        for (int k: ar2) {
            sum = sum + k;
        }
        Arrays.sort(ar2);
        for (int i: ar2) {
            System.out.print(i + "\t");
        }
        System.out.println();
        int[] ar3 = Arrays.copyOf(ar2, 10);
        int[] arr = new int[] {4, 1, 7, 2};
        int[] arr2 = new int[] {8, 3, 9, 6};
        int[] arr3 = Arrays.copyOf(arr, 8);
        for (int i: arr3) {
            System.out.print(i + "\t");
        }
        System.out.println();
        int[] sourceArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] destArr = new int[] {1, 2, 3, 0, 0, 0, 0, 0};

        System.arraycopy(arr2, 0, arr3, 4, 4);
        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i] + " ");
        }
    }
}
