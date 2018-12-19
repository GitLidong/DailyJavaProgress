package com.lidong.paixu.sort_demo;

import java.util.Arrays;

public class RadixSort {


    public static void main(String[] args) {
        int a[] = {3, 1, 5, 7, 2, 4, 9, 6, 10, 8};
        RadixSort radixSort = new RadixSort();
        int[] result = radixSort.sort(a);
        for (int temp : result) {
            System.out.print(temp + "\t");
        }
    }


    private int[] sort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int maxDigit = getMaxDigit(arr);

        return radixSort(arr, maxDigit);
    }

    private int[] radixSort(int[] arr, int maxDigit) {

        int mod = 10;
        int dev = 1;
        for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
            // 考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
            int[][] counter = new int[mod * 2][0];

            for (int j = 0; j < arr.length; j++) {
                int bucket = ((arr[j] % mod) / dev) + mod;
                counter[bucket] = arrayAppend(counter[bucket], arr[j]);
            }

            int pos = 0;
            for (int[] bucket : counter) {
                for (int value : bucket) {
                    arr[pos++] = value;
                }
            }
        }

        return arr;
    }

    //自动扩容，并保存数据
    private int[] arrayAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }


    //获取最高位数
    private int getMaxDigit(int[] arr) {
        int maxValue = getMaxValue(arr);
        return getNumLength(maxValue);
    }

    //获取数组中最大值
    private int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    //获取值的长度
    private int getNumLength(long num) {
        if (num == 0) {
            return 1;
        }
        int lenght = 0;
        for (long temp = num; temp != 0; temp /= 10) {
            lenght++;
        }
        return lenght;
    }

}
