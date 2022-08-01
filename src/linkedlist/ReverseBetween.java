package linkedlist;

/**
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 */
public class ReverseBetween {
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev1 = null, cur1=dummy;

        for(int i=0;i<m;i++) {
            prev1 = cur1;
            cur1 = cur1.next;
        }

        ListNode prev2 = prev1, cur2=cur1;
        for(int i=m;i<=n;i++) {
            ListNode temp = cur2.next;
            cur2.next = prev2;
            prev2 = cur2;
            cur2 = temp;
        }

        prev1.next = prev2;
        cur1.next = cur2;

        return dummy.next;
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

        ReverseBetween.test(node1, 2, 4);
    }

    public static ListNode test(ListNode head, int left, int right) {
        return reverse(head, left, right);
    }

    private static ListNode reverse(ListNode node1, int left, int right) {
        ListNode head = node1;
        ListNode prev = null;
        while(node1.val!=left) {
            prev = node1;
            node1=node1.next;
        }
        ListNode next = null;
        ListNode newHead = prev;
        while(node1!=null) {
            next = node1.next;
            node1.next = newHead;
            newHead = node1;
            node1 = next;
        }
        prev.next = newHead;
        return head;
    }
}
