package com.lidong.suanfa;

public class LinkedListSUm {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(1);
        l1.next.next = new ListNode(1);

        ListNode l2 = new ListNode(0);
//        l2.next = new ListNode(9);
//        l2.next.next = new ListNode(9);
//        l2.next.next.next = new ListNode(8);
//        l2.next.next.next.next = new ListNode(7);
//        l2.next.next.next.next.next = new ListNode(6);

        ListNode result = addTwoNumbers(l1, l2);

        while (result != null) {
            System.out.print(result.val + " , ");
            result = result.next;
            if (result == null) {
                break;
            }
        }


    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        this.val = x;
    }
}