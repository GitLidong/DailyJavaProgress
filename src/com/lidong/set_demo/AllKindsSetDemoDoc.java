package com.lidong.set_demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class AllKindsSetDemoDoc {

	public static void main(String[] args) {

		AllKindsSetDemoDoc test = new AllKindsSetDemoDoc();
		// test.hashSetTest();

		// test.treeSetTest();

		test.hashMapTest();
	}

	/*
	 * Java提供了HashSet，add方法添加元素，contains方法查看是否包含元素。
	 * 散列集的迭代器将以此访问所有的桶，但由于散列将元素分散在表的各个位置，所以访问他们的顺序几乎是随机的。 只有不关心集合中元素的顺序时才使用HashSet。
	 */
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

	/*
	 * TreeSet 与散列集十分类似，但是树集是一个有序集合。可以按任意顺序插入元素，在集合进行遍历时，每个值将自动按照排序后的顺序呈现。
	 * TreeSet当前使用的是红黑树。 将一个元素添加到树中要比添加到散列表中慢，但是比添加到数组会链表中的正确位置还是快很多的。
	 * n个元素的树，添加元素时，查找新元素正确位置平均需要 log N 次比较。
	 * 
	 * 
	 * 默认情况下树集假定插入的元素实现了 Comparable 接口，通过 compareTo（T other)方法进行比较。 或者是自定义类实现了
	 * Comparator 接口，通过 compare(T a, T b)进行比较
	 */
	private void treeSetTest() {
		Set<String> words = new TreeSet<>();
		words.add("Boys");
		words.add("Amay");
		words.add("Chenn");
		for (String temp : words) {
			System.out.println(temp);
		}
	}

	private void hashMapTest() {
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("AA", "111");
		hashMap.put("BB", "222");
		hashMap.put("CC", "333");
		hashMap.put("DD", "444");

		for (Entry<String, String> temp : hashMap.entrySet()) {
			System.out.println("key: " + temp.getKey() + " ,value: " + temp.getValue());
		}
	}
}
