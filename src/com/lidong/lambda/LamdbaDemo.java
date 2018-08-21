package com.lidong.lambda;

import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.Timer;

public class LamdbaDemo {

	public static final ThreadLocal<SimpleDateFormat> DATEFORMATE = new ThreadLocal<SimpleDateFormat>() {

		@Override
		protected SimpleDateFormat initialValue() {
			// TODO Auto-generated method stub
			return super.initialValue();
		}

	};

	public static final ThreadLocal<SimpleDateFormat> DATEFORMATE1 = ThreadLocal
			.withInitial(() -> new SimpleDateFormat());

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] plante = { "SSSSSSSSS", "DDDDDD", "RRRRRRRRR", "GGGGGGGg" };

		Arrays.sort(plante, new LengthCompartor1());

		Arrays.sort(plante, (first, second) -> first.length() - second.length());

		Timer timer = new javax.swing.Timer(1000, event -> {
			Toolkit.getDefaultToolkit().beep();
		});

		Runnable runnable = () -> {

		};

	}

}

class LengthCompartor1 implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		return o1.length() - o2.length();
	}

}
