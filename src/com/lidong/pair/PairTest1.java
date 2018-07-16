package com.lidong.pair;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PairTest1 {

	public static void main(String[] args) {
		String [] words = {
				"Mary","had","a","little","lamb"
		};
		Pair<String> mm = ArrayArg.minmax(words);
		System.out.println("Words min: "+mm.getFirst());
		System.out.println("Words max: "+mm.getSecond());
		
		GregorianCalendar [] birthdays = {
				new GregorianCalendar(1994, Calendar.OCTOBER, 10),
				new GregorianCalendar(1995,Calendar.JANUARY,3),
				new GregorianCalendar(2017,Calendar.DECEMBER,2)
		};
		
		Pair<GregorianCalendar> zz = ArrayArg.minmax(birthdays);
		System.out.println("GregorianCalendar min: "+zz.getFirst().getTime());
		System.out.println("GregorianCalendar max: "+zz.getSecond().getTime());
	}
	
}

class ArrayArg {
	
	public static <T extends Comparable> Pair<T> minmax(T [] a) {
		if(a == null || a.length == 0) {
			return null;
		}
		T min = a[0];
		T max = a[0];
		for (T temp : a) {
			if(min.compareTo(temp) > 0) {
				min = temp;
			}
			if (max.compareTo(temp) < 0 ) {
				max = temp;
			}
		}
		return new Pair<T>(min,max);
	}
}