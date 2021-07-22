package linkedlist;

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
