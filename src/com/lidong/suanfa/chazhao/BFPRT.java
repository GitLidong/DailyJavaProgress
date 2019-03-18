package com.lidong.suanfa.chazhao;

/**
 * Top K 问题
 */

public class BFPRT {


    //插入排序
    private void insertSort(int[] array, int left, int right) {

        int temp;
        int j;
        for (int i = left + 1; i <= right; i++) {
            temp = array[i];
            j = i - 1;
            while (j >= left && array[j] > temp) {
                array[j + 1] = array[j];
            }
            array[j + 1] = temp;
        }
    }

    ////寻找中位数的中位数
    private int findMidMid(int[] array, int left, int right) {

        if (left == right) {
            return array[left];
        }

        int i = 0 ; 
        return 1;
    }

}