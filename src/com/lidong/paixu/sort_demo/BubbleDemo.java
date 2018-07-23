package com.lidong.paixu.sort_demo;

public class BubbleDemo {

	public static void main(String[] args) {
		int a[] = { 3, 1, 5, 7, 2, 4, 9, 6, 10, 8 };
		BubbleDemo obj = new BubbleDemo();
		System.out.println("\n排序前：");
		print(a);
		obj.bubble2(a);
		System.out.println("\n排序后：");
		print(a);
	}

	private void bubble1(int[] a) {
		int count = 0;
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j + 1] < a[j]) {
					a[j] = a[j] + a[j + 1];
					a[j + 1] = a[j] - a[j + 1];
					a[j] = a[j] - a[j + 1];
				}
				System.out.print(i + " " + j + " : ");
				print(a);
				count++;
			}
		}
		System.out.println(count);
	}

	private void bubble2(int[] a) {
		int count = 0 ;
		int lenght = a.length;
		int low = 0, high = lenght - 1, temp, i;

		while (low < high) {
			for (i = low; i < high; i++) {
				if (a[i] > a[i + 1]) {
					temp = a[i];
					a[i] = a[i + 1];
					a[i + 1] = temp;
				}
				count ++;
			}
			high--;
			for (i = high;i>low;i--) {
				if (a[i] < a[i-1]) {
					temp = a[i];
					a[i] = a[i-1];
					a[i-1] = temp; 
				}
				count ++;
			}
			low++;
		}
		
		System.out.println(count);
	}

	private static void print(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

}
