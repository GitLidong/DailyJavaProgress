package com.lidong.hashtable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import sun.print.resources.serviceui;

/*
 * 散列表（hash table)可以快速查找所需要的对象，也叫做哈希表。
 * 散列表为每个对象计算一个整数（hashCode)，称为散列码，散列码是由对象的实例域产生的一个整数，更准确的说是具有不同数据域的对象将产生不同的散列码。
 * （如果自定义类，就要负责实现这个类的hashCode方法。并且需要注意的是hasCode方法要与equals方法兼容。）
 * （最好使用String Integer这样的 Wrapper类作为键，String最常见，因为它不可变，也是final的，同时也重写过了 hasCode 和 equals方法）
 * Java中散列表用链表数组实现，每个列表被称为桶（bucket)。
 * 对象的位置，由对象的散列码与桶数取余，得到该对象的桶的索引。
 * 如果桶中没有其他元素，那么直接插入。
 * 如果有，那么发生散列冲突，这时需要用新对象与桶中的所有对象进行比较。如果已经有的话，会覆盖，没有的话有四种处理散列冲突的方法。
 * 以HashMap为例，HashMap用链表存储对象，所以这个值会被存储在后面的链表中。
 * 
 * 控制散列表的运行性能的话，要指定初始的桶数，通常设置为预计元素个数的 75% ~ 150% ， 并且最好将桶数设置为素数，防止键的集聚。
 * 
 * 如果散列表太慢，超过了负载因子（ load factor： 0.75为默认值）,即超过了 75%的位置都已经填入元素，那么需要再散列（rehashed)（翻倍？）
 * 
 * 散列表可以用于实现几个重要的数据结构，其中最简单的是set类型。set没有重复元素。
 */

public class SetTest {
	
	public static void main(String[] args) {

		SetTest test = new SetTest();
		//test.hashSetTest();
		
		test.treeSetTest();
	}
	
	
	/*
	 * Java提供了HashSet，add方法添加元素，contains方法查看是否包含元素。
	 * 散列集的迭代器将以此访问所有的桶，但由于散列将元素分散在表的各个位置，所以访问他们的顺序几乎是随机的。
	 * 只有不关心集合中元素的顺序时才使用HashSet。
	 */
	private void hashSetTest() {
		Set<String> words = new HashSet<>();
		long totalTime = 0;
		System.out.println("input words");
		Scanner in = new Scanner(System.in);
		while(true) {
			String word = in.next();
			long calltime = System.currentTimeMillis();
			words.add(word);
			calltime = System.currentTimeMillis()-calltime;
			totalTime+=calltime;
			if (word.equals("end")) {
				break;
			}
		}
		System.out.println("totalTime: "+totalTime);
		Iterator<String> iter = words.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
	
	
	/*
	 * TreeSet 与散列集十分类似，但是树集是一个有序集合。可以按任意顺序插入元素，在集合进行遍历时，每个值将自动按照排序后的顺序呈现。
	 * TreeSet当前使用的是红黑树。
	 * 将一个元素添加到树中要比添加到散列表中慢，但是比添加到数组会链表中的正确位置还是快很多的。
	 * n个元素的树，添加元素时，查找新元素正确位置平均需要 log N 次比较。
	 * 
	 * 
	 * 默认情况下树集假定插入的元素实现了 Comparable 接口，通过 compareTo（T other)方法进行比较。
	 * 或者是自定义类实现了 Comparator 接口，通过 compare(T a, T b)进行比较
	 */
	private void treeSetTest() {
		Set<String> words = new TreeSet<>();
		words.add("Boys");
		words.add("Amay");
		words.add("Chenn");
		for(String temp:words) {
			System.out.println(temp);
		}
	}
}
