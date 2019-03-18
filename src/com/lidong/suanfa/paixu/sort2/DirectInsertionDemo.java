package com.lidong.suanfa.paixu.sort2;

/**
 * 
 * @author Dong
 *	插入排序之直接插入排序
 *
 *	时间复杂度： 最坏 O(N2)  最好  O(N)  平均 O(N2) 
 *	空间复杂度： 辅助存储 O(1)
 *	稳定性：         稳定
 */

public class DirectInsertionDemo {

	public static void main(String[] args) {
		int[] array = { 49, 38, 65, 97, 76, 13, 27, 49 };

		DirectInsertionDemo demo = new DirectInsertionDemo();
		demo.directInsert(array);
		Utils.printArray(array);

	}

	private void directInsert(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int j;
			int x = array[i];
			for (j = i; j > 0 && x < array[j - 1]; j--) {
				array[j] = array[j - 1];
			}
			array[j] = x;
		}

	}

}
