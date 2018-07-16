package com.lidong.set_demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Set;

public class SetDemoTest {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		LinkedListQueue<String> test = new LinkedListQueue<>();
		test.add("test1");
		test.add("test2");
		test.add("test3");
		test.add("test4");
		
		System.out.println(test.size());
		test.showInfo();
		
		System.out.println(test.remove());
		test.showInfo();
		
		
		LinkedList<String> data = new LinkedList<>();
		data.add("A");
		data.add("B");
		data.add("C");
		Iterator<String> iterator = data.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		LinkedList<String> data1 = new LinkedList<>();
		data1.add("AA");
		data1.add("BB");
		data1.add("CC");
		ListIterator<String> iterator1 = data1.listIterator();
		iterator1.next();
		iterator1.add("DD");
		iterator1 = data1.listIterator();
		
		
		while (iterator1.hasNext()) {
			String tempString = iterator1.next()+"1";
			iterator1.set(tempString);
		}
		for(int i = 0; i<data1.size();i++) {
			System.out.println(data1.get(i));
		}
		
		System.out.println("----------------- end 1---------------");
		
		long total = 0;
		Set<String> words = new HashSet<>();
		File file = new File("resource/hello.txt");
		Scanner scanner = new Scanner(file);
		while(scanner.hasNext()) {
			String world = scanner.next();
			long calltime = System.currentTimeMillis();
			words.add(world);
			long during = System.currentTimeMillis() - calltime;
			total += during;
		}
		
		Iterator<String> iterator2 = words.iterator();
		while (iterator2.hasNext()) {
			System.out.println(iterator2.next());
		}
		System.out.println("total time: "+total);
		
		
		System.out.println("----------------- end 2---------------");
		
		List<String> list = new ArrayList<>();
		list.add("A");
		list.add("A");
		System.out.println(list);
	}

}
