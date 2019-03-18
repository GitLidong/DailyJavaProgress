package com.lidong.suanfa.tree.CompleteBinaryTree;

public interface Tree<T> {

	public Node findKey(T value); // 查找

	public Node findNode(int index);

	public void insert(T value); // 插入

	public void inOrderTraverse(); // 中序遍历递归操作

	public void inOrderByStack(); // 中序遍历非递归操作

	public void preOrderTraverse(); // 前序遍历

	public void preOrderByStack(); // 前序遍历非递归操作

	public void postOrderTraverse(); // 后序遍历

	public void postOrderByStack(); // 后序遍历非递归操作

	public int getMinValue(); // 得到最小(大)值

	public boolean delete(T value); // 删除
}
