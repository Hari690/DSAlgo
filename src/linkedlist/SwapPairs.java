package linkedlist;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You may not modify the values in the list's nodes. Only nodes itself may be changed.
 */
public class SwapPairs {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        //node.next.next.next = new problems.ListNode(4);

        ListNode listNode = new SwapPairs().swapPairs(node);

        ListNode temp = listNode;
        while(temp!=null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;
            first.next = second.next;
            current.next = second;
            current.next.next = first;
            // move 2 steps
            current = current.next.next;
        }
        return dummy.next;
    }
}
