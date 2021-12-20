package linkedlist;

/**
 * You are given the head of a linked list, and an integer k.
 *
 * Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the
 * list is 1-indexed).
 */
public class SwappingNodes {
    public ListNode swapNodes(ListNode head, int k) {
        ListNode first = head;
        for(int i=1;i<k;i++) {
            first = first.next;
        }

        ListNode slow = head;
        ListNode fast = head;
        int i=0;
        while(fast!=null) {
            fast=fast.next;
            if(i>=k) {
                slow = slow.next;
            }
            i++;
        }

        int temp = first.val;
        first.val = slow.val;
        slow.val = temp;

        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        new SwappingNodes().swapNodes(node1, 2);
    }
}
