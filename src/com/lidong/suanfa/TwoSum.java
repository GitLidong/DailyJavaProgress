package com.lidong.suanfa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
	/**
	 * 时间复杂度为 O(N^2)
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] getTwo1(int[] nums, int target) {
		int[] result = new int[2];

		for (int i = 0; i < nums.length; i++) {
			int a = nums[i];
			for (int j = nums.length - 1; j >= 0; j--) {
				int b = nums[j];

				if ((a + b) == target) {
					result = new int[] { i, j };
				}
			}
		}
		return result;
	}

	public int[] getTwo2(int[] nums, int target) {
		int[] result = new int[2];
		Map<Integer, Integer> map = new HashMap<>(2);
		for (int i = 0; i < nums.length; i++) {

			if (map.containsKey(nums[i])) {
				result = new int[] { map.get(nums[i]), i };
			}
			map.put(target - nums[i], i);
		}
		return result;
	}

	public static void main(String[] args) {

		int[] nums = { 1, 3, 5, 7 };
		TwoSum demo = new TwoSum();
		System.out.println("begin");
		System.out.println(Arrays.toString(demo.getTwo1(nums, 8)));
		System.out.println(Arrays.toString(demo.getTwo2(nums, 8)));
	}
}
