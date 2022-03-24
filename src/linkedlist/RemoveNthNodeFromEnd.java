package linkedlist;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 *
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 *
 * Input: head = [1,2], n = 1
 * Output: [1]
 */
public class RemoveNthNodeFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(-1);
        start.next = head;
        ListNode slow = start;
        ListNode fast = start;

        for(int i=0;i<=n;i++) {
            fast = fast.next;
        }

        while(fast!=null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;

        return start.next;
    }
}
