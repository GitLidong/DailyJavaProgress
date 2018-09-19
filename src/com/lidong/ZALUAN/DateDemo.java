package com.lidong.ZALUAN;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateDemo {
	public static void main(String args[]) {

		// Date dNow = new Date();
		// SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a
		// zzz");
		//
		// System.out.println("Current Date: " + ft.format(dNow));

		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// Calendar cal = Calendar.getInstance();
		// cal.set(Calendar.YEAR, 2006);
		// cal.set(Calendar.MONTH, 8);
		// cal.set(Calendar.DAY_OF_MONTH, 3);
		// cal.add(Calendar.DATE, -4);
		// Date date = cal.getTime();
		// System.out.println(df.format(date));
		// cal.add(Calendar.DATE, 4);
		// date = cal.getTime();
		// System.out.println(df.format(date));

		DateDemo demo = new DateDemo();
		System.out.println(demo.test());
	}

	static int test() {
		int i = 1;
		try {
			System.out.println("try里面的i : " + i);
			return i;
		} finally {
			System.out.println("进入finally...");
			++i;
			System.out.println("fianlly里面的i : " + i);
			// return i;
		}
	}

}
