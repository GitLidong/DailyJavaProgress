package com.lidong.others;

import java.util.GregorianCalendar;

public class AllKindsDemo {

	public static void main(String[] args) {

//		GregorianCalendar date = new GregorianCalendar();
//		System.out.println(date);
//		System.out.println(date.YEAR);
//		System.out.println(date.MONTH);
//		System.out.println(date.DAY_OF_WEEK);
//		
//		
//		String a = "Programming";
//		String b = new String("Programming");
//		String c = "Program" + "ming";
//		
//		System.out.println(a == b);
//		System.out.println(a == c);
//		System.out.println(a.equals(b));
//		System.out.println(a.equals(c));
//		System.out.println(a.intern() == b.intern());
		// false true true true true

//        A ab = new B();
//        ab = new B();
		// 1a2b2b

//		A ab;
//		ab = new B();
		// 1a2b

		String test = "lidong";
		String after = "";
		for (char a : test.toCharArray()) {
			after = a + after;
		}
		System.out.println(after);
		System.out.println(reverse(after));
		
		System.out.println("AAAA   "+":main camera: Not found".toLowerCase().contains("not found"));
	}

	public static String reverse(String originStr) {
		if (originStr == null || originStr.length() <= 1) {
			return originStr;
		} else {
			return reverse(originStr.substring(1)) + originStr.charAt(0);
		}
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
