package com.lidong.suanfa.tree.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<T> {

    private TreeNode root;
    private T[] nodesData;
    ArrayList<TreeNode> nodesList;

    public static final int DRATH_MAX = 0;
    public static final int DEATH_MIN = 1;
    public static final int NUMBER_TREENODE = 2;
    public static final int NUMBER_NOCHILD_NODE = 3;
    public static final int NUMBER_IN_LEVEL = 4;
    public static final int NODES_IN_LEVEL = 5;

    public static final String PRE_raversingTree = "pre";
    public static final String IN_raversingTree = "in";
    public static final String POST_raversingTree = "post";
    public static final String BFS = "BFS";

    private TreeNode rootNode;
    private TreeNode tempNode;
    private TreeNode left_childDataNode;
    private TreeNode right_childDataNode;

    public BinaryTree(T[] nodesData) {
        this.nodesData = nodesData;
        if (nodesData.length >= 1) {
            nodesList = new ArrayList<>();
            for (T temp : nodesData) {
                nodesList.add(new TreeNode(temp));
            }
            root = nodesList.get(0);
        }
    }

    public BinaryTree() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) throws Exception {
        // Integer[] datas = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        // BinaryTree tree = new BinaryTree(datas);
        // tree.buildCompletBinaryTree();
        // tree.get(7).left = new TreeNode(16);

        String[] data = {"a", "b", "c", "d", "e", "f", "g"};
        BinaryTree tree = new BinaryTree(data);
        tree.get(0).left = tree.get(1);
        tree.get(0).right = tree.get(2);
        tree.get(1).left = tree.get(3);
        tree.get(1).right = tree.get(5);
        tree.get(3).right = tree.get(4);
        tree.get(5).left = tree.get(6);

        System.out.print("DFS : ");
        tree.DFS(tree.root);

        System.out.print("BFS ： ");
        tree.BFS(tree.root);

        System.out.print("前序遍历递归：  ");
        tree.traversingTree(PRE_raversingTree, true);
        System.out.print("前序遍历非递归： ");
        tree.traversingTree(PRE_raversingTree, false);
        System.out.print("中序遍历递归：  ");
        tree.traversingTree(IN_raversingTree, true);
        System.out.print("中序遍历非递归： ");
        tree.traversingTree(IN_raversingTree, false);
        System.out.print("后序遍历递归：  ");
        tree.traversingTree(POST_raversingTree, true);
        System.out.print("后序遍历非递归： ");
        tree.traversingTree(POST_raversingTree, false);


        System.out.println("Max death: " + tree.treeInquire(DRATH_MAX));
        System.out.println("Min death: " + tree.treeInquire(DEATH_MIN));
        System.out.println("Total number: " + tree.treeInquire(NUMBER_TREENODE));
        System.out.println("Node No Child number: " + tree.treeInquire(NUMBER_NOCHILD_NODE));
        System.out.println("Level 4 node Number: " + tree.treeInquire(NUMBER_IN_LEVEL, 4));
        System.out.print("Level 4 nodes: ");
        tree.nodesInLevel(3);

        // int[] pre = { 1, 2, 4, 5, 6, 7, 3 };
        // int[] in = { 4, 5, 2, 7, 6, 1, 3 };
        // TreeNode<Integer> myRoot = new TreeNode<>();
        // BinaryTree<Integer> tree2 = new BinaryTree<>();
        // tree2.BuildTree(pre, in, myRoot);
        // tree2.root = myRoot;
        // tree2.raversingTree(POST_raversingTree, true);
    }

    /**
     * 从最后一个节点的父节点开始构建完全二叉树.
     * <p>
     * 父节点 i 的话，他的左节点为 2i+1 他的右节点为 2i+2
     * <p>
     * 最后一个节点的父节点是 (index-1)/2
     */
    public void buildCompletBinaryTree() {
        for (int lastIndexParent = (nodesData.length - 2) / 2; lastIndexParent >= 0; lastIndexParent--) {
            if ((2 * lastIndexParent + 1) < nodesData.length) {
                nodesList.get(lastIndexParent).left = nodesList.get(2 * lastIndexParent + 1);
            }
            if ((2 * lastIndexParent + 2) < nodesData.length) {
                nodesList.get(lastIndexParent).right = nodesList.get(2 * lastIndexParent + 2);
            }
        }

    }

    public TreeNode get(int i) {
        if (nodesList == null) {
            return null;
        }
        return nodesList.get(i);
    }

    /**
     * 遍历树
     *
     * @param method    遍历方法
     * @param recursive 是否递归
     * @throws Exception
     */

    public void traversingTree(String method, boolean recursive) throws Exception {
        switch (method) {
            case PRE_raversingTree:
                if (recursive) {
                    preOrderTraverse(root);
                } else {
                    preOrderTraverseByStack(root);
                }
                break;
            case IN_raversingTree:
                if (recursive) {
                    inOrderTraverse(root);
                } else {
                    inOrderTraverseByStack(root);
                }
                break;
            case POST_raversingTree:
                if (recursive) {
                    postOrderTraverse(root);
                } else {
                    postOrderTraverseByStack(root);
                }
                break;
            default:
                throw new Exception("Inquire Method not found exception");
        }
        System.out.println();
    }

    private void preOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + "\t");
        preOrderTraverse(root.left);
        preOrderTraverse(root.right);
    }

    private void preOrderTraverseByStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                System.out.print(current.val + "\t");
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            current = current.right;
        }
    }

    private void inOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.left);
        System.out.print(root.val + "\t");
        inOrderTraverse(root.right);
    }

    private void inOrderTraverseByStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            System.out.print(current.val + "\t");
            current = current.right;
        }
    }

    private void postOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderTraverse(root.left);
        postOrderTraverse(root.right);
        System.out.print(root.val + "\t");
    }

    private void postOrderTraverseByStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        TreeNode flag = null;

        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                if (current.right == null || current.right == flag) {
                    System.out.print(current.val + "\t");
                    flag = current;
                    current = null;
                } else {
                    stack.push(current);
                    current = current.right;
                    stack.push(current);
                    current = current.left;
                }
            }
        }
    }


    /**
     * 其过程简要来说是对每一个可能的分支路径深入到不能再深入为止，而且每个节点只能访问一次。
     * 深度优先遍历各个节点，需要使用到栈（Stack）这种数据结构。stack的特点是是先进后出。
     * 先往栈中压入右节点，再压左节点，这样出栈就是先左节点后右节点了。
     */
    public void DFS(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        stack.push(current);
        while (!stack.empty()) {
            current = stack.pop();
            System.out.print(current.val + "\t");
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
        System.out.println();
    }

    /**
     * 对每一层节点依次访问，访问完一层进入下一层，而且每个节点只能访问一次。
     * 先往队列中插入左节点，再插右节点，这样出队就是先左节点后右节点了。
     * 广度优先遍历树，需要用到队列（Queue）来存储节点对象,队列的特点就是先进先出。
     */
    public void BFS(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        TreeNode current;
        queue.offer(root);
        while (!queue.isEmpty()) {
            current = queue.poll();
            System.out.print(current.val);
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
    }

    private void BFS2(TreeNode root) throws Exception {

        int n = treeInquire(DRATH_MAX);
        for (int i = 1; i <= n; i++) {
            System.out.print("Level " + i + " : ");
            nodesInLevel(i);
            System.out.println();
        }

    }

//	public void BuildTree(int[] preArray, int[] midArray, TreeNode<T> rootNode) {
//		int index_root = getIndex(midArray, (int) rootNode.val);
//		int lengthOfRightTree = midArray.length - index_root - 1;
//
//		int[] preArray_left;
//		int[] preArray_right;
//		int[] midArray_left;
//		int[] midArray_right;
//
//		if (index_root > 0) {
//			left_childDataNode = new TreeNode<T>();
//			if (index_root == 1) {
//				left_childDataNode.val = midArray[0];
//				rootNode.left = left_childDataNode;
//			} else {
//				preArray_left = new int[index_root];
//				midArray_left = new int[index_root];
//				System.arraycopy(preArray, 1, preArray_left, 0, index_root);
//				System.arraycopy(midArray, 0, midArray_left, 0, index_root);
//				left_childDataNode.val = preArray_left[0];
//				rootNode.left = left_childDataNode;
//				BuildTree(preArray_left, midArray_left, left_childDataNode);
//			}
//		}
//
//		if (lengthOfRightTree > 0) {
//			right_childDataNode = new TreeNode<>();
//			if (lengthOfRightTree == 1) {
//				right_childDataNode.val = midArray[index_root + 1];
//				rootNode.right = right_childDataNode;
//				return;
//			} else {
//				preArray_right = new int[lengthOfRightTree];
//				midArray_right = new int[lengthOfRightTree];
//				System.arraycopy(preArray, index_root + 1, preArray_right, 0, lengthOfRightTree);
//				System.arraycopy(midArray, index_root + 1, midArray_right, 0, lengthOfRightTree);
//				right_childDataNode.val = preArray_right[0];
//				rootNode.right = right_childDataNode;
//				BuildTree(preArray_right, midArray_right, right_childDataNode);
//			}
//		}
//
//	}

    public int getIndex(int[] midArray, int val) {
        int index = -1;
        for (int i = 0; i < midArray.length; i++) {
            if (midArray[i] == val) {
                index = i;
                return index;
            }
        }
        return index;
    }

    /**
     * 查询树
     *
     * @param method 查询类型
     * @return
     * @throws Exception
     */
    public int treeInquire(int method) throws Exception {
        return treeInquire(method, root);
    }

    private int treeInquire(int method, TreeNode root) throws Exception {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = treeInquire(method, root.left);
        int right = treeInquire(method, root.right);
        switch (method) {
            case DRATH_MAX:
                return Math.max(left, right) + 1;

            case DEATH_MIN:
                return Math.min(left, right) + 1;

            case NUMBER_TREENODE:
                return left + right + 1;

            case NUMBER_NOCHILD_NODE:
                return left + right;

            default:
                throw new Exception("Inquire Method not found exception");
        }
    }

    public int treeInquire(int method, int level) {
        return nodeNumberInLevel(root, level);
    }

    private int nodeNumberInLevel(TreeNode root, int level) {
        if (root == null || level < 1) {
            return 0;
        }
        if (level == 1) {
            return 1;
        }
        int left = nodeNumberInLevel(root.left, level - 1);
        int right = nodeNumberInLevel(root.right, level - 1);
        return left + right;

    }

    public void nodesInLevel(int level) {
        nodesInLevel(root, level);
    }

    private void nodesInLevel(TreeNode root, int level) {
        TreeNode current = root;
        if (current == null || level < 1) {
            return;
        }
        if (level == 1) {
            System.out.print(current.val + "\t");
        }
        nodesInLevel(current.left, level - 1);
        nodesInLevel(current.right, level - 1);
    }

}
