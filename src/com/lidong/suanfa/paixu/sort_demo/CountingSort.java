package com.lidong.suanfa.paixu.sort_demo;

import java.util.Arrays;

/**
 * 计数排序
 */

public class CountingSort {

    public static void main(String[] args) {
        int a[] = {3, 1, 5, 7, 2, 4, 9, 6, 10, 8};
        a = sort(a);
        Utils.print(a);
    }

    public static int[] sort(int[] array) {

        int[] data = Arrays.copyOf(array, array.length);
        int maxValue = Utils.getMaxValue(data);

        return countingSort(data, maxValue);
    }

    private static int[] countingSort(int[] data, int maxValue) {
        int bucketLen = maxValue + 1;
        int[] bucket = new int[bucketLen];

        for (int temp : data) {
            bucket[temp]++;
        }

        int index = 0;
        for (int i = 0; i < bucketLen; i++) {
            while (bucket[i] > 0) {
                data[index++] = i;
                bucket[i]--;
            }
        }
        return data;
    }


}
