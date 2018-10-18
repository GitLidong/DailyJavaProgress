package com.lidong.paixu.sort2;

/**
 * 
 * @author Dong
 *	插入排序之希尔排序
 *
 *	时间复杂度： 最坏 O(N2)  最好  O(N)  平均 O(N 1.3) 
 *	空间复杂度： 辅助存储 O(1)
 *	稳定性：        不稳定
 */

public class ShellSortDemo {

	public static void main(String[] args) {
		int[] array = { 49, 38, 65, 97, 76, 13, 27, 49 };

		ShellSortDemo demo = new ShellSortDemo();
		demo.shellSort(array);
		Utils.printArray(array);
	}

	private void shellSort(int[] array) {
		int dk = array.length / 2;
		while (dk >= 1) {
			shellSortSort(array, dk);
			dk = dk / 2;
		}
	}

	private void shellSortSort(int[] array, int dk) {
		for (int i = dk; i < array.length; i++) {
			int j;
			int aim = array[i];
			for (j = i; j >= dk && aim < array[j - dk]; j = j - dk) {
				array[j] = array[j - dk];
			}

			array[j] = aim;
		}
	}

}
