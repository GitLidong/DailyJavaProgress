package com.lidong.suanfa.paixu;

import java.util.Stack;

public class QiuckSortDemoByStack extends ArrayResource {


    public static void main(String[] args) {
        int array[] = {49, 38, 65, 97, 76, 13, 27, 49};
        QiuckSortDemoByStack demo = new QiuckSortDemoByStack();
        demo.quickSort(array, 0, array.length - 1);
        printArray(array);
    }


    private void quickSort(int[] array, int low, int high) {

        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.isEmpty()) {
            int right = stack.pop();
            int left = stack.pop();
            int middle = getMiddle(array, left, right);
            if (left < middle - 1) {
                stack.push(left);
                stack.push(middle - 1);
            }
            if (middle + 1 < right) {
                stack.push(middle + 1);
                stack.push(right);
            }
        }
    }

    private int getMiddle(int array[], int low, int high) {

        int base = array[low];
        while (low < high) {

            while (low < high && array[high] >= base) {
                high--;
            }
            array[low] = array[high];

            while (low < high && array[low] <= base) {
                low++;
            }
            array[high] = array[low];
        }
        array[low] = base;
        return low;

    }
}
