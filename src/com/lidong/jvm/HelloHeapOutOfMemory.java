package com.lidong.jvm;

import java.util.ArrayList;
import java.util.List;

class Person {
	
}

public class HelloHeapOutOfMemory {
	
	public static void main(String[] args) {
		System.out.println("HelloHeapOutOfMemory");
		
		while (true) {
			addPersonn();
		}

	}
	
	
	private static void addPersonn() {
		List<Person> persons = new ArrayList<>();
		int counter = 0;
		while (counter<1000) {
			persons.add(new Person());
			System.out.println("Person Instance: "+(++counter));
		}
		System.gc();
	}
}
