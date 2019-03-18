package com.lidong.suanfa.paixu;

/**
 * 
 * @author LiDong
 * 1, 基本思想：在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对相邻的两个数依次进行比较和调整，
 * 	让较大的数往下沉，较小的往上冒。即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。
 * 
 * 2，示例
 *    49 38 65 97 76 13 27 49 |
 *1,  38 49 65 76 13 27 49 | 97
 *2,  38 49 65 13 27 49 | 76 97
 *3,  38 49 13 27 49 | 65 76 97
 *4,  38 13 27 49 | 49 65 76 97
 *5,  13 27 38 | 49 49 65 76 97
 *6,  13 27 | 38 49 49 65 76 97
 *7,  13 | 27 38 49 49 65 76 97
 *8,  | 13 27 38 49 49 65 76 97
 */

public class BubbleSortDemo extends ArrayResource {

	public static void main(String[] args) {
		int[] array = { 49, 38, 65, 97, 76, 13, 27, 49 };
		BubbleSortDemo demo = new BubbleSortDemo();
		demo.bubbleSort(array);
	}

	private void bubbleSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - i - 1; j++) {
				if (array[j] > array[j + 1]) {
					swap(array, j, j + 1);
				}
			}
			System.out.print(i + ": ");
			printArray(array);
		}
	}

}
