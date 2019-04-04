package com.lidong.suanfa.paixu.sort_demo;

public class Utils {
    public static int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    public static void print(int[] arr) {
        for (int temp : arr) {
            System.out.print(temp + "\t");
        }
        System.out.println();
    }

    public static void swap(int[] array, int a, int b) {
        if (a == b) {
            return;
        }
        array[a] = array[a] + array[b];
        array[b] = array[a] - array[b];
        array[a] = array[a] - array[b];
    }
}
