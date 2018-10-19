package com.lidong.tree.Binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T> 完全二叉树
 * @author ubuntu
 */

public class CompleteBinaryTree<T> implements Tree<T> {

    Node root;
    private T[] dataArray;
    List<Node> listNodes;

    public CompleteBinaryTree(T[] dataArray) {
        // TODO Auto-generated constructor stub
        this.dataArray = dataArray;
    }

    public void createTree() {
        listNodes = new ArrayList<>();
        for (int i = 0; i < dataArray.length; i++) {
            listNodes.add(new Node<>(dataArray[i]));
        }

        /**
         * 从最后一个节点的父节点开始构建完全二叉树.
         *
         * 父节点 i 的话，他的左节点为 2i+1 他的右节点为 2i+2
         *
         * 最后一个节点的父节点是 (index-1)/2
         */
        for (int indexLastNodeParent = (dataArray.length - 2) / 2; indexLastNodeParent >= 0; indexLastNodeParent--) {
            if ((2 * indexLastNodeParent + 1) < dataArray.length) {
                listNodes.get(indexLastNodeParent).leftChild = listNodes.get(2 * indexLastNodeParent + 1);
            }
            if ((2 * indexLastNodeParent + 2) < dataArray.length) {
                listNodes.get(indexLastNodeParent).rightChild = listNodes.get(2 * indexLastNodeParent + 2);
            }
        }

        root = listNodes.get(0);
    }

    @Override
    public Node findKey(T value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Node findNode(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(T value) {
        // TODO Auto-generated method stub

    }

    @Override
    public void inOrderTraverse() {
        // TODO Auto-generated method stub
        inOrderTraverse(root);
    }

    private void inOrderTraverse(Node root) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.leftChild);
        System.out.print(root.data + "\t");
        inOrderTraverse(root.rightChild);
    }

    @Override
    public void inOrderByStack() {
        // TODO Auto-generated method stub

    }

    @Override
    public void preOrderTraverse() {
        // TODO Auto-generated method stub

    }

    @Override
    public void preOrderByStack() {
        // TODO Auto-generated method stub

    }

    @Override
    public void postOrderTraverse() {
        // TODO Auto-generated method stub

    }

    @Override
    public void postOrderByStack() {
        // TODO Auto-generated method stub

    }

    @Override
    public int getMinValue() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean delete(T value) {
        // TODO Auto-generated method stub
        return false;
    }

}
