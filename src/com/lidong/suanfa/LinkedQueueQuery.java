package com.lidong.suanfa;

import com.lidong.suanfa.res.*;

public class LinkedQueueQuery {

	public static void main(String[] args) {

		NodeList list = new NodeList();

		for (int i = 1; i <= 15; i++) {
			list.add(i);
		}
		list.tail.next = list.get(8);
		System.out.print("1: ");
		System.out.println(list.entryNodeOfLoop1(list) != null ? list.entryNodeOfLoop1(list).data : "");
		System.out.print("2: ");
		System.out.println(list.entryNodeOfLoop2(list) != null ? list.entryNodeOfLoop2(list).data : "");
		System.out.print("3: ");
		System.out.println(list.entryNodeOfLoopExist3(list) != null ? list.entryNodeOfLoop(list).data : "");

	}

}
