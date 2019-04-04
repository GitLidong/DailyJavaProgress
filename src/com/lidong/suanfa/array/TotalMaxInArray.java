package com.lidong.suanfa.array;

/**
 * 打印出数组中相加最大的连续整数
 */

public class TotalMaxInArray {

    public static void main(String[] args) throws Exception {

        int[] data = {1, -2, 3, 10, -4, 7, 2, -5};
        System.out.println(totalMax(data, data.length));
    }

    private static int totalMax(int[] datas, int size) throws Exception {
        if (size <= 0) {
            throw new Exception("Array size empty");
        }
        int sum = 0;
        int max = -(1 << 31);
        int cur = 0;
        int maxBegin = 0;
        while (cur < size) {
            sum += datas[cur++];
            if (sum > max) {
                max = sum;
            } else if (sum < 0) {
                sum = 0;
                maxBegin = cur;
            }
            System.out.println(cur + " , " + sum + " , " + max + " , " + maxBegin);
        }
        int temp = 0;
        while (temp < max) {
            temp += datas[maxBegin];
            System.out.print(datas[maxBegin] + "\t");
            maxBegin++;
        }
        return max;
    }

}
