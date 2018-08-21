package com.lidong.set_demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class CollectionDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CollectionDemo demo = new CollectionDemo();

		demo.list1();

		demo.list2();
	}

	private void list1() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add("ele" + i);
		}

		for (String temp : list) {
			System.out.print(temp + " ");
		}

		System.out.println();

		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}

		System.out.println();
		iterator = list.iterator();
		iterator.forEachRemaining(temp -> System.out.print(temp + " "));

		System.out.println();
		iterator = list.iterator();
		iterator.next();
		iterator.remove();// 删除了集合中的元素

		iterator = list.iterator();
		iterator.forEachRemaining(temp -> System.out.print(temp + " "));
	}

	private void list2() {
		System.out.println("list 2");

		List<String> list = new LinkedList<>();
		list.add("Amy");
		list.add("Bob");
		list.add("Carl");

		Iterator<String> iterator = list.iterator();
		iterator.next();
		iterator.next();
		iterator.remove();// 没有add方法

		iterator = list.iterator();
		iterator.forEachRemaining(temp -> System.out.print(temp + " "));
		System.out.println();

		ListIterator<String> iterator2 = list.listIterator();
		iterator2.next();
		iterator2.add("Bob");// ListIterator 添加了add方法
		iterator2.add("Bob2");
		iterator2.add("Bob3");

		iterator2 = list.listIterator();
		iterator2.forEachRemaining(temp -> System.out.print(temp + " "));
	}

}
