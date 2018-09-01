package com.lidong.suanfa;

/**
 * 
 * @author LiDongc
 *
 */

public class BFS_demo {

	public static void main(String[] args) {
		int count = 0;
		int num = 0;
		for (int i = 0; i <= 100; i++) {
			num = num + i;
			// count = count++;
			count++;
		}
		System.out.println("num * count = " + num * count);
	}

}
