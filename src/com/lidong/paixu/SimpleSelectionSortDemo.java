package com.lidong.paixu;

/**
 * 
 * @author LiDong
 * 
 * 基本思想：在要排序的一组数中，选出最小（或者最大）的一个数与第1个位置的数交换；
 * 然后在剩下的数当中再找最小（或者最大）的与第2个位置的数交换，
 * 依次类推，直到第n-1个元素（倒数第二个数）和第n个元素（最后一个数）比较为止。
 * 
 * 示例:
 *      3 1 5 7 2 4 9 6
 *  1， 1 3 5 7 2 4 9 6
 *  2， 1 2 3 5 7 4 9 6
 *  3， 1 2 3 4 5 7 9 6
 *  4， 1 2 3 4 5 7 9 6
 *  5， 1 2 3 4 5 7 9 6
 *  6， 1 2 3 4 5 6 7 9
 *  7， 1 2 3 4 5 6 7 9
 *  8， 1 2 3 4 5 6 7 9
 *  
 *   操作方法：
 *第一趟，从n 个记录中找出关键码最小的记录与第一个记录交换；
 *第二趟，从第二个记录开始的n-1 个记录中再选出关键码最小的记录与第二个记录交换；
 *以此类推.....
 *第i 趟，则从第i 个记录开始的n-i+1 个记录中选出关键码最小的记录与第i 个记录交换，
 *直到整个序列按关键码有序
 *
 */

public class SimpleSelectionSortDemo extends ArrayResource {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int array[] = { 3, 1, 5, 7, 2, 4, 9, 6, 10, 8 };
		SimpleSelectionSortDemo demo = new SimpleSelectionSortDemo();
		demo.simpleSelectionSort(array);
		printArray(array);
	}

	private void simpleSelectionSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[minIndex] > array[j]) {
					minIndex = j;
				}
			}
			swap(array, i, minIndex);
		}
	}

}
