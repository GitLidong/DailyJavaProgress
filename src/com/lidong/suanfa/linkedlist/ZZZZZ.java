package com.lidong.suanfa.linkedlist;

public class ZZZZZ {

    public static void main(String[] args) {

        ZZZZZ zzzzz = new ZZZZZ();
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(5);

        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(3);
        l1.next = l12;
        l12.next = l13;

        ListNode l22 = new ListNode(6);
        ListNode l23 = new ListNode(4);
        l2.next = l22;
        l22.next = l23;

        ListNode result = zzzzz.addTwoNumbers(l1, l2);
        ListNode temp = result;
        while (temp != null) {
            System.out.printf(temp.val + "\t");
            temp = temp.next;
        }

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int total = listToNum(l1) + listToNum(l2);
        return numToList(total);
    }

    public int listToNum(ListNode head) {
        int i = 0;
        int result = 0;
        while (head != null) {
            result += head.val * Math.pow(10, i);
            head = head.next;
            i++;
        }
        return result;
    }

    public ListNode numToList(int num) {
        int temp = num % 10;
        ListNode head = new ListNode(temp);
        ListNode cur = head;
        num = num / 10;
        while (num != 0) {
            temp = num % 10;
            cur.next = new ListNode(temp);
            cur = cur.next;
            num = num / 10;
        }
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}