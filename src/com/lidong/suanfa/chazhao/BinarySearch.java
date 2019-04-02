package com.lidong.suanfa.chazhao;

/**
 * 二分查找
 */

public class BinarySearch {

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5};
        System.out.println(binarySearch(array, array.length, 3));

    }

    public static int binarySearch(int[] array, int length, int aim) {

        int low = 0;
        int high = length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int midValue = array[mid];
            if (midValue < aim) {
                low = mid + 1;
            } else if (midValue > aim) {
                high = mid - 1;
            } else {
                return mid;
            }

        }
        return ~low;
    }

}
