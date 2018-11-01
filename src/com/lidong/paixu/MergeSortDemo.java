package com.lidong.paixu;

/**
 * 
 * @author LiDong 基本思想：归并（Merge）排序法是将两个（或两个以上）有序表合并成一个新的有序表，
 *         即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列。
 *
 */

// 示例：
// 0, (49) (38) (65) (97) (76) (13) (27)
// 1, (38 49) (65 97) (13 76) (27)
// 2, (38 49 65 97) (13 27 76)
// 3, (13 27 38 49 65 76 97)

public class MergeSortDemo extends ArrayResource {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int array[] = { 49, 38, 65, 97, 76, 13, 27, 49, 55, 04 };
		printArray(array);
		MergeSortDemo demo = new MergeSortDemo();
		demo.mergeSort(array);
		printArray(array);
	}

	private void mergeSort(int[] array) {
		sort(array, 0, array.length - 1);
	}

	private void sort(int[] array, int left, int right) {
		if (left >= right) {
			return;
		}
		int center = (left + right) / 2;
		sort(array, left, center);
		sort(array, center + 1, right);
		merge(array, left, center, right);
	}

	private void merge(int[] array, int left, int center, int right) {
		System.out.println("merge left , center , right :" + left + " , " + center + " , " + right);
		int[] tempArray = new int[array.length]; // 临时数组
		int indexLeft = left; // 缓存左数组第一个元素的索引
		int indexRight = center + 1; // 右数组第一个元素索引
		int indexArray = left; // 记录临时数组的索引
		// 从两个数组中取出最小的放入临时数组
		while (indexLeft <= center && indexRight <= right) {
			if (array[indexLeft] <= array[indexRight]) {
				tempArray[indexArray++] = array[indexLeft++];
			} else {
				tempArray[indexArray++] = array[indexRight++];
			}
		}
		// 剩余部分依次放入临时数组（实际上两个while只会执行其中一个）
		while (indexLeft <= center) {
			tempArray[indexArray++] = array[indexLeft++];
		}
		while (indexRight <= right) {
			tempArray[indexArray++] = array[indexRight++];
		}
		while (left <= right) {
			array[left] = tempArray[left++];
		}
	}

}
