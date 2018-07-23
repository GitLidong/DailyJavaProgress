package com.lidong.paixu.sort_demo;

public class SelectSortDemo {

	public static void main(String[] args) {
		int a[] = { 3, 1, 5, 7, 2, 4, 9, 6, 10, 8 };

		SelectSortDemo obj = new SelectSortDemo();
		System.out.println("\n排序前：");
		print(a);
		obj.selectSort(a);
		System.out.println("\n排序后：");
		print(a);
	}

	private void selectSort(int[] a) {
		int i, j, min, max, temp;
		for (i = 0; i < a.length / 2; i++) {
			min = i;
			max = i;
			for (j = i+1 ; j < a.length - i; j++) {
				if (a[min] > a[j]) {
					min = j;
				}
				if (a[max] < a[j]) {
					max = j;
				}
			}
			temp = a[i];
			a[i] = a[min];
			a[min] = temp;
			
			if(max == i) {
				max = min;
			}
			temp = a[a.length - i - 1];
			a[a.length - 1 - i] = a[max];
			a[max] = temp;
			System.out.print("第"+(i+1)+"次:   ");
			print(a);
		}
	}
	
	private static void print(int a[]) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
}
