package com.lidong.suanfa.tree.CompleteBinaryTree;

public class Node<T> {

	public T data;

	public Node leftChild;

	public Node rightChild;

	public Node(T data) {
		// TODO Auto-generated constructor stub
		leftChild = null;
		rightChild = null;
		this.data = data;
	}

	public Node() {
		// TODO Auto-generated constructor stub
	}

	public void display() {
		System.out.print(this.data + "\t");
	}

}
