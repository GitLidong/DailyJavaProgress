package com.lidong.suanfa.array;

public class ArrayDemo {


    public static void main(String[] args) {
        int[] arry = {-5, -1, 0, 5, 9, 11, 13, 15, 22, 35, 46};

        findSum(31, arry);

    }

    public static void findSum(int sum, int[] arry) {
        if (arry.length <= 1) {
            System.out.println("arr wrong");
            return;
        }
        int i = 0;
        int j = arry.length - 1;
        while (i != j) {
            int tmpSum = arry[i] + arry[j];
            if (tmpSum == sum) {
                System.out.println("a[" + i + "] = " + arry[i] + ", a[" + j + "] = " + arry[j]);
                return;
            }
            if (tmpSum < sum) {
                i++;
            }
            if (tmpSum > sum) {
                j--;
            }
        }
        System.out.println("not found");
    }

}
