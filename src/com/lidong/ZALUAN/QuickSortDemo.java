package com.lidong.ZALUAN;

public class QuickSortDemo {

	public static void main(String[] args) {
		int array[] = { 49, 38, 65, 97, 76, 13, 27, 49 };
		QuickSortDemo demo = new QuickSortDemo();
		demo.quickSort(array, 0, array.length - 1);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + "\t");
		}
	}

	private void quickSort(int[] array, int low, int high) {
		if (low < high) {
			int middle = getMiddle(array, low, high);
			quickSort(array, low, middle - 1);
			quickSort(array, middle + 1, high);
		}
	}

	private int getMiddle(int[] array, int low, int high) {
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
