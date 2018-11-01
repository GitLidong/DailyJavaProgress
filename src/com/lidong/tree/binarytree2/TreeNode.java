package com.lidong.tree.binarytree2;

public class TreeNode<T> {

	T val;
	TreeNode left;
	TreeNode right;

	public TreeNode(T val) {
		this.val = val;
		left = null;
		right = null;
	}

	public TreeNode() {
		// TODO Auto-generated constructor stub
		val = null;
		left = null;
		right = null;
	}

}
