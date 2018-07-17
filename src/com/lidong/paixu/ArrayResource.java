package com.lidong.paixu;

public class ArrayResource {

	protected static void printArray(int[] array) {
		for (int temp : array) {
			System.out.print(temp + " ");
		}
		System.out.print("\n");
	}

	
	protected static void swap(int[] array, int a, int b) {
		if (a == b) {
			return;
		}
		array[a] = array[a] + array[b];
		array[b] = array[a] - array[b];
		array[a] = array[a] - array[b];
	}
}
