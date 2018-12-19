package com.lidong.paixu.sort_demo;

import java.util.Arrays;

public class MergeSortDemo {

    public static void main(String[] args) {

        int array[] = {49, 38, 65, 97, 76, 13, 27, 49, 55, 04};
        for (int temp : array) {
            System.out.printf(temp + "\t");
        }
        System.out.println();
        MergeSortDemo demo = new MergeSortDemo();

        int[] result = demo.sort(array);

        for (int temp : result) {
            System.out.printf(temp + "\t");
        }

    }

    private int[] sort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2);
        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);
        return merge(sort(left), sort(right));
    }

    private int[] merge(int[] left, int[] right) {

        int[] result = new int[left.length + right.length];
        int resultIndex = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                result[resultIndex++] = left[leftIndex++];
            } else {
                result[resultIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length) {
            result[resultIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) {
            result[resultIndex++] = right[rightIndex++];
        }

        return result;
    }

}
