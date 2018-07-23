package com.lidong.paixu.sort_demo;

public class QuickSortDemo {

	public static void main(String[] args) {
		int a[] = { 3, 1, 5, 7, 2, 4, 9, 6, 10, 8 };
		QuickSortDemo obj = new QuickSortDemo();
		System.out.println("初始值：");
		obj.print(a);
		obj.quickSort(a, 0, a.length - 1);
		System.out.println("\n排序后：");
		obj.print(a);
	}

	private void quickSort(int[] a, int low, int high) {

		if (low < high) {// 如果不加这个判断递归会无法退出导致堆栈溢出异常
			int middle = getMiddle(a, low, high);
			quickSort(a, low, middle - 1);// 递归对低子表递归排序
			quickSort(a, middle + 1, high);// 递归对高子表递归排序
		}

	}

	private int getMiddle(int[] a, int low, int high) {

		int key = a[low];
		while (low < high) {
			while (low < high && a[high] >= key) {
				high--;
			}
			a[low] = a[high];
			a[high] = key;
			while (low < high && a[low] <= key) {
				low++;
			}
			a[high] = a[low];
			a[low] = key;
		}
		return low;

	}

	public void print(int a[]) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
}
