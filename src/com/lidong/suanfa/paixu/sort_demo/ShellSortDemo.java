package com.lidong.suanfa.paixu.sort_demo;

public class ShellSortDemo {

	public static void main(String[] args) {
		int a[] = { 3, 1, 5, 7, 2, 4, 9, 6, 10, 8 };
		ShellSortDemo obj = new ShellSortDemo();
		obj.print(a);
		obj.shellSort(a);
		System.out.println("\n排序后：");
		obj.print(a);
	}

	private void shellSort(int[] a) {
		int dk = a.length / 2;
		while (dk >= 1) {
			System.out.print("dk: " + dk + "   array now: ");
			print(a);
			myShell(a, dk);
			System.out.print("dk: " + dk + "   array after: ");
			print(a);
			dk = dk / 2;
		}
	}

	private void ShellInsertSort(int[] a, int dk) {// 类似插入排序，只是插入排序增量是1，这里增量是dk,把1换成dk就可以了
		for (int i = dk; i < a.length; i++) {
			if (a[i] < a[i - dk]) {
				int j;
				int x = a[i];// x为待插入元素
				a[i] = a[i - dk];
				for (j = i - dk; j >= 0 && x < a[j]; j = j - dk) {// 通过循环，逐个后移一位找到要插入的位置。
					a[j + dk] = a[j];
				}
				a[j + dk] = x;// 插入
			}
		}
	}

	private void myShell(int[] a, int dk) {
		for (int i = 0; (i + dk) < a.length; i++) {
			for (int j = i + dk; j < a.length; j += dk) {
				int k;
				int x = a[j];
				for (k = j ; k>0 && a[k-dk] > x; k -= dk) {
					a[k] = a[k-dk];
				}
				a[k] = x;
			}
		}
	}

	public static void print(int a[]) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
}
