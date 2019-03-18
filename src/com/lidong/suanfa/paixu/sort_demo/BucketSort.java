package com.lidong.suanfa.paixu.sort_demo;

import java.util.Arrays;

public class BucketSort {

    public static void main(String[] args) {

        int a[] = {3, 1, 5, 7, 2, 4, 9, 6, 10, 8};
        BucketSort bucketSort = new BucketSort();
        int[] result = bucketSort.sort(a);
        for (int temp : result) {
            System.out.print(temp + "\t");
        }

    }

    private int[] sort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        return bucketSort(arr, 5);
    }

    private int[] bucketSort(int[] arr, int bucketSize) {

        if (arr.length == 0) {
            return arr;
        }
        int minValue = arr[0];
        int maxValue = arr[0];
        for (int value : arr) {
            if (value < minValue) {
                minValue = value;
            } else if (value > maxValue) {
                maxValue = value;
            }
        }
        int bucketCount = (int) (Math.floor((maxValue - minValue) / bucketSize) + 1);
        System.out.println(bucketCount);
        int[][] buckets = new int[bucketCount][0];

        // 利用映射函数将数据分配到各个桶中
        for (int i = 0; i < arr.length; i++) {
            int index = (int) Math.floor((arr[i] - minValue) / bucketSize);
            buckets[index] = arrAppend(buckets[index], arr[i]);
        }

        int arrIndex = 0;
        for (int[] bucket : buckets) {
            if (bucket.length <= 0) {
                continue;
            }
            // 对每个桶进行排序，这里使用了插入排序
            bucket = insertSort(bucket);
            for (int value : bucket) {
                arr[arrIndex++] = value;
            }
        }
        return arr;
    }

    //自动扩容，并保存数据
    private int[] arrAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }

    private int[] insertSort(int[] arrs) {
        for (int i = 0; i < arrs.length; i++) {
            int j;
            int aim = arrs[i];
            for (j = i; j > 0 && arrs[j - 1] > aim; j--) {
                arrs[j] = arrs[j - 1];
            }
            arrs[j] = aim;
        }

        return arrs;
    }

}
