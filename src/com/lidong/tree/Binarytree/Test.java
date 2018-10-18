package com.lidong.tree.Binarytree;

public class Test {

	public static void main(String[] args) {
		Integer array[] = { 4, 2, 5, 1, 3, 6, 7 };

		CompleteBinaryTree<Integer> tree = new CompleteBinaryTree<>(array);

		tree.createTree();

		tree.inOrderTraverse();

	}

}
