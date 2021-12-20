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
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(5);

        node1.next = node2;

        ReverseBetween.reverseBetween(node1, 1, 2);
    }
}
