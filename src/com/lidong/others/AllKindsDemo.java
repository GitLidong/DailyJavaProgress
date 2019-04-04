package com.lidong.others;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AllKindsDemo {

	public static void main(String[] args) {

		// GregorianCalendar date = new GregorianCalendar();
		// System.out.println(date);
		// System.out.println(date.YEAR);
		// System.out.println(date.MONTH);
		// System.out.println(date.DAY_OF_WEEK);
		//
		//
		// String a = "Programming";
		// String b = new String("Programming");
		// String c = "Program" + "ming";
		//
		// System.out.println(a == b);
		// System.out.println(a == c);
		// System.out.println(a.equals(b));
		// System.out.println(a.equals(c));
		// System.out.println(a.intern() == b.intern());
		// false true true true true

		// A ab = new B();
		// ab = new B();
		// 1a2b2b

		// A ab;
		// ab = new B();
		// 1a2b

		// String test = "lidong";
		// String after = "";
		// for (char a : test.toCharArray()) {
		// after = a + after;
		// }
		// System.out.println(after);
		// System.out.println(reverse(after));
		//
		// System.out.println("AAAA "+":main camera: Not
		// found".toLowerCase().contains("not found"));

		// String lidong = "123456789ssddasdasd";
		// String te = lidong.substring(0, 3);
		// String rm = lidong.substring(8, lidong.length());
		// System.out.println(lidong + " " + te + " " + rm);

		get4();

	}

	public static void get() {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		System.out.println(test(a));
	}

	public static void get2() {
		Scanner scanner = new Scanner(System.in);
		String data = scanner.nextLine();
		String aim = scanner.nextLine();
		System.out.println(Test2(data, aim));

	}

	public static void get3() {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		Random random = new Random();
		List<Integer> datas = new ArrayList();
		for (int i = 0; i < N; i++) {
			int temp = random.nextInt(1000) + 1;
			if (!datas.contains(temp)) {
				datas.add(temp);
			}
		}

		Collections.sort(datas);
		Iterator<Integer> iterator = datas.iterator();
		iterator.forEachRemaining(System.out::println);

	}

	public static void get4() {
		Scanner in = new Scanner(System.in);
		List<String> datas = new ArrayList();
		while (in.hasNextLine()) {
			String temp = in.nextLine();
			if (temp.equals(" ") || temp == null || temp.equals("")) {
				break;
			}
			datas.add(temp);
		}

		Iterator<String> iterator = datas.iterator();
		while (iterator.hasNext()) {
			deatwith8(iterator.next());
		}

	}

	public static void deatwith8(String data) {
		if (data.length() < 8) {
			while (data.length() != 8) {
				data += "0";
			}
			System.out.println(data);
		} else if (data.length() > 8) {
			System.out.println(data.substring(0, 8));
			deatwith8(data.substring(8, data.length()));
		} else {
			System.out.println(data);
		}
	}

	private static int Test2(String data, String aim) {
		// TODO Auto-generated method stub
		String[] temps = data.split("");
		int count = 0;
		for (int i = 0; i < data.length() - 1; i++) {
			if (temps[i].equalsIgnoreCase(aim)) {
				count++;
			}
		}
		return count;
	}

	private static int test(int data) {
		int a[] = new int[10];
		int total = 0;

		while (data > 0) {
			if (a[data % 10] == 0) {
				a[data % 10]++;
				total = total * 10 + data % 10;
			}
			data = data / 10;
		}

		return total;
	}

	private static void migong(int[][] data) {

	}
	
	
	private static void TimeTask() {
	}

}

class A {

	static {
		System.out.print("1");
	}

	public A() {
		System.out.print("2");
	}
}

class B extends A {

	static {
		System.out.print("a");
	}

	public B() {
		System.out.print("b");
	}
}
