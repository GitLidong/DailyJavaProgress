package com.lidong.suanfa.paixu.sort2;

/**
 * 
 * @author Dong
 *	选择排序之简单选择
 *
 *	时间复杂度： 最坏 O(N2)  最好  O(N2)  平均 O(N2) 
 *	空间复杂度： 辅助存储 O(1)
 *	稳定性：        不稳定
 */

public class DirectSelectionDemo {

	public static void main(String[] args) {
		int[] array = { 49, 38, 65, 97, 76, 13, 27, 49 };
		DirectSelectionDemo demo = new DirectSelectionDemo();
		demo.directSelect(array);
		Utils.printArray(array);
	}

	private void directSelect(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int min = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[min]) {
					min = j;
				}
			}
			swap(array, i, min);
		}
	}

	private void swap(int[] array, int i, int j) {
		if( i == j ) {
			return;
		}
		array[i] = array[i] + array[j];
		array[j] = array[i] - array[j];
		array[i] = array[i] - array[j];
	}

}
