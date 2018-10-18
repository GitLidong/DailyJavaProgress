package com.lidong.ZALUAN;

import java.util.HashSet;
import java.util.Set;

public class HappyNum {

	public boolean isHappy(int number) {
		Set<Integer> set = new HashSet<>();

		while (number != 1) {
			int sum = 0;
			String line = "";
			while (number > 0) {
				sum += (number % 10) * (number % 10);
				line += (number % 10) + " * " + (number % 10) + " + ";
				number = number / 10;
			}
			System.out.println(line.substring(0, line.length() - 2) + " = " + sum);
			if (set.contains(sum)) {
				System.out.println("Include in set , not happy num !");
				return false;
			} else {
				System.out.println("Happy num !");
				set.add(sum);
			}
			number = sum;
		}
		return true;
	}

	public static void main(String[] args) {

		HappyNum demo = new HappyNum();
		demo.isHappy(19);

	}

}
