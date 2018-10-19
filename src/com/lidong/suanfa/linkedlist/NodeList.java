package com.lidong.suanfa.linkedlist;

import java.util.HashSet;

public class NodeList {

    public Node head;
    public Node tail;

    public NodeList() {
        // TODO Auto-generated constructor stub
        head = null;
        tail = null;
    }

    public static void main(String[] args) {

        NodeList list = new NodeList();

        for (int i = 1; i <= 15; i++) {
            list.add(i);
        }
//        list.tail.next = list.get(8);
//        System.out.print("1: ");
//        System.out.println(list.entryNodeOfLoop1(list) != null ? list.entryNodeOfLoop1(list).data : "");
//        System.out.print("2: ");
//        System.out.println(list.entryNodeOfLoop2(list) != null ? list.entryNodeOfLoop2(list).data : "");
//        System.out.print("3: ");
//        System.out.println(list.entryNodeOfLoopExist3(list) != null ? list.entryNodeOfLoop(list).data : "");


        list.reverseList2(list);
        list.print();

    }

    public void add(int data) {
        Node temp = new Node(data);
        if (head == null) {
            head = temp;
            tail = temp;
        } else {
            tail.next = temp;
            tail = temp;
        }
    }

    public Node get(int index) {
        if (head == null) {
            return null;
        }
        Node temp = head;
        for (int i = 1; temp != null && i < index; i++) {
            temp = temp.next;
        }
        return temp;

    }

    public void print() {
        if (head == null) {
            return;
        }

        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "\t");
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * 穷举遍历
     * 比如一个链表 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,8,9,10,.....
     * 链表的环入口为 8
     * 当遍历到节点5的时候，我们需要比较的是之前的节点 1，2,3,4 不存在相同节点。
     * 当遍历到15的时候，发现 8 与 15的next重复，因此判断 8 为链表环的入口点
     *
     * @param list
     * @return
     */
    public Node entryNodeOfLoop1(NodeList list) {

        Node pos = list.head;
        if (pos == null || pos.next == null) {
            return null;
        }

        Node current = pos.next;
        while (current != null) {
            pos = list.head;
            while (pos != null && pos != current) {
                if (pos == current.next) {
                    return pos;
                }
                pos = pos.next;
            }
            current = current.next;
        }

        return null;

    }

    /**
     * 哈希表缓存
     * 用 HashSet 存储链表的节点
     * 遍历链表将节点存储在 HashSet 中，如若遇到 HashSet 已经存在该节点，那么证明链表有环。
     * 并且该重复点即为环的入口
     *
     * @param list
     * @return
     */
    public Node entryNodeOfLoop2(NodeList list) {
        HashSet<Node> set = new HashSet<>();
        Node pos = list.head;
        while (pos != null) {
            if (set.contains(pos)) {
                break;
            }
            set.add(pos);
            pos = pos.next;
        }
        return pos;
    }


    /**
     * 先用快慢指针判断链表是否有环，如果又换，快慢指针一定能相遇
     *
     * @param list
     * @return Node
     */

    public Node entryNodeOfLoopExist3(NodeList list) {
        Node first = list.head;
        if (first == null || first.next == null) {
            return null;
        }
        Node slow = first;
        Node fast = first;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return slow;
            }
        }
        return null;
    }


    /**
     * 假设链表头到环的入口处长度为  a , 环的长度为 r ,
     * 现在有fast slow 两个指针在移动，fast每次移动1，slow每次移动2
     * 当 slow 移动至 环的入口时，即移动了a 的时候， 假设 fast 在环上已经移动了n圈,此时移动至距离环入口 b 距离的位置：
     * 那么可得   a = nr + b;
     * <p>
     * 此时 fast 距离 slow 的距离为 r-b
     * 当 fast slow相遇时， 假设slow移动了 x 距离,
     * 那么可得  x / 1 = (r-b+x)/x   ，可得 x = r-b;
     * 即 slow 在环上移动了 x-b 的距离， 此时 slow 距离 环的长度为 b
     * <p>
     * 此时从链表头开始出发一个速度为 1 的指针 p，从slow当前位置也触发一个速度为1的指针 q;
     * 那么当 q 移动至环的入口点的时候 ， q移动了 b + tn 步 （t 取 0 ，1 ，2， 3 ......)
     * <p>
     * 观察到 由于  a = nr+b ( n 取 0 ，1 ，2， 3 ......)
     * 所以让 p 从链表起点出发，p,q，都每次向前移动1，当他们相遇的时候恰好就是环的入点，
     * 也就是说p从链表头移动到p，q再次相遇在这里的作用是提供一个计数。
     * 所以，当p，q再次相遇的时候，他们的相遇点恰好了t，也就是需要找的环的入口点。
     *
     * @param list
     * @return
     */
    public Node entryNodeOfLoop(NodeList list) {

        Node q = entryNodeOfLoopExist3(list);
        if (q == null) {
            return null;
        }
        Node p = list.head;
        while (p != q) {
            p = p.next;
            q = q.next;
        }
        if (p == q) {
            return q;
        }
        return null;
    }


    /**
     * // 1.就地反转法
     *
     * @param list
     */
    public void reverse(NodeList list) {
        if (list.head == null) {
            return;
        }

        Node dummpy = new Node();
        dummpy.next = list.head;
        Node pre = dummpy.next;
        Node current = pre.next;

        while (current != null) {
            pre.next = current.next;
            current.next = dummpy.next;
            dummpy.next = current;

            current = pre.next;
        }
        head = dummpy.next;
    }


    // 2.新建链表,头节点插入法
    public void reverseList2(NodeList list) {
        Node dummy = new Node();
        Node pCur = list.head;
        Node pNex;
        while (pCur != null) {
            pNex = pCur.next;
            pCur.next = dummy.next;
            dummy.next = pCur;
            pCur = pNex;
        }
        list.head = dummy.next;
    }
}