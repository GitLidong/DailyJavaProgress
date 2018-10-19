package com.lidong.suanfa;

import java.util.Stack;

import com.lidong.suanfa.linkedlist.Node;
import com.lidong.suanfa.linkedlist.NodeList;

public class ReverseNode {

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		NodeList list = new NodeList();

		for (int i = 1; i <= 15; i++) {
			list.add(i);
		}

		ReverseNode demo = new ReverseNode();
		//demo.recNode(list.head);
		// demo.reverseNode1(list.head);
		/// demo.recNode(list.tail);
	}

	/**
	 * 递归
	 *
	 * @param node
	 */
	public void recNode(Node node) {

		if (node == null) {
			System.out.println("null");
			return;
		}
		if (node.next != null) {
			System.out.println("recNode:  " + node.next.data);
			recNode(node.next);
		}
		System.out.print(node.data + "===>");
	}

	/**
	 * 利用栈的先进后出特性
	 * 
	 * @param node
	 */
	public void reverseNode1(Node node) {
		Stack<Node> stack = new Stack<>();
		while (node != null) {
			System.out.print(node.data + "===>");
			stack.push(node);
			node = node.next;
		}

		System.out.println("");

		System.out.println("====翻转之后====");
		while (!stack.isEmpty()) {
			System.out.print(stack.pop().data + "===>");
		}
	}

}
