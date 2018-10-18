package com.lidong.suanfa.array;

import java.util.HashMap;

public class SercondMin {

	public static void main(String[] args) {

		int[] array = { 3, 4, 5, 2, 3, 4, 5, 0, 1 };

		// System.out.println(1 ^ 1);

		// System.out.println(getSecondMin(array));
		System.out.println(getFirstNoDup(array));

		int[] data1 = { 1, 3, 5, 7, 9, 10, 11, 12 };
		int[] data2 = { 2, 4, 6, 8 };

		int[] data = together(data1, data2);
		for (int temp : data) {
			System.out.print(temp + "\t");
		}

	}

	private static int getSecondMin(int[] array) {
		if (array == null || array.length == 0 || array.length == 1) {
		}
		int min1 = array[0];
		int min2 = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] < min1) {
				min2 = min1;
				min1 = array[i];
			}
			if (array[i] < min2 && array[i] > min1) {
				min2 = array[i];
			}
		}

		return min2;
	}

	private static int getFirstNoDup(int[] array) {

		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < array.length; i++) {
			if (map.containsKey(array[i])) {
				map.put(array[i], map.get(array[i]) + 1);
			} else {
				map.put(array[i], 1);
			}
		}
		for (int i = 0; i < array.length; i++) {
			if (map.get(array[i]) == 1) {
				return array[i];
			}
		}
		return (Integer) null;
	}

	private static int[] together(int[] data1, int[] data2) {

		int i = 0, j = 0;
		int k = 0;
		int[] data = new int[data1.length + data2.length];
		while (i < data1.length && j < data2.length) {
			if (data1[i] < data2[j]) {
				data[k++] = data1[i++];
			} else {
				data[k++] = data2[j++];
			}
		}

		if (i == data1.length) {
			while (j < data2.length) {
				data[k++] = data2[j++];
			}
		} else {
			while (i < data1.length) {
				data[k++] = data1[i++];
			}
		}

		return data;

	}

}
