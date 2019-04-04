package com.lidong.suanfa.paixu.sort_demo;

/*
 * Java实现希尔排序（缩小增量排序）
 *两个步骤：1，建堆  2，对顶与堆的最后一个元素交换位置
 */

public class SimpleSelectSortDemo {

    public static void main(String[] args) {
        int a[] = {3, 1, 5, 7, 2, 4, 9, 6, 10, 8};
        System.out.println("初始值：");
        Utils.print(a);
        selectSort(a);
        System.out.println("\n排序后：");
        Utils.print(a);

    }

    private static void selectSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int k = i;// k存放最小值下标。每次循环最小值下标+1
            for (int j = i + 1; j < a.length; j++) {// 找到最小值下标
                if (a[k] > a[j])
                    k = j;
            }
            Utils.swap(a, k, i);// 把最小值放到它该放的位置上
        }
    }
}
