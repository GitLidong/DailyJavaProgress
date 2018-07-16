package com.lidong.set_demo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class HashSetDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSetDemo demo = new HashSetDemo();
		demo.hashSetTest();
	}

	private void hashSetTest() {
		Set<String> words = new HashSet<>();
		long totalTime = 0;
		System.out.println("input words, type 'end' to exit");
		Scanner in = new Scanner(System.in);
		while (true) {
			String word = in.next();
			long calltime = System.currentTimeMillis();
			words.add(word);
			calltime = System.currentTimeMillis() - calltime;
			totalTime += calltime;
			if (word.equals("end")) {
				break;
			}
		}
		System.out.println("totalTime: " + totalTime);
		Iterator<String> iter = words.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}
