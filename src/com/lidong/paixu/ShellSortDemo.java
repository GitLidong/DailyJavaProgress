package com.lidong.paixu;
import com.lidong.threaddemo.synchronizer.CountDownAwaitDemo;

/**
 * 
 * @author LiDong
 * 
 *         基本思想：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，
 *         待整个序列中的记录“基本有序”时，再对全体记录进行依次直接插入排序。
 * 
 *         操作方法： 1，选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1； 2，按增量序列个数k，对序列进行k 趟排序；
 *         3，每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。 仅增量因子为1
 *         时，整个序列作为一个表来处理，表长度即为整个序列的长度。 * 示例： 49 38 65 97 76 13 27 49 55 04 13
 *         49 27 38 49 65 55 97 04 76
 * 
 *         13 27 49 55 04 49 38 65 97 76 -> 增量5，第一次排序后 04 13 38 49 97 27 49 55
 *         65 76 04 27 13 49 38 55 49 65 97 76 ->增量2，第二次排序后
 * 
 *         04 13 27 38 49 49 55 65 76 97 ->增量1，第三次排序后
 * 
 * 
 *         希尔排序时效分析很难，关键码的比较次数与记录移动次数依赖于增量因子序列d的选取，
 *         特定情况下可以准确估算出关键码的比较次数和记录的移动次数。 目前还没有人给出选取最好的增量因子序列的方法。
 *         增量因子序列可以有各种取法，有取奇数的，也有取质数的， 但需要注意：增量因子中除1 外没有公因子，且最后一个增量因子必须为1。
 * 
 *         希尔排序方法是一个不稳定的排序方法。
 **/

public class ShellSortDemo extends ArrayResource {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int array[] = { 49, 38, 65, 97, 76, 13, 27, 49, 55, 04 };
		ShellSortDemo demo = new ShellSortDemo();
		demo.shellSort(array);
		printArray(array);
	}

	private void shellSort(int[] array) {
		int dk = array.length / 2;
		while (dk >= 1) {
			ShellInsertSort(array, dk);
			dk = dk / 2;
		}
	}

	private void ShellInsertSort(int[] array, int dk) {
		for (int i = dk; i < array.length; i++) {
			if (array[i] < array[i - dk]) {
				int j = i;
				int aim = array[i];
				for (j = i; (j - dk) >= 0 && array[j - dk] > aim; j = j - dk) {
					array[j] = array[j - dk];
				}
				array[j] = aim;
			}
		}
		printArray(array);
	}

}
