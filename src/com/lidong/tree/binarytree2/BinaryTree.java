package com.lidong.tree.binarytree2;


import java.util.ArrayList;
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

    public static void main(String[] args) throws Exception {
//        Integer[] datas = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        BinaryTree tree = new BinaryTree(datas);
//        tree.buildCompletBinaryTree();
//        tree.get(7).left = new TreeNode(16);

        String[] data = {"a", "b", "c", "d", "e", "f", "g"};
        BinaryTree tree = new BinaryTree(data);
        tree.get(0).left = tree.get(1);
        tree.get(0).right = tree.get(2);
        tree.get(1).left = tree.get(3);
        tree.get(1).right = tree.get(5);
        tree.get(3).right = tree.get(4);
        tree.get(5).left = tree.get(6);

        System.out.print("前序遍历递归：  ");
        tree.raversingTree(PRE_raversingTree, true);
        System.out.print("前序遍历非递归： ");
        tree.raversingTree(PRE_raversingTree, false);
        System.out.print("中序遍历递归：  ");
        tree.raversingTree(IN_raversingTree, true);
        System.out.print("中序遍历非递归： ");
        tree.raversingTree(IN_raversingTree, false);
        System.out.print("后序遍历递归：  ");
        tree.raversingTree(POST_raversingTree, true);
        System.out.print("后序遍历非递归： ");
        tree.raversingTree(POST_raversingTree, false);
        System.out.println("Max death: " + tree.treeInquire(DRATH_MAX));
        System.out.println("Min death: " + tree.treeInquire(DEATH_MIN));
        System.out.println("Total number: " + tree.treeInquire(NUMBER_TREENODE));
        System.out.println("Node No Child number: " + tree.treeInquire(NUMBER_NOCHILD_NODE));
        System.out.println("Level 4 node Number: " + tree.treeInquire(NUMBER_IN_LEVEL, 4));
        System.out.print("Level 4 nodes: ");
        tree.nodesInLevel(3);
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

    public void raversingTree(String method, boolean recursive) throws Exception {
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
     * 查询书
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
